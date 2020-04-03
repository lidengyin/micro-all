package cn.hctech2006.micro.microueditor.mapper;

import cn.hctech2006.micro.microueditor.bean.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    List<SysUser> selectAll(SysUser sysUser);

    int updateByPrimaryKey(SysUser record);

    SysUser findByUsername(String username);

    List<SysUser> findByDeptId(Long deptId);
    List<SysUser> selectByArticleId(Long articleId);
}