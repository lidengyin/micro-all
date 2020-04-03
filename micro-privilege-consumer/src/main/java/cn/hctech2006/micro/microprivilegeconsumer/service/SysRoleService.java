package cn.hctech2006.micro.microprivilegeconsumer.service;

import cn.hctech2006.micro.microprivilegeconsumer.bean.SysRole;
import cn.hctech2006.micro.microprivilegeconsumer.http.HttpResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "micro-privilege",contextId = "f")
@RequestMapping("/role")
public interface SysRoleService {

    @PostMapping("/register")
    public HttpResult upload(@RequestParam String name, @RequestParam String remark, @RequestBody List<Long> menus);

    @PutMapping("/update/list")
    public HttpResult update(@RequestBody List<SysRole> sysRoles, @RequestParam(required = false) List<Long> menus);

    @PostMapping("/find/page")
    public HttpResult find(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam String name, @RequestParam Byte delFlag);

    @PostMapping("/find/id")
    public HttpResult findById(@RequestParam Long id);

    @PostMapping("/find/all")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult findAll( @RequestParam String name, @RequestParam Byte delFlag);
}
