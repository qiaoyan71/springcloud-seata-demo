package cn.dmego.seata.tcc.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @className: TCCProductApplication
 *
 * @description: 仓库服务
 * @author: ZengKai<dmeago@gmail.com>
 * @date: 2020/12/8 17:30
 **/
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {
        "cn.dmego.seata.common.config",
        "cn.dmego.seata.tcc.product"
})
public class TCCProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(TCCProductApplication.class, args);
    }
}
