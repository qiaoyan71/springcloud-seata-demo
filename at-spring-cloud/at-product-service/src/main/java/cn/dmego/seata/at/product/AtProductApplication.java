package cn.dmego.seata.at.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @className: AtProductApplication
 *
 * @description: AtProductApplication
 * @author: ZengKai<dmeago@gmail.com>
 * @date: 2020/12/8 17:42
 **/
@ComponentScan(basePackages = {
        "cn.dmego.seata.common.config",
        "cn.dmego.seata.at.product"
})
@SpringBootApplication
@EnableDiscoveryClient
public class AtProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtProductApplication.class, args);
    }

}
