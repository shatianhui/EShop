package cn.sth.shop.service.front;

import cn.sth.shop.exception.EmptyShopcarException;
import cn.sth.shop.exception.UnCompleteMemberInfomationException;
import cn.sth.shop.exception.UnEnoughAmountException;
import cn.sth.shop.vo.Orders;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * ClassName:IOrdersServiceFront
 * Package:cn.sth.shop.service.front
 * Description:
 * 用户信息不完善，抛出一个UnCompleteMemberInformationException异常(自定义异常)
 * 商品库存信息不足，可以抛出一个UnEnoughAmountException
 * 购物车没有数据，不能够创建，抛出EmptyShopcarException
 * @Date:2020/1/21 13:41
 * Author:沙天慧
 */
public interface IOrdersServiceFront {
    /**
     * 创建用户订单操作
     * @param mid
     * @return
     * @throws UnCompleteMemberInfomationException
     * @throws UnEnoughAmountException
     * @throws EmptyShopcarException
     * @throws SQLException
     */
    public boolean insert(String mid) throws UnCompleteMemberInfomationException, UnEnoughAmountException, EmptyShopcarException, SQLException;

    /**
     * 查看一个用户所有订单操作
     * @param mid
     * @param currentPage
     * @param lineSize
     * @return
     * @throws Exception
     */
    public Map<String,Object> listByMember(String mid, int currentPage, int lineSize) throws Exception;

    /**
     * 查看一个订单信息以及订单对应的详情信息
     * @param mid
     * @param oid
     * @return
     * @throws Exception
     */
    public Orders show(String mid,int oid) throws Exception;
}
