package cn.sth.shop.dao;

import cn.sth.shop.vo.Shopcar;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

/**
 * ClassName:IShopcarDAO
 * Package:cn.sth.shop.dao
 * Description:
 *
 * @Date:2020/1/19 15:48
 * Author:沙天慧
 */
public interface IShopcarDAO extends IDAO<Integer, Shopcar> {
    /**
     * 商品重复时，amount++
     * @param mid
     * @param gid
     * @return
     * @throws Exception
     */
    public boolean doUpdateAmount(String mid,Integer gid) throws Exception;

    /**
     * 根据用户id和商品编号查询购物车信息
     * @param mid
     * @param gid
     * @return
     * @throws Exception
     */
    public Shopcar findByMemberAndGoods(String mid,Integer gid) throws Exception;

    /**
     * 清除用户的购物车
     * @param mid
     * @return
     * @throws Exception
     */
    public boolean doRemoveByMember(String mid) throws SQLException;

    /**
     * 批量保存新的购物车数据
     * @param vos
     * @return
     * @throws Exception
     */
    public boolean doCreateBatch(Set<Shopcar> vos) throws Exception;

    /**
     * 删除一个商品的购物车记录
     * @param mid
     * @param gid
     * @return
     * @throws Exception
     */
    public boolean doRemoveByMemberAndGoods(String mid,Set<Integer> gid) throws Exception;

    /**
     * 一个用户购买的所有商品的信息
     * @param mid
     * @return
     * @throws Exception
     */
    public Map<Integer,Integer> findAllByMember(String mid) throws SQLException;
}
