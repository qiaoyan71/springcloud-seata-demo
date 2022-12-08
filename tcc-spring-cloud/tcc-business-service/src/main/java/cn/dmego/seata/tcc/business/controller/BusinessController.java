package cn.dmego.seata.tcc.business.controller;

import cn.dmego.seata.common.dto.BusinessDTO;
import cn.dmego.seata.common.dto.ReturnResult;
import cn.dmego.seata.tcc.business.service.BusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @className: BusinessController
 *
 * @description: 业务服务
 * @author: ZengKai<dmeago@gmail.com>
 * @date: 2020/12/6 17:24
 **/
@RestController
@RequestMapping("/tcc")
@Slf4j
public class BusinessController {

    @Resource
    private BusinessService businessService;

    @PostMapping("/buy")
    public ReturnResult<String> handleBusiness(@RequestBody BusinessDTO businessDTO){
        return ReturnResult.success(businessService.handleBusiness(businessDTO));
    }
}
