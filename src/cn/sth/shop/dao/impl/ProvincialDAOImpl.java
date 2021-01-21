package cn.sth.shop.dao.impl;

import cn.sth.shop.dao.IProvincialDAO;
import cn.sth.shop.util.dao.AbstractDAOImpl;
import cn.sth.shop.vo.Provincial;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:ProvincialDAOImpl
 * Package:cn.sth.shop.dao.impl
 * Description:
 *
 * @Date:2020/1/25 22:01
 * Author:沙天慧
 */
public class ProvincialDAOImpl extends AbstractDAOImpl implements IProvincialDAO {
    public ProvincialDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public List<Provincial> findAll() throws SQLException {
        List<Provincial> all=new ArrayList<Provincial>();
        String sql="SELECT pid,title FROM provincial";
        super.pstmt=super.conn.prepareStatement(sql);
        ResultSet rs=super.pstmt.executeQuery();
        while (rs.next()){
            Provincial vo=new Provincial();
            vo.setPid(rs.getInt(1));
            vo.setTitle(rs.getString(2));
            all.add(vo);
        }
        return all;
    }
}
