package cn.sth.shop.vo;

import java.io.Serializable;

/**
 * ClassName:Shopcar
 * Package:cn.sth.shop.vo
 * Description:
 *
 * @Date:2020/1/19 15:46
 * Author:沙天慧
 */
public class Shopcar implements Serializable {
    private Goods goods;
    private Member member;
    private Integer amount;
    public Shopcar(){}

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
