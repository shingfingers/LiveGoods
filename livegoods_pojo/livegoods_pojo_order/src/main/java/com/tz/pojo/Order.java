package com.tz.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Order implements Serializable {
    private Long id;
    private String userPhone;
    private Long houseId;
    private String houseTitle;
    private String houseType;
    private Long price;
    private String rentType;
    private Byte commentState;
    private String img;
}
