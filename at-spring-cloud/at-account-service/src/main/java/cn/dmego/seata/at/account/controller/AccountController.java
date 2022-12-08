package cn.dmego.seata.at.account.controller;


import cn.dmego.seata.at.account.service.AccountService;
import cn.dmego.seata.common.dto.AccountDTO;
import cn.dmego.seata.common.dto.ReturnResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 帐户控制器
 *
 * @author qiaoyan
 * @date 2022-11-24 15:40:36
 */
@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {

    @Resource
    private AccountService accountService;

    /**
     * 减余额
     * @param accountDTO 账户dto
     * @return {@code ReturnResult<Boolean>}
     * @throws Exception 异常
     */
    @PostMapping("/reduce-balance")
    public ReturnResult<Boolean> reduceBalance(@RequestBody AccountDTO accountDTO) throws Exception {
        log.info("[reduceBalance] 收到扣减余额请求, 用户:{}, 金额:{}", accountDTO.getUserId(), accountDTO.getAmount());
        return ReturnResult.success(accountService.reduceBalance(accountDTO.getUserId(), accountDTO.getAmount()));
    }

}
