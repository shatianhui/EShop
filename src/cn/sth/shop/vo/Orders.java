package cn.sth.shop.vo;

import cn.sth.shop.dbc.DataBaseConnection;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ClassName:Orders
 * Package:cn.sth.shop.vo
 * Description:
 *
 * @Date:2020/1/21 13:14
 * Author:沙天慧
 */
public class Orders implements Serializable {
    private Integer oid;
    private Member member;
    private String name;
    private String phone;
    private String address;
    private Date credate;
    private Double pay;
    private List<Details> allDetails;//一个订单可以有多个订单详情
    public Orders(){}

    public Integer getOid() {
        return oid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCredate() {
        return credate;
    }

    public void setCredate(Date credate) {
        this.credate = credate;
    }

    public Double getPay() {
        return pay;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }

    public List<Details> getAllDetails() {
        return allDetails;
    }

    public void setAllDetails(List<Details> allDetails) {
        this.allDetails = allDetails;
    }
}
