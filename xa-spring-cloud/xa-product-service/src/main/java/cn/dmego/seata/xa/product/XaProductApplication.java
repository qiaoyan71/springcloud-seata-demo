package cn.dmego.seata.xa.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


/**
 * XaProductApplication
 *
 * @author qiaoyan
 * @date 2022-11-28 16:04:03
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {
        "cn.dmego.seata.common.config",
        "cn.dmego.seata.xa.product"
})
public class XaProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(XaProductApplication.class, args);
    }

}
