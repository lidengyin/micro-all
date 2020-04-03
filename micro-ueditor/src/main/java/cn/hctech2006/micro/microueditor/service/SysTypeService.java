package cn.hctech2006.micro.microueditor.service;

import cn.hctech2006.micro.microueditor.bean.SysType;
import cn.hctech2006.micro.microueditor.page.PageRequest;
import cn.hctech2006.micro.microueditor.page.PageResult;

import java.util.List;

public interface SysTypeService extends CurdService<SysType>{
    @Override
    int save(SysType record);

    @Override
    int delete(SysType record);

    @Override
    int delete(List<SysType> records);

    @Override
    SysType findById(Long id);

    @Override
    PageResult findPage(PageRequest pageRequest);

    @Override
    int update(SysType record);

    @Override
    int update(List<SysType> records);

    @Override
    List<SysType> findAll(SysType records);
}
