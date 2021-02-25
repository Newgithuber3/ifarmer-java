package cn.tjau.ifarmer.domain.utilEntity;

import lombok.Data;

import java.util.Date;

@Data
public class ReturnApplyCondition {
    private Integer id;
    private String applyStatus;
    private Date applyTime;
    private Integer storeID;
    private Integer pageNum;
    private Integer pageSize;
}
