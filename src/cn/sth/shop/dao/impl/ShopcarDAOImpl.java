package cn.sth.shop.dao.impl;

import cn.sth.shop.dao.IShopcarDAO;
import cn.sth.shop.util.dao.AbstractDAOImpl;
import cn.sth.shop.vo.Shopcar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * ClassName:ShopcarDAOImpl
 * Package:cn.sth.shop.dao.impl
 * Description:
 *
 * @Date:2020/1/19 15:59
 * Author:沙天慧
 */
public class ShopcarDAOImpl extends AbstractDAOImpl implements IShopcarDAO {
    public ShopcarDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean doUpdateAmount(String mid, Integer gid) throws Exception {
        String sql="UPDATE shopcar SET amount=amount+1 WHERE mid=? and gid=?";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,mid);
        super.pstmt.setInt(2,gid);
        return super.pstmt.executeUpdate()>0;
    }

    @Override
    public Shopcar findByMemberAndGoods(String mid, Integer gid) throws Exception {
        Shopcar vo=null;
        String sql="SELECT amount FROM shopcar WHERE mid=? AND gid=?";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,mid);
        super.pstmt.setInt(2,gid);
        ResultSet rs=super.pstmt.executeQuery();
        if(rs.next()){
            vo=new Shopcar();
            vo.setAmount(rs.getInt(1));;
        }
        return vo;
    }

    @Override
    public boolean doRemoveByMember(String mid) throws SQLException {
        String sql="DELETE FROM shopcar WHERE mid=?";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,mid);
        return super.pstmt.executeUpdate()>0;
    }

    @Override
    public boolean doCreateBatch(Set<Shopcar> vos) throws Exception {
        boolean flag=true;
        String sql="INSERT INTO shopcar(gid,mid,amount) VALUES(?,?,?)";
        super.pstmt=super.conn.prepareStatement(sql);
        Iterator<Shopcar> iter=vos.iterator();
        while(iter.hasNext()){
            Shopcar vo=iter.next();
            super.pstmt.setInt(1,vo.getGoods().getGid());
            super.pstmt.setString(2,vo.getMember().getMid());
            super.pstmt.setInt(3,vo.getAmount());
            super.pstmt.addBatch();
        }
        int result[]=super.pstmt.executeBatch();
        for(int i=0;i<result.length;i++){
            if(result[i]==0){
                flag=false;
            }
        }
        return flag;
    }

    @Override
    public boolean doRemoveByMemberAndGoods(String mid, Set<Integer> gid) throws Exception {
        StringBuffer sql=new StringBuffer("DELETE FROM shopcar WHERE mid=? AND gid IN(");
        Iterator<Integer> iter=gid.iterator();
        while (iter.hasNext()){
            sql.append(iter.next()+",");
        }
        sql.delete(sql.length()-1,sql.length());
        sql.append(")");
        super.pstmt=super.conn.prepareStatement(sql.toString());
        super.pstmt.setString(1,mid);
        return super.pstmt.executeUpdate()>0;
    }

    @Override
    public Map<Integer, Integer> findAllByMember(String mid) throws SQLException {
        Map<Integer,Integer> map=new HashMap<Integer, Integer>();
        String sql="SELECT gid,amount FROM shopcar WHERE mid=?";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,mid);
        ResultSet rs=super.pstmt.executeQuery();
        while (rs.next()){
            map.put(rs.getInt(1),rs.getInt(2));
        }
        return map;
    }

    @Override
    public boolean doCreate(Shopcar vo) throws Exception {
        String sql="INSERT INTO shopcar(gid,mid,amount) VALUES(?,?,?)";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setInt(1,vo.getGoods().getGid());
        super.pstmt.setString(2,vo.getMember().getMid());
        super.pstmt.setInt(3,vo.getAmount());
        return super.pstmt.executeUpdate()>0;
    }

    @Override
    public boolean doUpdate(Shopcar vo) throws Exception {
        return false;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    @Override
    public Shopcar findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<Shopcar> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Shopcar> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        return null;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return null;
    }
}