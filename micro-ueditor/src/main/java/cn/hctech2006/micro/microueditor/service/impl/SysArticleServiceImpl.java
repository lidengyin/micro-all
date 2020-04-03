package cn.hctech2006.micro.microueditor.service.impl;

import cn.hctech2006.micro.microueditor.bean.SysArticle;
import cn.hctech2006.micro.microueditor.bean.SysDeptArticle;
import cn.hctech2006.micro.microueditor.bean.SysTypeArticle;
import cn.hctech2006.micro.microueditor.bean.SysUserArticle;
import cn.hctech2006.micro.microueditor.mapper.SysArticleMapper;
import cn.hctech2006.micro.microueditor.mapper.SysDeptArticleMapper;
import cn.hctech2006.micro.microueditor.mapper.SysTypeArticleMapper;
import cn.hctech2006.micro.microueditor.mapper.SysUserArticleMapper;
import cn.hctech2006.micro.microueditor.page.MybatisPageHelper;
import cn.hctech2006.micro.microueditor.page.PageRequest;
import cn.hctech2006.micro.microueditor.page.PageResult;
import cn.hctech2006.micro.microueditor.service.SysArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysArticleServiceImpl  implements SysArticleService {
    @Autowired
    private SysArticleMapper sysArticleMapper;
    @Autowired
    private SysTypeArticleMapper sysTypeArticleMapper;
    @Autowired
    private SysUserArticleMapper sysUserArticleMapper;
    @Autowired
    private SysDeptArticleMapper sysDeptArticleMapper;
    @Override
    public int save(SysArticle record) {
        return sysArticleMapper.insert(record);
    }

    @Override
    public int delete(SysArticle record) {
        return 0;
    }

    @Override
    public int delete(List<SysArticle> records) {
        return 0;
    }

    @Override
    public SysArticle findById(Long id) {
        return sysArticleMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, sysArticleMapper, "selectAll", pageRequest.getParam("sysArticle"));
    }

    @Override
    public int update(SysArticle record) {
        return sysArticleMapper.updateByPrimaryKey(record);
    }

    @Override
    public int update(List<SysArticle> records) {
        return 0;
    }

    @Override
    public int saveUserAndArticle(SysUserArticle sysUserArticle) {
        return sysUserArticleMapper.insert(sysUserArticle);
    }

    @Override
    public int saveDeptAndArticle(SysDeptArticle sysDeptArticle) {
        return sysDeptArticleMapper.insert(sysDeptArticle);
    }

    @Override
    public int saveTypeAndArticle(SysTypeArticle sysTypeArticle) {
        return sysTypeArticleMapper.insert(sysTypeArticle);
    }

    @Override
    public int deleteUserAndArticle(Long articleId) {
        return sysUserArticleMapper.deleteByPrimaryKey(articleId);
    }

    @Override
    public int deleteDeptAndArticle(Long articleId) {
        return sysDeptArticleMapper.deleteByPrimaryKey(articleId);
    }

    @Override
    public int deleteTypeAndArticle(Long articleId) {
        return sysTypeArticleMapper.deleteByPrimaryKey(articleId);
    }

    @Override
    public List<SysArticle> findAll(SysArticle records) {
        return sysArticleMapper.selectAll(records);
    }

}
