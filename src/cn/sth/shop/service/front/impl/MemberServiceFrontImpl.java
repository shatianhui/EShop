package cn.sth.shop.service.front.impl;

import cn.sth.shop.dbc.DataBaseConnection;
import cn.sth.shop.factory.DAOFactory;
import cn.sth.shop.factory.ServiceFrontFactory;
import cn.sth.shop.service.front.IMemberServiceFront;
import cn.sth.shop.vo.Member;

/**
 * ClassName:MemberServiceFrontImpl
 * Package:cn.sth.shop.service.front.impl
 * Description:
 *
 * @Date:2020/1/8 13:50
 * Author:沙天慧
 */
public class MemberServiceFrontImpl implements IMemberServiceFront {
    private DataBaseConnection dbc=new DataBaseConnection();
    @Override
    public boolean register(Member vo) throws Exception {
        try{
            if(DAOFactory.getMemberDAOInstance(this.dbc.getConnection()).findById(vo.getMid())==null) {
                return DAOFactory.getMemberDAOInstance(this.dbc.getConnection()).doCreate(vo);
            }
            return false;
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean login(Member vo) throws Exception {
        try{
            return DAOFactory.getMemberDAOInstance(this.dbc.getConnection()).findLogin(vo);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Member updatePre(String mid) throws Exception {
        try {
            return DAOFactory.getMemberDAOInstance(this.dbc.getConnection()).findById(mid);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean update(Member vo) throws Exception {
        try {
            return DAOFactory.getMemberDAOInstance(this.dbc.getConnection()).doUpdateMember(vo);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean checkMid(String mid) throws Exception {
        try {
            return DAOFactory.getMemberDAOInstance(this.dbc.getConnection()).findById(mid)==null;
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }
}
