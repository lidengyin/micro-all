package cn.hctech2006.micro.microuaa.mapper;


import cn.hctech2006.micro.microuaa.bean.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysMenu record);

    SysMenu selectByPrimaryKey(Long id);

    List<SysMenu> selectAll();

    int updateByPrimaryKey(SysMenu record);

    List<SysMenu> selectMenusByUsername(String username);
}