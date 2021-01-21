package cn.sth.shop.service.back;

import cn.sth.shop.vo.Item;

import java.util.List;
import java.util.Set;

/**
 * ClassName:IItemServiceBack
 * Package:cn.sth.shop.service.back
 * Description:
 *
 * @Date:2020/1/12 17:07
 * Author:沙天慧
 */
public interface IItemServiceBack  {
    public boolean insert(Item vo) throws Exception;
    public boolean update(Item vo) throws Exception;
    public boolean delete(Set<Integer> ids)throws Exception;
    public List<Item> list() throws Exception;
}
