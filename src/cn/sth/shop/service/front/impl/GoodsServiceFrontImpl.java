package cn.sth.shop.service.front.impl;

import cn.sth.shop.dbc.DataBaseConnection;
import cn.sth.shop.factory.DAOFactory;
import cn.sth.shop.service.front.IGoodsServiceFront;
import cn.sth.shop.vo.Goods;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:GoodsServiceFrontImpl
 * Package:cn.sth.shop.service.front.impl
 * Description:
 *
 * @Date:2020/1/17 14:03
 * Author:沙天慧
 */
public class GoodsServiceFrontImpl implements IGoodsServiceFront {
    private DataBaseConnection dbc=new DataBaseConnection();

    @Override
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyword) throws Exception {
        try{
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("allItems",DAOFactory.getItemDAOInstance(this.dbc.getConnection()).findAll());
            map.put("allGoods",DAOFactory.getGoodsInstance(this.dbc.getConnection()).findAllByStatus(1,currentPage,lineSize,column,keyword));
            map.put("goodsCount",DAOFactory.getGoodsInstance(this.dbc.getConnection()).getAllCountByStatus(1,column,keyword));
            return map;
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Map<String, Object> listByItem(int iid, int currentPage, int lineSize, String column, String keyword) throws Exception {
        try{
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("allItems",DAOFactory.getItemDAOInstance(this.dbc.getConnection()).findAll());
            map.put("allGoods",DAOFactory.getGoodsInstance(this.dbc.getConnection()).findAllByItem(iid,1,currentPage,lineSize,column,keyword));
            map.put("goodsCount",DAOFactory.getGoodsInstance(this.dbc.getConnection()).getAllCountByItem(iid,1,column,keyword));
            return map;
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Goods show(int gid) throws Exception {
        try{
            Goods vo=DAOFactory.getGoodsInstance(this.dbc.getConnection()).findById(gid);
            if(vo!=null){
                vo.setItem(DAOFactory.getItemDAOInstance(this.dbc.getConnection()).findById(vo.getItem().getIid()));
                DAOFactory.getGoodsInstance(this.dbc.getConnection()).doUpdateBrowse(gid);
            }
            return vo;
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }
}
