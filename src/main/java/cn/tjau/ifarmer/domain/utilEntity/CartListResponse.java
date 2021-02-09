package cn.tjau.ifarmer.domain.utilEntity;

import cn.tjau.ifarmer.domain.Cart;
import lombok.Data;

import java.util.Date;

@Data
public class CartListResponse {
    private Integer id;

    private Integer uid;

    private Date addtime;

    private Integer productid;

    private Integer number;

    private double price;

    private String productname;

    private String productimage;

    public CartListResponse(Cart cart, String productname ,String productimage) {
        this.id=cart.getId();
        this.uid=cart.getUid();
        this.addtime=cart.getAddtime();
        this.productid=cart.getProductid();
        this.number=cart.getNumber();
        this.price=cart.getPrice();
        this.productname=productname;
        this.productimage=productimage;
    }
}

