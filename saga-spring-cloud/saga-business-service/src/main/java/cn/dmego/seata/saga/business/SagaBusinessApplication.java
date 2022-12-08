package cn.dmego.seata.saga.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * SagaBusinessApplication
 *
 * @author dmego
 * @date 2021/3/31 10:48
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class SagaBusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(SagaBusinessApplication.class, args);
    }
}
