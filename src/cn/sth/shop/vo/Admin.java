package cn.sth.shop.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ClassName:Admin
 * Package:cn.sth.shop.vo
 * Description:
 *
 * @Date:2020/1/10 14:54
 * Author:沙天慧
 */
public class Admin implements Serializable {
    private String aid;
    private String password;
    private List<Goods> allgoods;//一个管理员可以发布多个商品

    public List<Goods> getAllgoods() {
        return allgoods;
    }

    public void setAllgoods(List<Goods> allgoods) {
        this.allgoods = allgoods;
    }

    private Date lastdate;

    public Admin() {
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastdate() {
        return lastdate;
    }

    public void setLastdate(Date lastdate) {
        this.lastdate = lastdate;
    }
}
