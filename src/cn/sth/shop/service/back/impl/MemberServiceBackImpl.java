package cn.sth.shop.service.back.impl;

import cn.sth.shop.dbc.DataBaseConnection;
import cn.sth.shop.factory.DAOFactory;
import cn.sth.shop.service.back.IMemberServiceBack;
import cn.sth.shop.vo.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ClassName:MemberServiceBackImpl
 * Package:cn.sth.shop.service.back.impl
 * Description:
 *
 * @Date:2020/1/11 14:12
 * Author:沙天慧
 */
public class MemberServiceBackImpl implements IMemberServiceBack {
    private DataBaseConnection dbc=new DataBaseConnection();
    @Override
    public Map<String, Object> list(String column, String keyword, int currentPage, int lineSize) throws Exception {
        try{
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("allMembers", DAOFactory.getMemberDAOInstance(this.dbc.getConnection()).findAllSplit(currentPage,lineSize,column,keyword));
            map.put("memberCount",DAOFactory.getMemberDAOInstance(this.dbc.getConnection()).getAllCount(column,keyword));
            return map;
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Map<String, Object> listByStatus(int status, String column, String keyword, int currentPage, int lineSize) throws Exception {
        try{
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("allMembers", DAOFactory.getMemberDAOInstance(this.dbc.getConnection()).findAllByStatus(status,currentPage,lineSize,column,keyword));
            map.put("memberCount",DAOFactory.getMemberDAOInstance(this.dbc.getConnection()).getAllCountByStatus(status,column,keyword));
            return map;
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean updateActive(Set<String> ids) throws Exception {
        try{
            return DAOFactory.getMemberDAOInstance(this.dbc.getConnection()).doUpdateStatus(ids,1);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean updateLock(Set<String> ids) throws Exception {
        try{
            return DAOFactory.getMemberDAOInstance(this.dbc.getConnection()).doUpdateStatus(ids,0);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Member show(String id) throws Exception {
        try{
            return DAOFactory.getMemberDAOInstance(this.dbc.getConnection()).findById(id);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }
}
