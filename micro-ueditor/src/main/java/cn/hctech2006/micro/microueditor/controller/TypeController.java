package cn.hctech2006.micro.microueditor.controller;

import cn.hctech2006.micro.microueditor.bean.SysType;
import cn.hctech2006.micro.microueditor.http.HttpResult;
import cn.hctech2006.micro.microueditor.page.PageRequest;
import cn.hctech2006.micro.microueditor.page.PageResult;
import cn.hctech2006.micro.microueditor.service.SysTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "文章类型信息接口")
@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private SysTypeService sysTypeService;
    @ApiOperation(value = "文章类型注册",notes = "文章类型注册,也就是显示的标签")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query",name = "name",value = "文章类型名",required = true)
    })
    @PostMapping("/register")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult upload(String name){

        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            SysType sysType = new SysType();
            sysType.setName(name);
            sysType.setCreateBy(authentication.getName());
            sysType.setCreateTime(new Date());
            sysType.setLastUpdateTime(new Date());
            sysType.setDelFlag((byte)0);
            sysType.setLastUpdateBy(authentication.getName());
            sysTypeService.save(sysType);
            return HttpResult.ok(sysType);
        }catch (DuplicateKeyException e){
            e.printStackTrace();
            return HttpResult.error("类型重复注册");
        }
    }
    @ApiOperation(value = "批量文章类型修改",notes = "文章类型修改，需要参数如下，自主组合，and模式\n" +
            "        @ApiImplicitParam(type = \"query\",name = \"id\", required = true,value=类型ID),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"name\",required = false,value=类型名),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"delFlag\", required = false，value=删除标志)[\n" +
            "实例\n" +
            "  {\n" +
            "    \n" +
            "    \"delFlag\": 0,\n" +
            "    \"id\": 0,\n" +
            "    \"name\": \"string\"\n" +
            "  }，\n" +
            "  {\n" +
            "    \n" +
            "    \"delFlag\": 0,\n" +
            "    \"id\": １,\n" +
            "    \"name\": \"str\"\n" +
            "  }\n" +
            "]")
    @ApiImplicitParams({
    })
    @PutMapping("/update")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult update(@RequestBody List<SysType> sysTypes){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            for(SysType sysType: sysTypes){
                sysType.setLastUpdateBy(authentication.getName());
                sysType.setLastUpdateTime(new Date());
                sysTypeService.update(sysType);
            }
            return HttpResult.ok("类型修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("类型修改失败");
        }
    }
    @ApiOperation(value = "分页查看类型列表",notes = "分页查看类型列表")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "pageNum",value = "页码",required = true),
            @ApiImplicitParam(type = "query", name = "pageSize",value = "页行",required = true),
            @ApiImplicitParam(type = "query",name = "name",value = "类型名"),
            @ApiImplicitParam(type = "query",name = "id",value = "类型ID"),
            @ApiImplicitParam(type = "query",name = "delFlag",value = "删除标志,-1删除，０正常")

    })
    @PostMapping("/find/page")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult findPage(int pageNum, int pageSize, String name, Long id, Byte delFlag){
        try{
            SysType sysType = new SysType();
            sysType.setName(name);
            sysType.setId(id);
            sysType.setDelFlag(delFlag);
            Map<String, Object> map = new HashMap<>();
            map.put("sysType",sysType);
            PageRequest pageRequest = new PageRequest(pageNum, pageSize, map);
            PageResult pageResult = sysTypeService.findPage(pageRequest);
            return HttpResult.ok(pageResult);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("分页查询失败");
        }
    }
    @ApiOperation(value = "具体查看某个文章类型",notes = "具体查看某个文章类型")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "id", value = "文章列表", required = false)
    })
    @PostMapping("/find/id")
    public HttpResult find(Long id){
        try {
            SysType sysType = sysTypeService.findById(id);
            return HttpResult.ok(sysType);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("具体查询失败");
        }
    }
    @ApiOperation(value = "查看类型列表",notes = "查看类型列表")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query",name = "name",value = "类型名"),
            @ApiImplicitParam(type = "query",name = "id",value = "类型ID"),
            @ApiImplicitParam(type = "query",name = "delFlag",value = "删除标志,-1删除，０正常")

    })
    @PostMapping("/find/all")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult findAll( String name, Long id, Byte delFlag){
        try{
            SysType sysType = new SysType();
            sysType.setName(name);
            sysType.setId(id);
            sysType.setDelFlag(delFlag);
            List<SysType> sysTypes = sysTypeService.findAll(sysType);
            return HttpResult.ok(sysTypes);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("分页查询失败");
        }
    }
}
