package cn.sth.shop.dao;

import cn.sth.shop.vo.Orders;

import java.sql.SQLException;
import java.util.List;

/**
 * ClassName:IOrdersDAO
 * Package:cn.sth.shop.dao
 * Description:
 *
 * @Date:2020/1/21 13:22
 * Author:沙天慧
 */
public interface IOrdersDAO extends IDAO<Integer, Orders> {
    /**
     * 调用last_insert_id()函数取得当前增长的订单编号，为订单详情做准备
     * @return
     * @throws Exception
     */
    public Integer findLastInsertId() throws SQLException;
    public boolean doCreateOrders(Orders vo) throws SQLException;

    /**
     *根据用户编号列出订单信息
     * @param mid
     * @return
     * @throws Exception
     */
    public List<Orders> findAllByMember(String mid,Integer currentPage,Integer lineSize) throws Exception;
    public Integer getAllCountByMember(String mid) throws Exception;

    /**
     * 查询一个用户的一个订单信息
     * @param mid
     * @param oid
     * @return
     * @throws Exception
     */
    public Orders findByIdAndByMember(String mid,Integer oid) throws Exception;
}
