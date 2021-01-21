package cn.sth.shop.service.back.impl;

import cn.sth.shop.dbc.DataBaseConnection;
import cn.sth.shop.factory.DAOFactory;
import cn.sth.shop.service.back.IAdminServiceBack;
import cn.sth.shop.vo.Admin;

/**
 * ClassName:AdminServiceBackImpl
 * Package:cn.sth.shop.service.back.impl
 * Description:
 *
 * @Date:2020/1/10 15:19
 * Author:沙天慧
 */
public class AdminServiceBackImpl implements IAdminServiceBack {
    DataBaseConnection dbc=new DataBaseConnection();
    @Override
    public boolean login(Admin vo) throws Exception {
        try{
            if (DAOFactory.getAdminDAOInstance(this.dbc.getConnection()).findLogin(vo)){
                return DAOFactory.getAdminDAOInstance(this.dbc.getConnection()).doUpdateLastdate(vo.getAid());
            }
        }catch (Exception e){
            throw  e;
        }finally {
            this.dbc.close();
        }
        return false;
    }
}
