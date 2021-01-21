package cn.sth.shop.dao.impl;

import cn.sth.shop.dao.ICityDAO;
import cn.sth.shop.util.dao.AbstractDAOImpl;
import cn.sth.shop.vo.City;
import cn.sth.shop.vo.Provincial;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:CityDAOImpl
 * Package:cn.sth.shop.dao.impl
 * Description:
 *
 * @Date:2020/1/25 22:01
 * Author:沙天慧
 */
public class CityDAOImpl extends AbstractDAOImpl implements ICityDAO {
    public CityDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public List<City> findAllByProvincial(Integer pid) throws SQLException {
        List<City> all=new ArrayList<City>();
        String sql="SELECT cid,title,pid FROM city WHERE pid=?";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setInt(1,pid);
        ResultSet rs=super.pstmt.executeQuery();
        while (rs.next()){
            City vo=new City();
            vo.setCid(rs.getInt(1));
            vo.setTitle(rs.getString(2));
            Provincial provincial=new Provincial();
            provincial.setPid(rs.getInt(3));
            vo.setProvincial(provincial);
            all.add(vo);
        }
        return all;
    }
}
