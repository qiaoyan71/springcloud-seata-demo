package cn.dmego.seata.saga.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * SagaProductApplication
 *
 * @author dmego
 * @date 2021/3/31 10:53
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SagaProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(SagaProductApplication.class, args);
    }
}
