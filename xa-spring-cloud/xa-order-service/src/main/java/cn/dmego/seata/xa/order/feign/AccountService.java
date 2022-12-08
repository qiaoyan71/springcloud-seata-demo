package cn.dmego.seata.xa.order.feign;


import cn.dmego.seata.common.dto.AccountDTO;
import cn.dmego.seata.common.dto.ReturnResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Feign 客户端
 *
 * @author qiaoyan
 * @date 2022-11-24 15:36:35
 */
@FeignClient(name = "xa-account-service")
@RequestMapping("/account")
public interface AccountService {

    @PostMapping("/reduce-balance")
    ReturnResult<Boolean> reduceBalance(@RequestBody AccountDTO accountDTO);

}
