package cn.hctech2006.micro.microueditorconsumer.service;


import cn.hctech2006.micro.microueditorconsumer.bean.SysType;
import cn.hctech2006.micro.microueditorconsumer.http.HttpResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "micro-ueditor",contextId = "g")
@RequestMapping("/type")
public interface SysTypeService {
    @PostMapping("/register")
    public HttpResult upload(@RequestParam String name);

    @PutMapping("/update")
    public HttpResult update(@RequestBody List<SysType> sysTypes);

    @PostMapping("/find/page")
    public HttpResult findPage(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam String name, @RequestParam Long id, @RequestParam Byte delFlag);

    @PostMapping("/find/all")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult findAll( @RequestParam Long id,@RequestParam String name,@RequestParam Long parentId,@RequestParam  Byte delFlag);

    @PostMapping("/find/id")
    public HttpResult find(@RequestParam Long id);
}
