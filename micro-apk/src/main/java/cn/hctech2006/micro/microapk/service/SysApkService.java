package cn.hctech2006.micro.microapk.service;



import cn.hctech2006.micro.microapk.bean.SysApk;
import cn.hctech2006.micro.microapk.page.PageRequest;
import cn.hctech2006.micro.microapk.page.PageResult;

import java.util.List;

public interface SysApkService extends CurdService<SysApk> {
    @Override
    int save(SysApk record);

    @Override
    int delete(SysApk record);

    @Override
    int delete(List<SysApk> records);

    @Override
    SysApk findById(Long id);

    @Override
    PageResult findPage(PageRequest pageRequest);


}
