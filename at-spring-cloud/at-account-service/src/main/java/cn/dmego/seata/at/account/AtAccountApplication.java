package cn.dmego.seata.at.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 帐户应用程序
 *
 * @author qiaoyan
 * @date 2022-11-24 10:25:11
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {
        "cn.dmego.seata.common.config",
        "cn.dmego.seata.at.account"
})
public class AtAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtAccountApplication.class, args);
    }

}
