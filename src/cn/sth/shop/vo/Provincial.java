package cn.sth.shop.vo;

import java.io.Serializable;

/**
 * ClassName:Provincial
 * Package:cn.sth.shop.vo
 * Description:
 *
 * @Date:2020/1/25 21:51
 * Author:沙天慧
 */
public class Provincial implements Serializable {
    private Integer pid;
    private String title;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
