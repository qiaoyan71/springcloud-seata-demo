package cn.dmego.seata.saga.account.service.impl;

import cn.dmego.seata.saga.account.dao.AccountDao;
import cn.dmego.seata.saga.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * AccountServiceImpl
 *
 * @author dmego
 * @date 2021/3/31 10:46
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean reduceBalance(Long userId, Integer amount) throws Exception {
        log.info("[reduceBalance] 开始扣减余额, userId:{}, amount: {}", userId, amount);

        // 检查余额
        checkBalance(userId, amount);

        int result = accountDao.reduceBalance(userId, amount);
        if(result == 0){
            log.warn("[reduceBalance] 扣减余额失败, userId:{}, amount: {} ", userId, amount);
            return false;
        }
        log.info("[reduceBalance] 扣减余额成功, userId:{}, amount: {}", userId, amount);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean compensateBalance(Long userId, Integer amount) throws Exception {
        log.info("[compensateBalance] 开始回滚余额, userId:{}, amount: {}", userId, amount);

        int result = accountDao.compensateBalance(userId, amount);
        if(result == 0){
            log.warn("[compensateBalance] 回滚余额失败, userId:{}, amount: {}", userId, amount);
            return false;
        }
        log.info("[compensateBalance] 回滚余额成功, userId:{}, amount: {}", userId, amount);
        return true;
    }

    private void checkBalance(Long userId, Integer price) throws Exception {
        log.info("[checkBalance] 检查用户 {} 余额", userId);
        Integer balance = accountDao.getBalance(userId);
        if (balance < price) {
            log.warn("[checkBalance] 用户 {} 余额不足，当前余额:{}", userId, balance);
            throw new Exception("余额不足");
        }
    }
}
