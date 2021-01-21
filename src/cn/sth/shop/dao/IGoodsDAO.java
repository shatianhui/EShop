package cn.sth.shop.dao;

import cn.sth.shop.vo.Goods;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * ClassName:IGoodsDAO
 * Package:cn.sth.shop.dao
 * Description:
 *
 * @Date:2020/1/13 14:49
 * Author:沙天慧
 */
public interface IGoodsDAO extends IDAO<Integer, Goods> {
    public List<Goods> findAllByStatus(Integer status,Integer currentPage,Integer lineSize,String column,String keyword) throws Exception;
    public Integer getAllCountByStatus(Integer status,String column,String keyword) throws Exception;
    public boolean doUpdateStatus(Set<Integer> ids, Integer status) throws Exception;
    public Set<String> findAllByPhoto(Set<Integer> id) throws Exception;

    /**
     * 根据商品的分类和状态进行商品列表显示
     * @param iid
     * @param status
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyword
     * @return
     * @throws Exception
     */
    public List<Goods> findAllByItem(Integer iid,Integer status,Integer currentPage,Integer lineSize,String column,String keyword) throws Exception;
    public Integer getAllCountByItem(Integer iid,Integer status,String column,String keyword) throws Exception;

    /**
     * 更新访问次数
     * @param id
     * @return
     * @throws Exception
     */
    public boolean doUpdateBrowse(Integer id) throws Exception;

    /**
     * 查询指定商品编号的所有信息
     * @param ids
     * @return
     * @throws Exception
     */
    public List<Goods> findAllByGid(Set<Integer> ids) throws SQLException;

    /**
     * 商品库存量的变更
     * @param gid
     * @param num
     * @return
     * @throws Exception
     */
    public boolean doUpdateByAmount(Integer gid,Integer num) throws SQLException;
}
