package cn.hctech2006.micro.microprivilegeconsumer.service;


import cn.hctech2006.micro.microprivilegeconsumer.bean.SysMenu;
import cn.hctech2006.micro.microprivilegeconsumer.http.HttpResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "micro-privilege",contextId = "d")
@RequestMapping("/menu")
public interface SysMenuService {

    @PostMapping("/register")
    public HttpResult upload(@RequestParam String name, @RequestParam Long parentId, @RequestParam String perms);

    @PutMapping("/update/list")
    public HttpResult update(@RequestBody List<SysMenu> sysMenus);

    @PostMapping("/find/page")
    public HttpResult find(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam Long id, @RequestParam String name, @RequestParam Long parentId, @RequestParam String perms, @RequestParam Byte delFlag);

    @PostMapping("/find/all")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult findAll(@RequestParam Long id ,@RequestParam String name,@RequestParam Long parentId,@RequestParam String perms, Byte delFlag);

}
