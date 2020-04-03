package cn.hctech2006.micro.microprivilegeconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class MicroPrivilegeConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroPrivilegeConsumerApplication.class, args);
    }

}
