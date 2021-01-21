package cn.sth.shop.service.back;

import cn.sth.shop.vo.Orders;

import java.util.Map;

/**
 * ClassName:IOrdersServiceBack
 * Package:cn.sth.shop.service.back
 * Description:
 *
 * @Date:2020/1/22 20:02
 * Author:沙天慧
 */
public interface IOrdersServiceBack {
    public Map<String,Object> list(int currentPage,int lineSize,String column,String keyword) throws Exception;
    public Orders show(int oid) throws Exception;
}
