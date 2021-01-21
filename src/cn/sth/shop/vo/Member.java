package cn.sth.shop.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ClassName:Member
 * Package:cn.sth.shop.vo
 * Description:
 *
 * @Date:2020/1/8 12:38
 * Author:沙天慧
 */
public class Member implements Serializable {
    private String mid;
    private String password;
    private String name;
    private String phone;
    private String address;
    private String code;
    private int status;
    private Date regdate;
    private String photo;
    private List<Orders> allOrders; //一个用户可以有多个订单

    public List<Orders> getAllOrders() {
        return allOrders;
    }

    public void setAllOrders(List<Orders> allOrders) {
        this.allOrders = allOrders;
    }

    public Member() {
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
