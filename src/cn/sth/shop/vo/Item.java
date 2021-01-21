package cn.sth.shop.vo;

import java.io.Serializable;

/**
 * ClassName:Item
 * Package:cn.sth.shop.vo
 * Description:
 *
 * @Date:2020/1/12 16:48
 * Author:沙天慧
 */
public class Item implements Serializable {
    private Integer iid;
    private String title;

    public Item() {
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
