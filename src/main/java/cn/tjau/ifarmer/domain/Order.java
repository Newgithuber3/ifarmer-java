package cn.tjau.ifarmer.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Long id;

    private String status;

    private Boolean ispay;

    private Double money;

    private String buyername;

    private String buyerphone;

    private Integer buyeraddress;

    private Date createtime;

    private Date updatetime;

    private Integer productid;

    private Product product;

    private OrderDetail orderDetail;

}