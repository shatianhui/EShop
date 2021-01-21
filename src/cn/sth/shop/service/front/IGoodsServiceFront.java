package cn.sth.shop.service.front;

import cn.sth.shop.vo.Goods;

import java.util.Map;

/**
 * ClassName:IGoodsServiceFront
 * Package:cn.sth.shop.service.front
 * Description:
 *
 * @Date:2020/1/17 14:00
 * Author:沙天慧
 */
public interface IGoodsServiceFront {
    public Map<String,Object> list(int currentPage,int lineSize,String column,String keyword) throws Exception;
    public Map<String,Object> listByItem(int iid,int currentPage,int lineSize,String column,String keyword) throws Exception;

    /**
     * 显示商品的完整信息
     * @param gid
     * @return
     * @throws Exception
     */
    public Goods show(int gid) throws Exception;
}
