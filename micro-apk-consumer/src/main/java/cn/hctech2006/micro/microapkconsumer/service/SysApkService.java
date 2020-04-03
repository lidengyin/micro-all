package cn.hctech2006.micro.microapkconsumer.service;



import cn.hctech2006.micro.microapkconsumer.http.HttpResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(contextId = "a", name = "micro-apk")
@RequestMapping(value = "/upload")
public interface SysApkService {

    @RequestMapping(value = "/apk",method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public HttpResult upload(@RequestBody MultipartFile uploadFile);

    @PostMapping("/select/delFlag")
    public HttpResult selectByDelFlag(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam Byte delFlag);

    @PostMapping("/select/id")
    public HttpResult find(@RequestParam Long id);

    @PutMapping("/update")
    public HttpResult updateApk(@RequestParam Long id, @RequestParam Byte delFlag);

}
