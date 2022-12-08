package cn.dmego.seata.saga.account.controller;

import cn.dmego.seata.saga.account.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * AccountController
 *
 * @author qiaoyan
 * @date 2022-12-05 15:21:14
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @RequestMapping("/reduceBalance")
    Boolean reduceBalance(@RequestParam("userId") Long userId, @RequestParam("amount") Integer amount) throws Exception {
        return accountService.reduceBalance(userId, amount);
    }

    @RequestMapping("/compensateBalance")
    Boolean compensateBalance(@RequestParam("userId") Long userId, @RequestParam("amount") Integer amount) throws Exception {
        return accountService.compensateBalance(userId, amount);
    }
}
