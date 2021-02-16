package cn.tjau.ifarmer.domain;

import lombok.Data;

import java.util.Date;

@Data
public class SellerInfo {
    private Integer id;

    private String storename;

    private Float score;

    private Date registTime;

    private Integer hostid;

    private Integer storeid;

    private Double turnover;

    private ProductCategory mainCategory;

    private SellerCategory storeCategory;
}