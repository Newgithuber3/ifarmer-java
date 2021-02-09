package cn.tjau.ifarmer.domain.utilEntity;

import lombok.Data;


@Data
public class OrderCondition {
    private Boolean isPay;
    private String buyerName;
    private String productName;
    private String createTime;
    private Integer pageNum;
    private Integer pageSize;
}
