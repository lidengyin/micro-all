package cn.hctech2006.micro.microueditorconsumer.service;


import cn.hctech2006.micro.microueditorconsumer.http.HttpResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "micro-ueditor",contextId = "b")
@RequestMapping("/article")
public interface SysArticleService {

    @PostMapping("/register")
    public HttpResult upload(@RequestParam String articleName, @RequestParam String articleImgUrl, @RequestParam String articleContentUrl,
                             @RequestParam String articleIntroUrl, @RequestParam List<Long> depts, @RequestParam List<Long> users, @RequestParam List<Long> types);

    @PutMapping("/update")
    public HttpResult update(@RequestParam(required = false) Byte delFlag, @RequestParam Long id, @RequestParam(required = false) String articleName, @RequestParam(required = false) String articleImgUrl, @RequestParam String articleContentUrl,
                             @RequestParam(required = false) String articleIntroUrl, @RequestParam(required = false) List<Long> depts, @RequestParam(required = false) List<Long> users, @RequestParam(required = false) List<Long> types);


    @PostMapping("/find/id")
    public HttpResult findId(@RequestParam Long id);

    @PostMapping("/find/list")
    public HttpResult findByPage(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam Long id,
                                 @RequestParam String name, @RequestParam Long typeId, @RequestParam Long userId,
                                 @RequestParam Long deptId, @RequestParam Byte delFlag);
}
