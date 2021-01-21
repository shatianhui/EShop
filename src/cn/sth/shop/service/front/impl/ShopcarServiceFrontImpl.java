package cn.sth.shop.service.front.impl;

import cn.sth.shop.dbc.DataBaseConnection;
import cn.sth.shop.factory.DAOFactory;
import cn.sth.shop.service.front.IShopcarServiceFront;
import cn.sth.shop.vo.Goods;
import cn.sth.shop.vo.Shopcar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ClassName:ShopcarServiceFrontImpl
 * Package:cn.sth.shop.service.front.impl
 * Description:
 *
 * @Date:2020/1/18 15:40
 * Author:沙天慧
 */
public class ShopcarServiceFrontImpl implements IShopcarServiceFront {
    private DataBaseConnection dbc=new DataBaseConnection();
    @Override
    public boolean addCar(Shopcar vo) throws Exception {
        try{
            if(DAOFactory.getShopcarInstance(this.dbc.getConnection()).findByMemberAndGoods(vo.getMember().getMid(),vo.getGoods().getGid())!=null){
                return DAOFactory.getShopcarInstance(this.dbc.getConnection()).doUpdateAmount(vo.getMember().getMid(),vo.getGoods().getGid());
            }else{
                vo.setAmount(1);
                return DAOFactory.getShopcarInstance(this.dbc.getConnection()).doCreate(vo);
            }
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Map<String, Object> listCar(String mid) throws Exception {
        try {
            Map<String,Object> map=new HashMap<String, Object>();
            Map<Integer,Integer> cars=DAOFactory.getShopcarInstance(this.dbc.getConnection()).findAllByMember(mid);
            map.put("allShopcars",cars);
            System.out.println("***"+cars==null);
            map.put("allGoods",DAOFactory.getGoodsInstance(this.dbc.getConnection()).findAllByGid(cars.keySet()));
            return map;
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean deleteCar(String mid, Set<Integer> gid) throws Exception {
        try {
            return DAOFactory.getShopcarInstance(this.dbc.getConnection()).doRemoveByMemberAndGoods(mid,gid);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean update(String mid, Set<Shopcar> vos) throws Exception {
        try {
            if(vos.size()==0){
                return false;
            }
            if(DAOFactory.getShopcarInstance(this.dbc.getConnection()).doRemoveByMember(mid)){//清空购物车记录
                return DAOFactory.getShopcarInstance(this.dbc.getConnection()).doCreateBatch(vos);
            }
            return false;
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }
}
