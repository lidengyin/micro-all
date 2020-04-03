package cn.hctech2006.micro.microueditor.controller;

import cn.hctech2006.micro.microueditor.bean.SysDept;
import cn.hctech2006.micro.microueditor.http.HttpResult;
import cn.hctech2006.micro.microueditor.page.PageRequest;
import cn.hctech2006.micro.microueditor.page.PageResult;
import cn.hctech2006.micro.microueditor.service.SysDeptService;
import cn.hctech2006.micro.microueditor.util.OSSUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Api(tags = "机构信息接口")
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private SysDeptService sysDeptService;
    @ApiOperation(value = "分页查询机构信息",notes = "分页显示机构信息\n" +
            "可选参数进行and组合，全部为空则为查询全部\n" +
            "@ApiImplicitParam(type = \"query\",name = \"id\",value = \"机构编号\",required = true),\n" +
            "            @ApiImplicitParam(type = \"query\",name = \"name\",value = \"机构名\"),\n" +
            "            @ApiImplicitParam(type = \"query\",name = \"parentId\",value = \"上级机构ID，一级机构为0\"),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"delFlag\",value = \"删除标志，-1删除，0正常\")")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "当前页码",required = true),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页行数",required = true),
            @ApiImplicitParam(paramType = "query",name = "id", value = "机构编号"),
            @ApiImplicitParam(paramType = "query",name = "name", value = "机构名称", required = false),
            @ApiImplicitParam(paramType = "query",name = "parentId", value = "父机构ID，顶级机构父ID为－１", required = false),
            @ApiImplicitParam(paramType = "query",name = "delFlag", value = "删除标志，－１删除状态，０正常", required = false),

    })
    @PostMapping("/find/page")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult find(int pageNum, int pageSize, Long id,String name,Long parentId,  Byte delFlag){
        try{
            SysDept sysDept = new SysDept();
            sysDept.setId(id);
            sysDept.setName(name);
            sysDept.setParentId(parentId);
            sysDept.setDelFlag(delFlag);
            Map<String, Object> map = new HashMap<>();
            map.put("sysDept",sysDept);
            PageRequest pageRequest = new PageRequest(pageNum, pageSize, map);
            PageResult pageResult = sysDeptService.findPage(pageRequest);
            return HttpResult.ok(pageResult);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("查询失败");
        }
    }
    @ApiOperation(value = "具体查看某个机构信息")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "id", value = "机构编号",required = true)
    })
    @PostMapping("/find/id")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult findById(Long id){
        try{
            SysDept sysDept = sysDeptService.findById(id);
            return HttpResult.ok(sysDept);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("查询机构失败");
        }

    }
    @ApiOperation(value = "查询机构树",notes = "查询机构树")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "parentId", value = "规定根目录的父ID为-1，",required = true)

    })
    @PostMapping("/find/tree")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult findDeptNodes(Long parentId){
        try{
            System.out.println("parentId:"+parentId);
            List<SysDept> sysDepts = sysDeptService.findByParentId(parentId);
            List<SysDept> rootList = sysDeptService.findRootTree();
            for(SysDept sysDept: sysDepts){
                List<SysDept> depts = getChildNodes(sysDept.getParentId(), rootList);
                sysDept.setDeptList(depts);
            }
            return HttpResult.ok(sysDepts);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("查询失败");
        }
    }

    private List<SysDept> getChildNodes(Long id, List<SysDept> rootList){
        //新建字节点列表
        List<SysDept> chileList = new ArrayList<>();
        //根据父节点ID填充对应的字节点
        for(SysDept sysDept : rootList){
            if(sysDept.getParentId() != null){
                if(sysDept.getParentId() == id){
                    chileList.add(sysDept);
                }
            }
        }
        if(chileList.size() == 0){
            return null;
        }
        //便利子节点并且进行对应的填充
        for(SysDept sysDept: chileList){
            sysDept.setDeptList(getChildNodes(sysDept.getId(), rootList));
        }
        return chileList;
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
        try{
            SysDept sysDept = new SysDept();
            sysDept.setId(id);
            sysDept.setName(name);
            sysDept.setParentId(parentId);
            sysDept.setDelFlag(delFlag);
            List<SysDept> sysDepts = sysDeptService.findAll(sysDept);
            return HttpResult.ok(sysDepts);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("查询失败");
        }
    }
}
