package cn.sth.shop.service.front.impl;

import cn.sth.shop.dao.IProvincialDAO;
import cn.sth.shop.dbc.DataBaseConnection;
import cn.sth.shop.factory.DAOFactory;
import cn.sth.shop.service.front.IProvincialServiceFront;
import cn.sth.shop.vo.Provincial;

import java.util.List;

/**
 * ClassName:ProvincialServiceFrontImpl
 * Package:cn.sth.shop.service.front.impl
 * Description:
 *
 * @Date:2020/1/26 10:22
 * Author:沙天慧
 */
public class ProvincialServiceFrontImpl implements IProvincialServiceFront {
    private DataBaseConnection dbc=new DataBaseConnection();
    @Override
    public List<Provincial> list() throws Exception {
        try{
            return DAOFactory.getProvincialInstance(this.dbc.getConnection()).findAll();
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }
}
