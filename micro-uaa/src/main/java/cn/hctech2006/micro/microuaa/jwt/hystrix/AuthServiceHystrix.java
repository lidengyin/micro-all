package cn.hctech2006.micro.microuaa.jwt.hystrix;


import cn.hctech2006.micro.microuaa.jwt.JWT;
import cn.hctech2006.micro.microuaa.jwt.fegin.AuthServiceClient;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceHystrix implements AuthServiceClient {


    @Override
    public JWT getToken(String authorization, String type, String username, String password) {
        return null;
    }
}
