package cn.sth.shop.service.back.impl;

import cn.sth.shop.dbc.DataBaseConnection;
import cn.sth.shop.factory.DAOFactory;
import cn.sth.shop.service.back.IGoodsServiceBack;
import cn.sth.shop.vo.Goods;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ClassName:GoodsServiceBackImpl
 * Package:cn.sth.shop.service.back.impl
 * Description:
 *
 * @Date:2020/1/13 15:07
 * Author:沙天慧
 */
public class GoodsServiceBackImpl implements IGoodsServiceBack {
    private DataBaseConnection dbc=new DataBaseConnection();
    @Override
    public Map<String, Object> insertPre() throws Exception {
        try{
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("allItems", DAOFactory.getItemDAOInstance(this.dbc.getConnection()).findAll());
            return map;
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean insert(Goods vo) throws Exception {
        try {
            return DAOFactory.getGoodsInstance(this.dbc.getConnection()).doCreate(vo);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyword) throws Exception {
        try{
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("allGoods",DAOFactory.getGoodsInstance(this.dbc.getConnection()).findAllSplit(currentPage,lineSize,column,keyword));
            map.put("goodsCount",DAOFactory.getGoodsInstance(this.dbc.getConnection()).getAllCount(column,keyword));
            return map;
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Map<String, Object> listStatus(int status,int currentPage, int lineSize, String column, String keyword) throws Exception {
        try{
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("allGoods",DAOFactory.getGoodsInstance(this.dbc.getConnection()).findAllByStatus(status,currentPage,lineSize,column,keyword));
            map.put("goodsCount",DAOFactory.getGoodsInstance(this.dbc.getConnection()).getAllCountByStatus(status,column,keyword));
            return map;
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean updateUp(Set<Integer> ids) throws Exception {
        try{
            return DAOFactory.getGoodsInstance(this.dbc.getConnection()).doUpdateStatus(ids,1);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean updateDown(Set<Integer> ids) throws Exception {
        try{
            return DAOFactory.getGoodsInstance(this.dbc.getConnection()).doUpdateStatus(ids,0);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean updateDelete(Set<Integer> ids) throws Exception {
        try{
            return DAOFactory.getGoodsInstance(this.dbc.getConnection()).doUpdateStatus(ids,2);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Map<String, Object> updatePre(int gid) throws Exception {
        try{
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("allItems", DAOFactory.getItemDAOInstance(this.dbc.getConnection()).findAll());
            map.put("goods",DAOFactory.getGoodsInstance(this.dbc.getConnection()).findById(gid));
            return map;
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean update(Goods vo) throws Exception {
        try{
            return DAOFactory.getGoodsInstance(this.dbc.getConnection()).doUpdate(vo);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Map<String, Object> deleteAll(Set<Integer> ids) throws Exception {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            map.put("allPhotos",DAOFactory.getGoodsInstance(this.dbc.getConnection()).findAllByPhoto(ids));
            map.put("flag",DAOFactory.getGoodsInstance(this.dbc.getConnection()).doRemoveBatch(ids));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.dbc.close();
        }
        return map;
    }

    @Override
    public boolean delete(Set<Integer> ids) throws Exception {
        try {
            return DAOFactory.getGoodsInstance(this.dbc.getConnection()).doRemoveBatch(ids);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.dbc.close();
        }
        return false;
    }
}
