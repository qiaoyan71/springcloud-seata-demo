package cn.dmego.seata.xa.business.controller;

import cn.dmego.seata.xa.business.serivce.BusinessService;
import cn.dmego.seata.common.dto.BusinessDTO;
import cn.dmego.seata.common.dto.ReturnResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * BusinessController
 *
 * @author qiaoyan
 * @date 2022-11-24 11:07:45
 */
@RestController
@RequestMapping("/at")
public class BusinessController {
    @Resource
    private BusinessService businessService;

    @PostMapping("/buy")
    public ReturnResult<String> handleBusiness(@RequestBody BusinessDTO businessDTO){
        return ReturnResult.success(businessService.handleBusiness(businessDTO));
    }
}
