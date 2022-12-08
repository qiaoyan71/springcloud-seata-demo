package cn.dmego.seata.xa.business.serivce;

import cn.dmego.seata.common.dto.BusinessDTO;

public interface BusinessService {

    String handleBusiness(BusinessDTO businessDTO);
}
