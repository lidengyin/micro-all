package cn.hctech2006.micro.microapk.controller;


import ch.qos.logback.core.pattern.color.BoldYellowCompositeConverter;
import cn.hctech2006.micro.microapk.bean.SysApk;
import cn.hctech2006.micro.microapk.http.HttpResult;
import cn.hctech2006.micro.microapk.page.PageRequest;
import cn.hctech2006.micro.microapk.page.PageResult;
import cn.hctech2006.micro.microapk.service.SysApkService;
import cn.hctech2006.micro.microapk.util.ApkInfoUtil;
import cn.hctech2006.micro.microapk.util.OSSUtils;
//import com.netflix.ribbon.proxy.annotation.Http;
//import com.sun.org.apache.xalan.internal.lib.ExsltBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Api(tags = "APK安装包上传接口")
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private SysApkService sysApkService;
    //文件按时间结构存储
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    @ApiOperation(value = "文件上传",notes = "文件上传")
    @ApiImplicitParams({
            //@ApiImplicitParam(type = "query",name = "createBy",dataType = "String",required = true)
    })
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(value = "/apk",method = RequestMethod.POST)
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",
            methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult upload(@RequestBody MultipartFile uploadFile) throws NullPointerException, FileNotFoundException {

        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            //获取文件大小
            String apkSize = uploadFile.getSize()+"";
            String url = ResourceUtils.getURL("").getPath()+uploadFile.getOriginalFilename();
            File folder = new File(url);
            //文件上传名
            String apkOldName = uploadFile.getOriginalFilename();
            //文件存储名:UUID+文件类型后缀
            String apkStoName = UUID.randomUUID().toString()+
                    apkOldName.substring(apkOldName.lastIndexOf("."),apkOldName.length());

            //将上传文件以指定名称上传到指定文件夹
            uploadFile.transferTo(folder);
            Map<String,Object> apkInfo = ApkInfoUtil.readAPK(folder);
            //应用名
            String apkName = (String) apkInfo.get("name");
            //String apkName = "1";
            //应用包
            String pkName = (String) apkInfo.get("pkName");
            //版本名
            String vName = (String)apkInfo.get("vName");
            //版本号
            long vCode = (Long) apkInfo.get("vCode");
            //注释掉的是阿里云OSS
            url = OSSUtils.upload(folder, UUID.randomUUID().toString()+".apk");
            System.out.println(url);
            folder.delete();
            //设置SysApk
            SysApk sysApk = new SysApk();
            sysApk.setApkName(apkName);
            sysApk.setApkPk(pkName);
            sysApk.setApkVc(vCode);
            sysApk.setApkVn(vName);
            sysApk.setCreateBy(authentication.getName());
            sysApk.setCreateTime(new Date());
            sysApk.setLastUpdateBy(authentication.getName());
            sysApk.setLastUpdateTime(new Date());
            sysApk.setApkSize(apkSize);
            sysApk.setApkUrl(url);
            sysApk.setDelFlag((byte)0);
            sysApkService.save(sysApk);
            return HttpResult.ok(sysApk);
        }catch (IOException e){
            e.printStackTrace();

        }
        return HttpResult.error("上传失败");
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
        System.out.println(pageNum);
        System.out.println(pageSize);
        System.out.println(delFlag);
        SysApk sysApk = new SysApk();
        sysApk.setDelFlag(delFlag);
        Map<String, Object> map = new HashMap<>();
        map.put("sysApk", sysApk);
        PageRequest pageRequest = new PageRequest(pageNum,pageSize,map);
        PageResult pageResult = sysApkService.findPage(pageRequest);
        return HttpResult.ok(pageResult);
    }
    @ApiOperation(value = "具体查看某个apk安装包",notes = "具体查看某个apk安装包")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "id", value = "应用ID", required = false)
    })
    @PostMapping("/select/id")
    public HttpResult find(Long id){
        try {
            SysApk sysApk = sysApkService.findById(id);
            return HttpResult.ok(sysApk);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("查找失败");
        }
    }
    @ApiOperation(value = "修改apk信息", notes = "apk信息修改")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "id", value = "apkID",required = true),
            @ApiImplicitParam(type = "query", name = "delFlag", value = "删除标志，０正常，－１删除")

    })
    @PutMapping("/update")
    public HttpResult updateApk(Long id, Byte delFlag){
        try {
            SysApk sysApk = new SysApk();
            sysApk.setId(id);
            sysApk.setDelFlag(delFlag);
            int result = sysApkService.update(sysApk);
            return HttpResult.ok("信息修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("休息修改失败");
        }
    }
}
