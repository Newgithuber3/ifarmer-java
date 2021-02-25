package cn.tjau.ifarmer.domain;

import lombok.Data;

import java.util.Date;

@Data
public class OrderReturnApply {
    private Integer id;
    private String applyStatus;
    private Date applyTime;
    private Integer userAccount;
    private String applyReason;
    private String question;
    private String proofImg;
    private Date operateTime;
    private String operateNote;
    private Integer receiverAddress;
    private Long orderID;
    private Integer storeID;
}
