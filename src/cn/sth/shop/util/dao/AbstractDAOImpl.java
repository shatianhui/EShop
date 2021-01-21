package cn.sth.shop.util.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * ClassName:AbstractDAOImpl
 * Package:cn.sth.shop.util.dao
 * Description:
 *
 * @Date:2020/1/8 12:48
 * Author:沙天慧
 */
public abstract class AbstractDAOImpl  {
    protected Connection conn;
    protected PreparedStatement pstmt;
    public AbstractDAOImpl(Connection conn){
        this.conn=conn;
    }

    /**
     * 实现批量删除操作，彻底删除功能
     * @param table 表名称
     * @param column 列名称
     * @param ids 所有的id数据，使用set可以避免重复
     * @return 删除成功返回true，删除失败返回false
     */
    public boolean removeHandle(String table, String column, Set<?> ids) throws Exception{
        if(ids.size()==0) {//没有数据
            return false;
        }
        StringBuffer buf=new StringBuffer("DELETE FROM ");
        buf.append(table).append(" WHERE ").append(column).append(" in (");
        Iterator<?> iter=ids.iterator();
        while(iter.hasNext()){
            buf.append(iter.next()+",");
        }
        buf.delete(buf.length()-1,buf.length());
        buf.append(")");
        this.pstmt=this.conn.prepareStatement(buf.toString());
        return this.pstmt.executeUpdate()==ids.size();
    }

    /**
     * 统计数据量
     * @param table 要查询的表
     * @param column 模糊查询的列
     * @param keyWord 关键字
     * @return 查询的数据量
     * @throws Exception
     */
    public Integer countHandel(String table,String column,String keyWord) throws Exception{
        StringBuffer sql=new StringBuffer("SELECT COUNT(*) FROM ");
        sql.append(table).append(" WHERE ").append(column).append(" LIKE ?");
        this.pstmt=this.conn.prepareStatement(sql.toString());
        this.pstmt.setString(1,"% "+keyWord+" %");
        ResultSet rs=this.pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }

    public Set<String> photoHandle(String table,String column,Set<?> ids,String photoColumn)throws Exception{
        Set<String> all=new HashSet<String>();
        StringBuffer sql=new StringBuffer();
        sql.append("SELECT ").append(photoColumn).append(" FROM ").append(table).append(" WHERE ").append(column).append(" IN(");
        Iterator<?> iter=ids.iterator();
        while(iter.hasNext()){
            sql.append(iter.next()+",");
        }
        sql.delete(sql.length()-1,sql.length());
        sql.append(")");
        sql.append(" AND ").append(photoColumn).append(" <>'nophoto.jpg'");
        this.pstmt=this.conn.prepareStatement(sql.toString());
        ResultSet rs=this.pstmt.executeQuery();
        while(rs.next()){
            all.add(rs.getString(1));
        }
        return all;
    }
}
