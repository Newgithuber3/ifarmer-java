package cn.tjau.ifarmer.domain.utilEntity;

import lombok.Data;

@Data
public class ProductCondition {
    private Integer productID;
    private Integer storeID;
    private Integer category;
    private String name;
    private Integer pageNum = 1 ;
    private Integer pageSize = 5;

}
