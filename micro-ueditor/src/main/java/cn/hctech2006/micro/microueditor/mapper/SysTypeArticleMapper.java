package cn.hctech2006.micro.microueditor.mapper;

import cn.hctech2006.micro.microueditor.bean.SysTypeArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysTypeArticleMapper {
    int deleteByPrimaryKey(Long articleId);

    int insert(SysTypeArticle record);

    SysTypeArticle selectByPrimaryKey(@Param("articleId") Long articleId, @Param("typeId") Long typeId);

    List<SysTypeArticle> selectAll();

    int updateByPrimaryKey(SysTypeArticle record);
}