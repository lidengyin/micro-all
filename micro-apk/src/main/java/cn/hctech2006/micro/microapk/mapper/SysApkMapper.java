package cn.hctech2006.micro.microapk.mapper;


import cn.hctech2006.micro.microapk.bean.SysApk;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysApkMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysApk record);

    SysApk selectByPrimaryKey(Long id);

    List<SysApk> selectAll();

    int updateByPrimaryKey(SysApk record);

    int updateDelFlagById(SysApk sysApk);

    List<SysApk> selectByDelFlag(SysApk sysApk);
}