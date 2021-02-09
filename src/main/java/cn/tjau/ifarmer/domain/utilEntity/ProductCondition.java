package cn.tjau.ifarmer.domain.utilEntity;

import lombok.Data;

@Data
public class ProductCondition {
    private Integer storeid;
    private Integer category;
    private String name;
    private Integer pageNum;
    private Integer pageSize;

}
