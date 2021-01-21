package cn.sth.shop.dao.impl;

import cn.sth.shop.dao.IOrdersDAO;
import cn.sth.shop.util.dao.AbstractDAOImpl;
import cn.sth.shop.vo.Member;
import cn.sth.shop.vo.Orders;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * ClassName:OrdersDAOImpl
 * Package:cn.sth.shop.dao.impl
 * Description:
 *
 * @Date:2020/1/21 14:26
 * Author:沙天慧
 */
public class OrdersDAOImpl extends AbstractDAOImpl implements IOrdersDAO {
    public OrdersDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public Integer findLastInsertId() throws SQLException {
        String sql="SELECT LAST_INSERT_ID()";
        super.pstmt=super.conn.prepareStatement(sql);
        ResultSet rs=super.pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean doCreateOrders(Orders vo) throws SQLException {
        String sql="INSERT INTO Orders(mid,name,phone,address,credate,pay) VALUES(?,?,?,?,?,?)";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,vo.getMember().getMid());
        super.pstmt.setString(2,vo.getName());
        super.pstmt.setString(3,vo.getPhone());
        super.pstmt.setString(4,vo.getAddress());
        super.pstmt.setTimestamp(5, new java.sql.Timestamp(vo.getCredate().getTime()));
        super.pstmt.setDouble(6,vo.getPay());
        return super.pstmt.executeUpdate()>0;
    }

    @Override
    public List<Orders> findAllByMember(String mid, Integer currentPage, Integer lineSize) throws Exception {
        List<Orders> all=new ArrayList<>();
        String sql="SELECT oid,mid,name,phone,address,credate,pay FROM orders WHERE mid=? LIMIT ?,?";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,mid);
        super.pstmt.setInt(2,(currentPage-1)*lineSize);
        super.pstmt.setInt(3,lineSize);
        ResultSet rs=super.pstmt.executeQuery();
        while (rs.next()){
            Orders vo=new Orders();
            vo.setOid(rs.getInt(1));
            Member member=new Member();
            member.setMid(rs.getString(2));
            vo.setMember(member);
            vo.setName(rs.getString(3));
            vo.setPhone(rs.getString(4));
            vo.setAddress(rs.getString(5));
            vo.setCredate(rs.getTimestamp(6));
            vo.setPay(rs.getDouble(7));
            all.add(vo);
        }
        return all;
    }

    @Override
    public Integer getAllCountByMember(String mid) throws Exception {
        String sql="SELECT COUNT(*) FROM orders WHERE mid=?";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,mid);
        ResultSet rs=super.pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public Orders findByIdAndByMember(String mid, Integer oid) throws Exception {
        Orders vo=null;
        String sql="SELECT oid,mid,name,phone,address,credate,pay FROM orders WHERE mid=? AND oid=?";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,mid);
        super.pstmt.setInt(2,oid);
        ResultSet rs=super.pstmt.executeQuery();
        while (rs.next()){
            vo=new Orders();
            vo.setOid(rs.getInt(1));
            Member member=new Member();
            member.setMid(rs.getString(2));
            vo.setMember(member);
            vo.setName(rs.getString(3));
            vo.setPhone(rs.getString(4));
            vo.setAddress(rs.getString(5));
            vo.setCredate(rs.getTimestamp(6));
            vo.setPay(rs.getDouble(7));
        }
        return vo;
    }

    @Override
    public boolean doCreate(Orders vo) throws Exception {
        return false;
    }

    @Override
    public boolean doUpdate(Orders vo) throws Exception {
        return false;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    @Override
    public Orders findById(Integer id) throws Exception {
        Orders vo=null;
        String sql="SELECT oid,mid,name,phone,address,credate,pay FROM orders WHERE oid=?";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setInt(1,id);
        ResultSet rs=super.pstmt.executeQuery();
        while (rs.next()){
            vo=new Orders();
            vo.setOid(rs.getInt(1));
            Member member=new Member();
            member.setMid(rs.getString(2));
            vo.setMember(member);
            vo.setName(rs.getString(3));
            vo.setPhone(rs.getString(4));
            vo.setAddress(rs.getString(5));
            vo.setCredate(rs.getTimestamp(6));
            vo.setPay(rs.getDouble(7));
        }
        return vo;
    }

    @Override
    public List<Orders> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Orders> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        List<Orders> all=new ArrayList<>();
        String sql="SELECT oid,mid,name,phone,address,credate,pay FROM orders WHERE "+column+" LIKE ? LIMIT ?,?";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,"%"+keyWord+"%");
        super.pstmt.setInt(2,(currentPage-1)*lineSize);
        super.pstmt.setInt(3,lineSize);
        ResultSet rs=super.pstmt.executeQuery();
        while (rs.next()){
            Orders vo=new Orders();
            vo.setOid(rs.getInt(1));
            Member member=new Member();
            member.setMid(rs.getString(2));
            vo.setMember(member);
            vo.setName(rs.getString(3));
            vo.setPhone(rs.getString(4));
            vo.setAddress(rs.getString(5));
            vo.setCredate(rs.getTimestamp(6));
            vo.setPay(rs.getDouble(7));
            all.add(vo);
        }
        return all;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        String sql="SELECT COUNT(*) FROM orders WHERE "+column+" LIKE ?";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,"%"+keyWord+"%");
        ResultSet rs=super.pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }
}
