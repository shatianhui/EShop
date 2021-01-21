package cn.sth.shop.service.back.impl;

import cn.sth.shop.dbc.DataBaseConnection;
import cn.sth.shop.factory.DAOFactory;
import cn.sth.shop.service.back.IOrdersServiceBack;
import cn.sth.shop.service.front.IOrdersServiceFront;
import cn.sth.shop.util.dao.AbstractDAOImpl;
import cn.sth.shop.vo.Orders;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:OrdersServiceBackImpl
 * Package:cn.sth.shop.service.back.impl
 * Description:
 *
 * @Date:2020/1/22 20:04
 * Author:沙天慧
 */
public class OrdersServiceBackImpl implements IOrdersServiceBack {
    DataBaseConnection dbc=new DataBaseConnection();
    @Override
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyword) throws Exception {
        try{
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("allOrders", DAOFactory.getOrdersInstance(this.dbc.getConnection()).findAllSplit(currentPage,lineSize,column,keyword));
            map.put("ordersCount",DAOFactory.getOrdersInstance(this.dbc.getConnection()).getAllCount(column,keyword));
            return map;
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Orders show(int oid) throws Exception {
        try{
            Orders vo=DAOFactory.getOrdersInstance(this.dbc.getConnection()).findById(oid);
            if (vo!=null){
                vo.setAllDetails(DAOFactory.getDetailsInstance(this.dbc.getConnection()).findAllByOrders(oid));
            }
            return vo;
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }
}
