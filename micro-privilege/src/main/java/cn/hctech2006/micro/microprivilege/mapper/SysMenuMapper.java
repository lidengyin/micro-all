package cn.hctech2006.micro.microprivilege.mapper;

import cn.hctech2006.micro.microprivilege.bean.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysMenu record);

    SysMenu selectByPrimaryKey(Long id);

    List<SysMenu> selectAll(SysMenu sysMenu);

    int updateByPrimaryKey(SysMenu record);
    List<SysMenu> selectByRoleId(Long roleId);
}