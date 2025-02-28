package cn.dmego.seata.saga.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * SagaAccountApplication
 *
 * @author dmego
 * @date 2021/3/31 10:45
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SagaAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(SagaAccountApplication.class, args);
    }
}
