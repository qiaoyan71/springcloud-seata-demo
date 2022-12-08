package cn.dmego.seata.saga.business.config;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * @author dmego
 * @date 2021/05/10 18:36
 */
@Configuration
@Slf4j
public class FeignErrorDecoder extends ErrorDecoder.Default {

    @Override
    public Exception decode(String methodKey, Response response) {
        Exception exception = super.decode(methodKey, response);
        try {
            // 将 FeignException 包装成 普通异常
            if (exception instanceof FeignException) {
                exception = new RuntimeException(exception.getMessage());
            }
        }catch(Exception ex){
            log.error(ex.getMessage(), ex);
        }
        return exception;
    }
}
