package cn.hctech2006.micro.microprivilege;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

//开启服务熔断
@EnableHystrix
//开启声明式服务消费客户端
@EnableFeignClients
@SpringBootApplication
public class MicroPrivilegeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroPrivilegeApplication.class, args);
    }

}
