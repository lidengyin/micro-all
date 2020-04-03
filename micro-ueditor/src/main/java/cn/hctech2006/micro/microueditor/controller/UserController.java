package cn.hctech2006.micro.microueditor.controller;

import cn.hctech2006.micro.microueditor.bean.SysUser;
import cn.hctech2006.micro.microueditor.http.HttpResult;
import cn.hctech2006.micro.microueditor.page.PageRequest;
import cn.hctech2006.micro.microueditor.page.PageResult;
import cn.hctech2006.micro.microueditor.service.SysUserService;
import cn.hctech2006.micro.microueditor.util.OSSUtils;
import com.mysql.cj.jdbc.ClientPreparedStatement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Api(tags = "用户信息接口")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SysUserService sysUserService;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");



    @ApiOperation(value = "分页查看用户列表",notes = "分页查看用户列表:可选参数列表，以and的形式，随机组合，不加参数就是全选")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "当前页码",required = true),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页行数",required = true),
            @ApiImplicitParam(type = "query", name="id",value = "用户编号"),
            @ApiImplicitParam(type = "query", name = "name",value = "用户名"),
            @ApiImplicitParam(type = "query", name = "deptId",value = "所属方向ID"),
            @ApiImplicitParam(type = "query", name = "grade",value = "年级，比如2018"),
            @ApiImplicitParam(type = "query", name = "delFlag",value = "删除标志，-1删除，0正常")
    })
    @PostMapping("/find/page")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult find(int pageNum, int pageSize, Long id, String name, String grade, Long deptId, Byte delFlag){
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setGrade(grade);
        sysUser.setName(name);
        sysUser.setDeptId(deptId);
        sysUser.setDelFlag(delFlag);
        Map<String, Object> map = new HashMap<>();
        map.put("sysUser",sysUser);
        PageRequest pageRequest = new PageRequest(pageNum, pageSize, map);
        PageResult pageResult = sysUserService.findPage(pageRequest);
        return HttpResult.ok(pageResult);
    }
    @ApiOperation(value = "具体查看某一个用户信息",notes = "具体查看某一个用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "id", value = "用户编号", required = true)
    })
    @PostMapping("/find/id")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult findById(Long id){
        try{
            SysUser sysUser = sysUserService.findById(id);
            return HttpResult.ok(sysUser);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("查询用户失败");
        }
    }
    @ApiOperation(value = "查看用户列表",notes = "查看用户列表:可选参数列表，以and的形式，随机组合，不加参数就是全选")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name="id",value = "用户编号"),
            @ApiImplicitParam(type = "query", name = "name",value = "用户名"),
            @ApiImplicitParam(type = "query", name = "deptId",value = "所属方向ID"),
            @ApiImplicitParam(type = "query", name = "grade",value = "年级，比如2018"),
            @ApiImplicitParam(type = "query", name = "delFlag",value = "删除标志，-1删除，0正常")
    })
    @PostMapping("/find/all")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult findALL( Long id, String name, String grade, Long deptId, Byte delFlag){
        try{
            SysUser sysUser = new SysUser();
            sysUser.setId(id);
            sysUser.setGrade(grade);
            sysUser.setName(name);
            sysUser.setDeptId(deptId);
            sysUser.setDelFlag(delFlag);
            List<SysUser> sysUsers = sysUserService.findAll(sysUser);
            return HttpResult.ok(sysUsers);

        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("查找失败");
        }
    }
}
