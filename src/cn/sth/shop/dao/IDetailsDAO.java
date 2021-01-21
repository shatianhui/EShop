package cn.sth.shop.dao;

import cn.sth.shop.vo.Details;

import java.sql.SQLException;
import java.util.List;

/**
 * ClassName:IDetailsDAO
 * Package:cn.sth.shop.dao
 * Description:
 *
 * @Date:2020/1/21 13:26
 * Author:沙天慧
 */
public interface IDetailsDAO extends IDAO<Integer, Details> {
    /**
     * 批量创建订单详情
     * @param vos
     * @return
     * @throws Exception
     */
    public boolean doCreateBath(List<Details> vos) throws SQLException;

    /**
     * 根据订单编号查询出订单的详情内容
     * @param oid
     * @return
     * @throws Exception
     */
    public List<Details> findAllByOrders(Integer oid) throws Exception;
}
