package cn.hctech2006.micro.microapkconsumer.controller;


import cn.hctech2006.micro.microapkconsumer.http.HttpResult;
import cn.hctech2006.micro.microapkconsumer.service.SysApkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;



@Api(tags = "APK安装包上传接口")
@RestController
@RequestMapping("/feign/upload")
public class UploadController {
    @Autowired
    private SysApkService sysApkService;
    //文件按时间结构存储
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    @ApiOperation(value = "文件上传",notes = "文件上传")
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/apk",method = RequestMethod.POST)
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",
            methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult upload(@RequestBody MultipartFile uploadFile) throws NullPointerException, FileNotFoundException {
        return sysApkService.upload(uploadFile);
    }
    @ApiOperation(value = "根据删除标志，分页查看上传文件",notes = "根据删除标志，分页查看上传文件")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "pageNum", value = "页码",required = true),
            @ApiImplicitParam(type = "query", name = "pageSize", value = "行数",required = true),
            @ApiImplicitParam(type = "query",name = "delFlag", value = "删除标志，-1为删除，0为正常")
    })
    @PostMapping("/select/delFlag")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult selectByDelFlag(int pageNum, int pageSize, Byte delFlag){
        return sysApkService.selectByDelFlag(pageNum,pageSize,delFlag);
    }
    @ApiOperation(value = "具体查看某个apk安装包",notes = "具体查看某个apk安装包")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "id", value = "应用ID", required = false)
    })
    @PostMapping("/select/id")
    public HttpResult find(Long id){
       return sysApkService.find(id);
    }
    @ApiOperation(value = "修改apk信息", notes = "apk信息修改")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "id", value = "apkID",required = true),
            @ApiImplicitParam(type = "query", name = "delFlag", value = "删除标志，０正常，－１删除")

    })
    @PutMapping("/update")
    public HttpResult updateApk(Long id, Byte delFlag){
        return sysApkService.updateApk(id,delFlag);
    }
}
