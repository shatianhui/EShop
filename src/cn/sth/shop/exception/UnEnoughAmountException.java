package cn.sth.shop.exception;

/**
 * ClassName:UnEnoughAmountException
 * Package:cn.sth.shop.exception
 * Description:
 *
 * @Date:2020/1/21 13:49
 * Author:沙天慧
 */
public class UnEnoughAmountException extends Exception {
    public UnEnoughAmountException(String msg){
        super(msg);
    }
}
