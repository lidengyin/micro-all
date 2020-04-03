package cn.hctech2006.micro.microapkconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MicroApkConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroApkConsumerApplication.class, args);
    }

}
