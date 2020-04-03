package cn.hctech2006.micro.microueditor.service;


import cn.hctech2006.micro.microueditor.bean.SysArticle;
import cn.hctech2006.micro.microueditor.bean.SysDeptArticle;
import cn.hctech2006.micro.microueditor.bean.SysTypeArticle;
import cn.hctech2006.micro.microueditor.bean.SysUserArticle;
import cn.hctech2006.micro.microueditor.page.PageRequest;
import cn.hctech2006.micro.microueditor.page.PageResult;

import java.util.List;

public interface SysArticleService extends CurdService<SysArticle>{
    @Override
    int save(SysArticle record);

    @Override
    int delete(SysArticle record);

    @Override
    int delete(List<SysArticle> records);

    @Override
    SysArticle findById(Long id);

    @Override
    PageResult findPage(PageRequest pageRequest);

    @Override
    int update(SysArticle record);

    @Override
    int update(List<SysArticle> records);

    int saveUserAndArticle(SysUserArticle sysUserArticle);
    int saveDeptAndArticle(SysDeptArticle sysDeptArticle);
    int saveTypeAndArticle(SysTypeArticle sysTypeArticle);
    int deleteUserAndArticle(Long articleId);
    int deleteDeptAndArticle(Long articleId);
    int deleteTypeAndArticle(Long articleId);

    @Override
    List<SysArticle> findAll(SysArticle records);
}
