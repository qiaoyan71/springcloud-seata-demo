package cn.dmego.seata.saga.business.controller;

import cn.dmego.seata.common.dto.BusinessDTO;
import cn.dmego.seata.saga.business.service.BusinessService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 业务controller
 *
 * @author qiaoyan
 * @date 2022-12-05 10:55:01
 */
@RestController
@RequestMapping("/saga")
public class BusinessController {

    @Resource
    BusinessService businessService;

    @PostMapping("/buy")
    public String handlerBusiness(@RequestBody BusinessDTO businessDTO) {
        return businessService.handlerBusiness(businessDTO);
    }
}
