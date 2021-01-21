package cn.sth.shop.vo;

/**
 * ClassName:Details
 * Package:cn.sth.shop.vo
 * Description:
 *
 * @Date:2020/1/21 13:14
 * Author:沙天慧
 */
public class Details {
    private Integer odid;
    private Orders orders;
    private Goods goods;
    private String title;
    private Double price;
    private Integer amount;

    @Override
    public String toString() {
        return "Details{" +
                "odid=" + odid +
                ", orders=" + orders +
                ", goods=" + goods +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }

    public Integer getOdid() {
        return odid;
    }

    public void setOdid(Integer odid) {
        this.odid = odid;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
