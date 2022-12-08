package cn.dmego.seata.tcc.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * tccaccount应用程序
 *
 * @author qiaoyan
 * @date 2022-11-24 16:01:07
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {
        "cn.dmego.seata.common.config",
        "cn.dmego.seata.tcc.account"
})
public class TCCAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(TCCAccountApplication.class, args);
    }
}
