package cn.hctech2006.micro.microapkconsumer.service;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@FeignClient(name = "micro-apk",contextId = "e")
public interface SysQRCodeService {
    @GetMapping(value = "/qrcode/createCommonQRCode",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response createCommonQRCode(@RequestParam HttpServletResponse response, @RequestParam Long id);
}
