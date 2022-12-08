package cn.dmego.seata.at.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @className: AtOrderApplication
 *
 * @description: AtOrderApplication
 * @author: ZengKai<dmeago@gmail.com>
 * @date: 2020/12/8 17:42
 **/
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan(basePackages = {
        "cn.dmego.seata.common.config",
        "cn.dmego.seata.at.order"
})
public class AtOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtOrderApplication.class, args);
    }

}
