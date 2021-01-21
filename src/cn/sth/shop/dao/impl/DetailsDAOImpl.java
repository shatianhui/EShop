package cn.sth.shop.dao.impl;

import cn.sth.shop.dao.IDetailsDAO;
import cn.sth.shop.util.dao.AbstractDAOImpl;
import cn.sth.shop.vo.Details;
import cn.sth.shop.vo.Goods;
import cn.sth.shop.vo.Orders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * ClassName:DatailsDAOImpl
 * Package:cn.sth.shop.dao.impl
 * Description:
 *
 * @Date:2020/1/21 14:50
 * Author:沙天慧
 */
public class DetailsDAOImpl extends AbstractDAOImpl implements IDetailsDAO {
    public DetailsDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean doCreateBath(List<Details> vos) throws SQLException {
        String sql="INSERT INTO details(oid,gid,title,price,amount) VALUES(?,?,?,?,?)";
        super.pstmt=super.conn.prepareStatement(sql);
        Iterator<Details> iter=vos.iterator();
        while (iter.hasNext()){
            Details vo=iter.next();
            super.pstmt.setInt(1,vo.getOrders().getOid());
            super.pstmt.setInt(2,vo.getGoods().getGid());
            super.pstmt.setString(3,vo.getTitle());
            super.pstmt.setDouble(4,vo.getPrice());
            super.pstmt.setInt(5,vo.getAmount());
            super.pstmt.addBatch();
        }
        int result[]=super.pstmt.executeBatch();
        boolean flag=true;
        for(int i=0;i<result.length;i++){
            if (result[i]==0){
                flag=false;
                break;
            }
        }
        return flag;
    }

    @Override
    public List<Details> findAllByOrders(Integer oid) throws Exception {
        List<Details> all=new ArrayList<Details>();
        String sql="SELECT odid,oid,gid,title,price,amount FROM details WHERE oid=?";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setInt(1,oid);
        ResultSet rs=super.pstmt.executeQuery();
        while (rs.next()){
            Details vo=new Details();
            vo.setOdid(rs.getInt(1));
            Orders orders=new Orders();
            orders.setOid(rs.getInt(2));
            vo.setOrders(orders);
            Goods goods=new Goods();
            goods.setGid(rs.getInt(3));
            vo.setGoods(goods);
            vo.setTitle(rs.getString(4));
            vo.setPrice(rs.getDouble(5));
            vo.setAmount(rs.getInt(6));
            all.add(vo);
        }
        return all;
    }

    @Override
    public boolean doCreate(Details vo) throws Exception {
        return false;
    }

    @Override
    public boolean doUpdate(Details vo) throws Exception {
        return false;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    @Override
    public Details findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<Details> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Details> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        return null;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return null;
    }
}
