package com.tz.pojo;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String title;
    private String img;
    private String city;
    private Long sales;
    private String link;
    private Boolean hot;
    private Integer hotSort;
}
