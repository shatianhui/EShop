package cn.sth.shop.service.back;

import cn.sth.shop.vo.Goods;

import java.util.Map;
import java.util.Set;

/**
 * ClassName:IGoodsServiceBack
 * Package:cn.sth.shop.service.back
 * Description:
 *
 * @Date:2020/1/13 15:02
 * Author:沙天慧
 */
public interface IGoodsServiceBack {
    /**
     * 商品增加前的数据查询操作,查询出所有的商品类型
     * @return
     * @throws Exception
     */
    public Map<String,Object> insertPre() throws Exception;
    public boolean insert(Goods vo) throws Exception;
    public Map<String,Object> list(int currentPage,int lineSize,String column,String keyword) throws Exception;
    public Map<String,Object> listStatus(int status,int currentPage,int lineSize,String column,String keyword) throws Exception;
    public boolean updateUp(Set<Integer> ids) throws Exception;
    public boolean updateDown(Set<Integer> ids) throws Exception;
    public boolean updateDelete(Set<Integer> ids) throws Exception;

    /**
     * 商品修改前的查询操作
     * @param gid
     * @return
     * @throws Exception
     */
    public Map<String,Object> updatePre(int gid) throws Exception;
    public boolean update(Goods vo) throws Exception;

    /**
     * 实现数据的批量删除
     * @param ids
     * @return
     * @throws Exception
     */
    public Map<String,Object> deleteAll(Set<Integer> ids)throws Exception;
    public boolean delete(Set<Integer> ids) throws Exception;
}
