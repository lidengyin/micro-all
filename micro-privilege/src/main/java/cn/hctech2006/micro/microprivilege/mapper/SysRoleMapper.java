package cn.hctech2006.micro.microprivilege.mapper;


import cn.hctech2006.micro.microprivilege.bean.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    List<SysRole> selectAll(SysRole sysRole);

    int updateByPrimaryKey(SysRole record);
    int updateByPrimaryKey(List<SysRole> record);
}