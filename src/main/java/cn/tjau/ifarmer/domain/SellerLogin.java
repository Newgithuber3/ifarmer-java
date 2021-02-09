package cn.tjau.ifarmer.domain;

import lombok.Data;

@Data
public class SellerLogin {
    private Integer id;

    private String name;

    private String password;

    private String status;

    private String statusInfo;

    private SellerInfo sellerInfo;
}