package cn.hctech2006.micro.microueditor.mapper;

import cn.hctech2006.micro.microueditor.bean.SysUserArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserArticleMapper {
    int deleteByPrimaryKey(Long articleId);

    int insert(SysUserArticle record);

    SysUserArticle selectByPrimaryKey(@Param("userId") Long userId, @Param("articleId") Long articleId);

    List<SysUserArticle> selectAll();

    int updateByPrimaryKey(SysUserArticle record);
}