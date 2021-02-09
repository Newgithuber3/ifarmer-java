package cn.tjau.ifarmer.domain.utilEntity;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private Integer addressID;
    private List<Integer> cartList;
}
