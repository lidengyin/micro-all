package cn.hctech2006.micro.microueditor.mapper;

import cn.hctech2006.micro.microueditor.bean.SysDeptArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysDeptArticleMapper {
    int deleteByPrimaryKey(Long articleId);

    int insert(SysDeptArticle record);

    SysDeptArticle selectByPrimaryKey(@Param("deptId") Long deptId, @Param("articleId") Long articleId);

    List<SysDeptArticle> selectAll();

    int updateByPrimaryKey(SysDeptArticle record);

}