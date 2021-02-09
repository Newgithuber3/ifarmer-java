package cn.tjau.ifarmer.domain;

public class OrderDetail {
    private Long id;

    private Integer productid;

    private String productname;

    private Integer productnumber;

    private Double productprice;

    private String productimageUrl;

    private Long orderid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public Integer getProductnumber() {
        return productnumber;
    }

    public void setProductnumber(Integer productnumber) {
        this.productnumber = productnumber;
    }

    public Double getProductprice() {
        return productprice;
    }

    public void setProductprice(Double productprice) {
        this.productprice = productprice;
    }

    public String getProductimageUrl() {
        return productimageUrl;
    }

    public void setProductimageUrl(String productimageUrl) {
        this.productimageUrl = productimageUrl == null ? null : productimageUrl.trim();
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", productid=" + productid +
                ", productname='" + productname + '\'' +
                ", productnumber=" + productnumber +
                ", productprice=" + productprice +
                ", productimageUrl='" + productimageUrl + '\'' +
                ", orderid=" + orderid +
                '}';
    }
}