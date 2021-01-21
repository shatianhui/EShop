package cn.sth.shop.service.front.impl;

import cn.sth.shop.dbc.DataBaseConnection;
import cn.sth.shop.factory.DAOFactory;
import cn.sth.shop.service.front.ICityServiceFront;
import cn.sth.shop.vo.City;

import java.util.List;

/**
 * ClassName:CityServiceFrontImpl
 * Package:cn.sth.shop.service.front.impl
 * Description:
 *
 * @Date:2020/1/26 10:22
 * Author:沙天慧
 */
public class CityServiceFrontImpl implements ICityServiceFront {
    private DataBaseConnection dbc=new DataBaseConnection();
    @Override
    public List<City> listByProvincial(int pid) throws Exception {
        try {
            return DAOFactory.getCityInstance(this.dbc.getConnection()).findAllByProvincial(pid);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }
}
