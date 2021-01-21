package cn.sth.shop.service.front.impl;

import cn.sth.shop.dbc.DataBaseConnection;
import cn.sth.shop.exception.EmptyShopcarException;
import cn.sth.shop.exception.UnCompleteMemberInfomationException;
import cn.sth.shop.exception.UnEnoughAmountException;
import cn.sth.shop.factory.DAOFactory;
import cn.sth.shop.service.front.IOrdersServiceFront;
import cn.sth.shop.vo.Details;
import cn.sth.shop.vo.Goods;
import cn.sth.shop.vo.Member;
import cn.sth.shop.vo.Orders;

import javax.print.attribute.standard.DateTimeAtCompleted;
import java.sql.SQLException;
import java.util.*;

/**
 * ClassName:OrdersServiceFrontImpl
 * Package:cn.sth.shop.service.front
 * Description:
 *
 * @Date:2020/1/21 13:55
 * Author:沙天慧
 */
public class OrdersServiceFrontImpl implements IOrdersServiceFront {
    private DataBaseConnection dbc=new DataBaseConnection();
    @Override
    public boolean insert(String mid) throws UnCompleteMemberInfomationException, UnEnoughAmountException, EmptyShopcarException, SQLException {
        boolean flag=false;
        dbc.getConnection().setAutoCommit(false);//取消自动提交
        try {
            //首先判断当前用户信息是否完整
            Member member = DAOFactory.getMemberDAOInstance(this.dbc.getConnection()).findById2(mid);
            if (member.getName() == null || member.getPhone() == null || member.getAddress() == null) {
                throw new UnCompleteMemberInfomationException("用户个人信息不完整，无法进行订单创建！");
            }
            //数据库中是否包含购物车信息
            Map<Integer, Integer> map = DAOFactory.getShopcarInstance(this.dbc.getConnection()).findAllByMember(mid);
            if (map.size() == 0) {
                throw new EmptyShopcarException("购物车为空，无法创建订单！");
            }
            //有购物车信息，需要查询出所有的商品信息
            List<Goods> allGoods = DAOFactory.getGoodsInstance(this.dbc.getConnection()).findAllByGid(map.keySet());
            //库存量是否足够,同时计算总金额
            double pay = 0.0;
            Iterator<Goods> iterGoods = allGoods.iterator();
            while (iterGoods.hasNext()) {
                Goods vo = iterGoods.next();
                if (vo.getAmount() - map.get(vo.getGid()) < 0) {
                    throw new UnEnoughAmountException("商品库存量不足，无法购买！");
                }
                pay += vo.getPrice() * map.get(vo.getGid());//单价乘以购买数量
            }
            Orders order = new Orders();
            order.setMember(member);
            order.setName(member.getName());
            order.setPhone(member.getPhone());
            order.setAddress(member.getAddress());
            order.setCredate(new Date());
            order.setPay(pay);
            flag = DAOFactory.getOrdersInstance(this.dbc.getConnection()).doCreateOrders(order);
            //取得订单编号，订单详情需要
            Integer oid = DAOFactory.getOrdersInstance(this.dbc.getConnection()).findLastInsertId();
            //创建订单详情信息，以及修改所有购买商品的数量
            order.setOid(oid);
            iterGoods = null;
            iterGoods = allGoods.iterator();
            List<Details> all = new ArrayList<Details>();
            while (iterGoods.hasNext()) {
                Details vo = new Details();
                Goods goods = iterGoods.next();
                vo.setGoods(goods);//订单详情与商品关系
                vo.setOrders(order);//订单详情与订单的关系
                int amount = map.get(goods.getGid());
                vo.setAmount(amount);
                vo.setTitle(goods.getName());
                vo.setPrice(goods.getPrice());
                all.add(vo);
                flag = DAOFactory.getGoodsInstance(this.dbc.getConnection()).doUpdateByAmount(goods.getGid(), 0 - amount);
            }
            flag = DAOFactory.getDetailsInstance(this.dbc.getConnection()).doCreateBath(all);
            //清空购物车信息
            flag = DAOFactory.getShopcarInstance(this.dbc.getConnection()).doRemoveByMember(mid);
            dbc.getConnection().commit();//提交
        }catch (SQLException e){
            dbc.getConnection().rollback();//回滚
            throw e;
        }finally {
            this.dbc.close();
        }
        return flag;
    }

    @Override
    public Map<String,Object> listByMember(String mid, int currentPage, int lineSize) throws Exception {
        try {
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("allOrders",DAOFactory.getOrdersInstance(this.dbc.getConnection()).findAllByMember(mid, currentPage, lineSize));
            map.put("ordersCount",DAOFactory.getOrdersInstance(this.dbc.getConnection()).getAllCountByMember(mid));
            return map;
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Orders show(String mid, int oid) throws Exception {
        try {
            Orders vo=DAOFactory.getOrdersInstance(this.dbc.getConnection()).findByIdAndByMember(mid,oid);
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
