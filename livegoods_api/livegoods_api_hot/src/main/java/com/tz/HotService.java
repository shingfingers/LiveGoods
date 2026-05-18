package com.tz;

import com.tz.commons.vo.LivegoodsResult;

public interface HotService {
    LivegoodsResult showHotsales(String city);
    LivegoodsResult showRecommendation(String city);

}

