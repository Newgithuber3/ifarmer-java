package cn.tjau.ifarmer.domain;

import java.util.Date;

public class SellerInfo {
    private Integer id;

    private String storename;

    private Float score;

    private Date registTime;

    private Integer hostid;

    private Integer storeid;

    private Double turnover;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename == null ? null : storename.trim();
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public Integer getHostid() {
        return hostid;
    }

    public void setHostid(Integer hostid) {
        this.hostid = hostid;
    }

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public Double getTurnover() {
        return turnover;
    }

    public void setTurnover(Double turnover) {
        this.turnover = turnover;
    }

    @Override
    public String toString() {
        return "SellerInfo{" +
                "id=" + id +
                ", storename='" + storename + '\'' +
                ", score=" + score +
                ", registTime=" + registTime +
                ", hostid=" + hostid +
                ", storeid=" + storeid +
                ", turnover=" + turnover +
                '}';
    }
}