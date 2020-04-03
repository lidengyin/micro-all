package cn.hctech2006.micro.microueditorconsumer.controller;

import cn.hctech2006.micro.microueditorconsumer.bean.SysType;
import cn.hctech2006.micro.microueditorconsumer.http.HttpResult;
import cn.hctech2006.micro.microueditorconsumer.service.SysTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "文章类型信息接口")
@RestController
@RequestMapping("/feign/type")
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
        return sysTypeService.upload(name);
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
       return sysTypeService.update(sysTypes);
    }
    @ApiOperation(value = "查看类型列表",notes = "查看类型列表")
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
        return sysTypeService.findPage(pageNum, pageSize, name, id, delFlag);
    }
    @ApiOperation(value = "查询机构列表",notes = "显示机构信息\n" +
            "可选参数进行and组合，全部为空则为查询全部\n" +
            "@ApiImplicitParam(type = \"query\",name = \"id\",value = \"机构编号\",required = true),\n" +
            "            @ApiImplicitParam(type = \"query\",name = \"name\",value = \"机构名\"),\n" +
            "            @ApiImplicitParam(type = \"query\",name = \"parentId\",value = \"上级机构ID，一级机构为0\"),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"delFlag\",value = \"删除标志，-1删除，0正常\")")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "id", value = "机构编号"),
            @ApiImplicitParam(paramType = "query",name = "name", value = "机构名称", required = false),
            @ApiImplicitParam(paramType = "query",name = "parentId", value = "父机构ID，顶级机构父ID为－１", required = false),
            @ApiImplicitParam(paramType = "query",name = "delFlag", value = "删除标志，－１删除状态，０正常", required = false),

    })

    @PostMapping("/find/all")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult findAll( Long id,String name,Long parentId,  Byte delFlag){
        return sysTypeService.findAll(id,name,parentId,delFlag);
    }

    @ApiOperation(value = "具体查看某个文章类型",notes = "具体查看某个文章类型")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "id", value = "文章列表", required = false)
    })

    @PostMapping("/find/id")
    public HttpResult find(Long id){
        return sysTypeService.find(id);
    }
}
