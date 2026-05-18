package com.tz.search.service.impl;

import com.tz.commons.vo.HasMoreResult;
import com.tz.commons.vo.LivegoodsResult;
import com.tz.pojo.SearchPojo;
import com.tz.search.service.SearchService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.CriteriaQueryBuilder;
import org.springframework.data.elasticsearch.core.query.HighlightQuery;
import org.springframework.data.elasticsearch.core.query.highlight.Highlight;
import org.springframework.data.elasticsearch.core.query.highlight.HighlightField;
import org.springframework.data.elasticsearch.core.query.highlight.HighlightFieldParameters;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@DubboService
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    // 从分布式配置中心中拿到nginx地址
    @Value("${livegoods.nginx.host}")
    private String nginxHost;
    @Override
    public LivegoodsResult search(String city, String content, Integer page, Integer size) {
        // 总体代码思路：通过ElasticsearchTemplate进行搜索，当参数需要什么，我们就在上面new什么对象。
        // 查询条件
        Criteria criteria = new Criteria()
                .or(new Criteria("city").matches(city).and("rentType").matches(content))
                .or(new Criteria("city").matches(city).and("title").matches(content))
                .or(new Criteria("city").matches(city).and("houseType").matches(content));
        // 高亮操作
        List<HighlightField> listFields = new ArrayList<>();
        // 设置高亮前缀和后缀
        HighlightFieldParameters fieldParameters = HighlightFieldParameters.builder()
                .withPreTags("<span style='color:red;'>")
                .withPostTags("</span>")
                .build();
        // 把三个高亮属性添加进去
        listFields.add(new HighlightField("title",fieldParameters));
        listFields.add(new HighlightField("rentType",fieldParameters));
        listFields.add(new HighlightField("houseType",fieldParameters));
        Highlight highlight = new Highlight(listFields);
        HighlightQuery highlightQuery = new HighlightQuery(highlight, SearchPojo.class);
        CriteriaQueryBuilder criteriaQueryBuilder = new CriteriaQueryBuilder(criteria)
                .withPageable(PageRequest.of(page,size)) // 分页的条件设置好了
                .withHighlightQuery(highlightQuery) // 设置高亮查询
                ;
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteriaQueryBuilder);
        // 返回值为外层hits
        // 参数1：在Spring Data Elaticsearch 5.x版本推荐使用QBC进行查询
        // 参数2：类中要包含Document注解，里面有索引的名称
        SearchHits<SearchPojo> searchHits = elasticsearchTemplate.search(criteriaQuery, SearchPojo.class);
        // 里层的hits
        List<SearchHit<SearchPojo>> listSearchHits = searchHits.getSearchHits();
        // 创建一个集合存储所有替换好高亮数据及图片路径的对象
        List<SearchPojo> contents = new ArrayList<>();
        for(SearchHit<SearchPojo> searchHit : listSearchHits){
            // 非高亮实体数据
            SearchPojo searchPojo = searchHit.getContent();
            // 替换高亮数据
            if(searchHit.getHighlightField("title").size()>0){
                searchPojo.setTitle(searchHit.getHighlightField("title").get(0));
            }
            if(searchHit.getHighlightField("rentType").size()>0){
                searchPojo.setRentType(searchHit.getHighlightField("rentType").get(0));
            }
            if(searchHit.getHighlightField("houseType").size()>0){
                searchPojo.setHouseType(searchHit.getHighlightField("houseType").get(0));
            }
            // 拼接nginx地址
            searchPojo.setImg(nginxHost+searchPojo.getImg());
            // 操作完成后放入到集合中
            contents.add(searchPojo);
        }
        HasMoreResult hasMoreResult = new HasMoreResult();
        hasMoreResult.setContents(contents);
        // 通过判断当前页是否为最后一页，就知道了还有没有下一页
        // 总条数
        long total = searchHits.getTotalHits();
        // 总页数
        long totalPage = total%size==0?total/size:total/size+1;
        if(totalPage>page+1){
            hasMoreResult.setHasMore(true);
        }else{
            hasMoreResult.setHasMore(false);
        }
        return LivegoodsResult.ok(hasMoreResult);
    }
}

