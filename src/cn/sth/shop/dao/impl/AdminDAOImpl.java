package cn.sth.shop.dao.impl;

import cn.sth.shop.dao.IAdminDAO;
import cn.sth.shop.util.dao.AbstractDAOImpl;
import cn.sth.shop.vo.Admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * ClassName:AdminDAOImpl
 * Package:cn.sth.shop.dao.impl
 * Description:
 *
 * @Date:2020/1/10 15:02
 * Author:沙天慧
 */
public class AdminDAOImpl extends AbstractDAOImpl implements IAdminDAO {
    public AdminDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean findLogin(Admin vo) throws Exception {
        boolean flag=false;
        String sql="SELECT lastdate FROM admin WHERE aid=? and password=?";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,vo.getAid());
        super.pstmt.setString(2,vo.getPassword());
        ResultSet rs=super.pstmt.executeQuery();
        if(rs.next()){
            flag=true;
            vo.setLastdate(rs.getTimestamp(1));
        }
        return flag;
    }

    @Override
    public boolean doUpdateLastdate(String aid) throws Exception {
        String sql="UPDATE admin SET lastdate=? WHERE aid=?";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setTimestamp(1,new Timestamp(new java.util.Date().getTime()));
        super.pstmt.setString(2,aid);
        return super.pstmt.executeUpdate()>0;
    }

    @Override
    public boolean doCreate(Admin vo) throws Exception {
        return false;
    }

    @Override
    public boolean doUpdate(Admin vo) throws Exception {
        return false;
    }

    @Override
    public boolean doRemoveBatch(Set<String> ids) throws Exception {
        return false;
    }

    @Override
    public Admin findById(String id) throws Exception {
        return null;
    }

    @Override
    public List<Admin> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Admin> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        return null;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return null;
    }
}
