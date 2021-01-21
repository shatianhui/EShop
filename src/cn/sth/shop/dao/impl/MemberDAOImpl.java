package cn.sth.shop.dao.impl;

import cn.sth.shop.dao.IMemberDAO;
import cn.sth.shop.util.dao.AbstractDAOImpl;
import cn.sth.shop.vo.Member;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * ClassName:MemberDAOImpl
 * Package:cn.sth.shop.dao.impl
 * Description:
 *
 * @Date:2020/1/8 12:46
 * Author:沙天慧
 */
public class MemberDAOImpl extends AbstractDAOImpl implements IMemberDAO {
    public MemberDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean doCreate(Member vo) throws Exception {
        String sql="INSERT INTO `member`(mid,password,code,regdate,status,photo) VALUES(?,?,?,?,?,?)";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,vo.getMid());
        super.pstmt.setString(2,vo.getPassword());
        super.pstmt.setString(3,vo.getCode());
        super.pstmt.setTimestamp(4,new  Timestamp(vo.getRegdate().getTime()));
        super.pstmt.setInt(5,vo.getStatus());
        super.pstmt.setString(6,vo.getPhoto());
        return super.pstmt.executeUpdate()>0;
    }

    @Override
    public boolean doUpdate(Member vo) throws Exception {
        return false;
    }

    @Override
    public boolean doRemoveBatch(Set<String> ids) throws Exception {
        return false;
    }

    @Override
    public Member findById(String id) throws Exception {
        Member vo=null;
        String sql="SELECT mid,name,phone,password,address,code,status,regdate,photo FROM `member` WHERE mid=?";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,id);
        ResultSet rs=super.pstmt.executeQuery();
        while(rs.next()){
            vo=new Member();
            vo.setMid(rs.getString(1));
            vo.setName(rs.getString(2));
            vo.setPhone(rs.getString(3));
            vo.setPassword(rs.getString(4));
            vo.setAddress(rs.getString(5));
            vo.setCode(rs.getString(6));
            vo.setStatus(rs.getInt(7));
            vo.setRegdate(rs.getTimestamp(8));
            vo.setPhoto(rs.getString(9));
        }
        return vo;
    }
    @Override
    public Member findById2(String id) throws SQLException {
        Member vo=null;
        String sql="SELECT mid,name,phone,password,address,code,status,regdate,photo FROM `member` WHERE mid=?";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,id);
        ResultSet rs=super.pstmt.executeQuery();
        while(rs.next()){
            vo=new Member();
            vo.setMid(rs.getString(1));
            vo.setName(rs.getString(2));
            vo.setPhone(rs.getString(3));
            vo.setPassword(rs.getString(4));
            vo.setAddress(rs.getString(5));
            vo.setCode(rs.getString(6));
            vo.setStatus(rs.getInt(7));
            vo.setRegdate(rs.getTimestamp(8));
            vo.setPhoto(rs.getString(9));
        }
        return vo;
    }
    @Override
    public List<Member> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Member> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        List<Member> all=new ArrayList<Member>();
        String sql="SELECT mid,password,name,phone,address,code,status,regdate,photo FROM " +
                "`member` WHERE "+column+" LIKE ? LIMIT ?,?";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,"%"+keyWord+"%");
        super.pstmt.setInt(2,(currentPage-1)*lineSize);
        super.pstmt.setInt(3,lineSize);
        ResultSet rs=super.pstmt.executeQuery();
        while(rs.next()){
            Member vo=new Member();
            vo.setMid(rs.getString(1));
            vo.setPassword(rs.getString(2));
            vo.setName(rs.getString(3));
            vo.setPhone(rs.getString(4));
            vo.setAddress(rs.getString(5));
            vo.setCode(rs.getString(6));
            vo.setStatus(rs.getInt(7));
            vo.setRegdate(rs.getTimestamp(8));
            vo.setPhoto(rs.getString(9));
            all.add(vo);
        }
        return all;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        String sql="SELECT COUNT(*) FROM `member` WHERE "+column+" LIKE ?" ;
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,"%"+keyWord+"%");
        ResultSet rs=super.pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean findLogin(Member vo) throws Exception {
        boolean flag=false;
        String sql="SELECT photo FROM `member` WHERE mid=? AND password=? AND status=1";//用户状态为1才能登录
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,vo.getMid());
        super.pstmt.setString(2,vo.getPassword());
        ResultSet rs=super.pstmt.executeQuery();
        if(rs.next()){
            flag=true;
            vo.setPhoto(rs.getString(1));
        }
        return flag;
    }

    @Override
    public List<Member> findAllByStatus(Integer status, Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        List<Member> all=new ArrayList<Member>();
        String sql="SELECT mid,password,name,phone,address,code,status,regdate,photo FROM " +
                "`member` WHERE "+column+" LIKE ? AND status=? LIMIT ?,?";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,"%"+keyWord+"%");
        super.pstmt.setInt(2,status);
        super.pstmt.setInt(3,(currentPage-1)*lineSize);
        super.pstmt.setInt(4,lineSize);
        ResultSet rs=super.pstmt.executeQuery();
        while(rs.next()){
            Member vo=new Member();
            vo.setMid(rs.getString(1));
            vo.setPassword(rs.getString(2));
            vo.setName(rs.getString(3));
            vo.setPhone(rs.getString(4));
            vo.setAddress(rs.getString(5));
            vo.setCode(rs.getString(6));
            vo.setStatus(rs.getInt(7));
            vo.setRegdate(rs.getTimestamp(8));
            vo.setPhoto(rs.getString(9));
            all.add(vo);
        }
        return all;
    }

    @Override
    public Integer getAllCountByStatus(Integer status, String column, String keyWord) throws Exception {
        String sql="SELECT COUNT(*) FROM `member` WHERE "+column+" LIKE ? AND status=?";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,"%"+keyWord+"%");
        super.pstmt.setInt(2,status);
        ResultSet rs=super.pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean doUpdateStatus(Set<String> ids, Integer status) throws Exception {
        if (ids.size()==0){
            return false;
        }
        boolean flag=true;
        String sql="UPDATE `member` SET status=? WHERE mid=?";
        super.pstmt=super.conn.prepareStatement(sql);
        Iterator<String> iter=ids.iterator();
        while(iter.hasNext()){
            super.pstmt.setInt(1,status);
            super.pstmt.setString(2,iter.next());
            super.pstmt.addBatch();//增加到批处理操作
        }
        int result[]=super.pstmt.executeBatch();//执行批处理
        for(int i=0;i<result.length;i++){
            if(result[i]==0){//有数据未被更新
                flag=false;
            }
        }
        return flag;
    }

    @Override
    public boolean doUpdateMember(Member vo) throws Exception {
        String sql="UPDATE `member` SET name=?,phone=?,address=?,photo=? WHERE mid=?";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,vo.getName());
        super.pstmt.setString(2,vo.getPhone());
        super.pstmt.setString(3,vo.getAddress());
        super.pstmt.setString(4,vo.getPhoto());
        super.pstmt.setString(5,vo.getMid());
        return super.pstmt.executeUpdate()>0;
    }
}
