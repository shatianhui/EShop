package cn.sth.shop.service.front;

import cn.sth.shop.vo.Provincial;

import java.util.List;

/**
 * ClassName:IProvincialServiceFront
 * Package:cn.sth.shop.service.front
 * Description:
 *
 * @Date:2020/1/26 10:18
 * Author:沙天慧
 */
public interface IProvincialServiceFront {
    public List<Provincial> list() throws Exception;
}
