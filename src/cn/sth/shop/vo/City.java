package cn.sth.shop.vo;

import java.io.Serializable;

/**
 * ClassName:City
 * Package:cn.sth.shop.vo
 * Description:
 *
 * @Date:2020/1/25 21:52
 * Author:沙天慧
 */
public class City implements Serializable {
    private Integer cid;
    private String title;
    private Provincial provincial;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Provincial getProvincial() {
        return provincial;
    }

    public void setProvincial(Provincial provincial) {
        this.provincial = provincial;
    }
}
