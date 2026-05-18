package com.tz.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "lg_index")
public class SearchPojo {
    // es中主键是字符串类型
    @Id
    @Field(name = "id",type = FieldType.Keyword)
    private String id;
    @Field(name = "title",type = FieldType.Text,analyzer = "ik_max_word")
    private String title;
    @Field(name = "rentType",type = FieldType.Text,analyzer = "ik_max_word")
    private String rentType;
    @Field(name = "price",type = FieldType.Long)
    private Long price;
    @Field(name = "houseType",type = FieldType.Text,analyzer = "ik_max_word")
    private String houseType;
    @Field(name = "img",type = FieldType.Text)
    private String img;
    @Field(name = "city",type = FieldType.Keyword)
    private String city;
}
