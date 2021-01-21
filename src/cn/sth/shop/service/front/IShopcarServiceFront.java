package cn.sth.shop.service.front;

import cn.sth.shop.vo.Goods;
import cn.sth.shop.vo.Shopcar;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ClassName:IShopcarServiceFront
 * Package:cn.sth.shop.service.front
 * Description:
 *
 * @Date:2020/1/18 15:39
 * Author:沙天慧
 */
public interface IShopcarServiceFront {
    /**
     * 购物车数据的增加
     * 查询增加的购物车数据是否存在，如果存在，保存数量自增，否则保存一个新的内容，并且数量为1
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean addCar(Shopcar vo) throws Exception;

    /**
     * 根据用户id，查询出所有的购买商品
     * @param mid
     * @return
     * @throws Exception
     */
    public Map<String,Object> listCar(String mid) throws Exception;

    /**
     * 删除指定一个用户和商品的信息
     * @param mid
     * @param gid
     * @return
     * @throws Exception
     */
    public boolean deleteCar(String mid,Set<Integer> gid) throws Exception;

    /**
     * 更新购买的所有商品信息
     * @param mid
     * @param vos
     * @return
     * @throws Exception
     */
    public boolean update(String mid,Set<Shopcar> vos) throws Exception;
}
