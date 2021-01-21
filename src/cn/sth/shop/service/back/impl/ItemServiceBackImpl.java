package cn.sth.shop.service.back.impl;

import cn.sth.shop.dbc.DataBaseConnection;
import cn.sth.shop.factory.DAOFactory;
import cn.sth.shop.service.back.IItemServiceBack;
import cn.sth.shop.vo.Item;

import java.util.List;
import java.util.Set;

/**
 * ClassName:ItemServiceBackImpl
 * Package:cn.sth.shop.service.back.impl
 * Description:
 *
 * @Date:2020/1/12 17:10
 * Author:沙天慧
 */
public class ItemServiceBackImpl implements IItemServiceBack {
    private DataBaseConnection dbc=new DataBaseConnection();
    @Override
    public boolean insert(Item vo) throws Exception {
        try{
            return DAOFactory.getItemDAOInstance(this.dbc.getConnection()).doCreate(vo);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean update(Item vo) throws Exception {
        try{
            return DAOFactory.getItemDAOInstance(this.dbc.getConnection()).doUpdate(vo);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean delete(Set<Integer> ids) throws Exception {
        try{
            return DAOFactory.getItemDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public List<Item> list() throws Exception {
        try{
            return DAOFactory.getItemDAOInstance(this.dbc.getConnection()).findAll();
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }
}
