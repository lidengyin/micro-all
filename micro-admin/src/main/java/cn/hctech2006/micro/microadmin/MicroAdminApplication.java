package cn.hctech2006.micro.microadmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@EnableAdminServer
@SpringBootApplication
public class MicroAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroAdminApplication.class, args);
    }

}
