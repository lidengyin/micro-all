package cn.hctech2006.micro.microueditorconsumer.service;

import cn.hctech2006.micro.microueditorconsumer.http.HttpResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "micro-ueditor",contextId = "c")
@RequestMapping("/dept")
public interface SysDeptService {
    @RequestMapping(value = "/register", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public HttpResult upload(@RequestParam String name, @RequestParam Long parentId, @RequestBody MultipartFile uploadFile);

    @RequestMapping(value = "/update", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public HttpResult update(@RequestParam String name, @RequestParam Long id, @RequestParam Long parentId, @RequestParam Byte delFlag, @RequestBody MultipartFile uploadFile);

    @PostMapping("/find/page")
    public HttpResult find(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam Long id, @RequestParam String name, @RequestParam Long parentId, @RequestParam Byte delFlag);

    @PostMapping("/find/id")
    public HttpResult findById(@RequestParam Long id);

    @PostMapping("/find/tree")
    public HttpResult findDeptNodes(@RequestParam Long parentId);

    @PostMapping("/find/all")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult findAll( @RequestParam Long id,@RequestParam String name,@RequestParam Long parentId,@RequestParam Byte delFlag);

}
