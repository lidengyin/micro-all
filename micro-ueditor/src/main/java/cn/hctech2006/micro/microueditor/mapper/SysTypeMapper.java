package cn.hctech2006.micro.microueditor.mapper;

import cn.hctech2006.micro.microueditor.bean.SysType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysType record);

    SysType selectByPrimaryKey(Long id);

    List<SysType> selectAll(SysType sysType);

    int updateByPrimaryKey(SysType record);
    List<SysType> selectTypeByArticleId(Long articleId);
}