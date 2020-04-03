package cn.hctech2006.micro.microueditorconsumer.service;


import cn.hctech2006.micro.microueditorconsumer.http.HttpResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "micro-ueditor",contextId = "i")
@RequestMapping("/user")
public interface SysUserService {

    @PostMapping("/login")

    public HttpResult login(@RequestParam String name, @RequestParam String password);

    @RequestMapping(value = "/register",method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public HttpResult register(@RequestParam String name, @RequestParam String password, @RequestParam Long deptId, @RequestParam String grade, @RequestParam String email, @RequestParam String mobile,
                               @RequestParam List<Long> roleList, @RequestBody MultipartFile uploadFile);

    @RequestMapping(value = "/update",method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public HttpResult update(@RequestParam Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String password, @RequestParam(required = false) Long deptId, @RequestParam(required = false) String grade, @RequestParam(required = false) String email, @RequestParam(required = false) String mobile,
                             @RequestParam(required = false) List<Long> roleList, @RequestBody(required = false) MultipartFile uploadFile);

    @PostMapping("/find/page")
    public HttpResult find(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam(required = false) Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String grade, @RequestParam(required = false) Long deptId, @RequestParam(required = false) Byte delFlag);

    @PostMapping("/find/id")
    public HttpResult findById(@RequestParam Long id);

    @PostMapping("/find/all")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult findALL( @RequestParam Long id, @RequestParam String name, @RequestParam String grade,@RequestParam  Long deptId, @RequestParam Byte delFlag);
}
