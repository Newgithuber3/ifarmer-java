package cn.tjau.ifarmer.domain;

import java.util.Date;

public class ProductParameter {
    private Integer id;

    private String origin;

    private Date productionDate;

    private String expirationDate;

    private String note;

    private Integer productid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate == null ? null : expirationDate.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    @Override
    public String toString() {
        return "ProductParameter{" +
                "id=" + id +
                ", origin='" + origin + '\'' +
                ", productionDate=" + productionDate +
                ", expirationDate='" + expirationDate + '\'' +
                ", note='" + note + '\'' +
                ", productid=" + productid +
                '}';
    }
}