package cn.dmego.seata.tcc.business;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @className: TCCBusinessApplication
 *
 * @description: 业务服务
 * @author: ZengKai<dmeago@gmail.com>
 * @date: 2020/12/6 17:26
 **/
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        DruidDataSourceAutoConfigure.class ,
        HibernateJpaAutoConfiguration.class
})@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan(basePackages = {
        "cn.dmego.seata.common.config",
        "cn.dmego.seata.tcc.business"
})
public class TCCBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(TCCBusinessApplication.class, args);
    }
}
