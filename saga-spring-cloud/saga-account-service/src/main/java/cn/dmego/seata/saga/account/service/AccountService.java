package cn.dmego.seata.saga.account.service;

/**
 * 帐户服务
 *
 * @author qiaoyan
 * @date 2022-12-05 15:21:33
 */
public interface AccountService {

    Boolean reduceBalance(Long userId, Integer amount) throws Exception;

    Boolean compensateBalance(Long userId, Integer amount) throws Exception;
}
