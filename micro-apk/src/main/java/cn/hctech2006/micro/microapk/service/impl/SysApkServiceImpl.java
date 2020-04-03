package cn.hctech2006.micro.microapk.service.impl;

import cn.hctech2006.micro.microapk.bean.SysApk;
import cn.hctech2006.micro.microapk.mapper.SysApkMapper;
import cn.hctech2006.micro.microapk.page.MybatisPageHelper;
import cn.hctech2006.micro.microapk.page.PageRequest;
import cn.hctech2006.micro.microapk.page.PageResult;
import cn.hctech2006.micro.microapk.service.SysApkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysApkServiceImpl implements SysApkService {
    @Autowired
    private SysApkMapper sysApkMapper;
    @Override
    public int save(SysApk record) {
        return sysApkMapper.insert(record);
    }

    @Override
    public int delete(SysApk record) {
        int result = sysApkMapper.updateDelFlagById(record);
        return result;
    }

    @Override
    public int delete(List<SysApk> records) {
        return 0;
    }

    @Override
    public SysApk findById(Long id) {
        return sysApkMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {

           return MybatisPageHelper.findPage(pageRequest, sysApkMapper, "selectByDelFlag", pageRequest.getParam("sysApk"));

    }

    @Override
    public int update(SysApk record) {
        return sysApkMapper.updateByPrimaryKey(record);
    }

    @Override
    public int update(List<SysApk> records) {
        return 0;
    }
}
