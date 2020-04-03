package cn.hctech2006.micro.microprivilege.service;


import cn.hctech2006.micro.microprivilege.bean.SysMenu;
import cn.hctech2006.micro.microprivilege.page.PageRequest;
import cn.hctech2006.micro.microprivilege.page.PageResult;

import java.util.List;

public interface SysMenuService extends CurdService<SysMenu> {
    @Override
    int save(SysMenu record);

    @Override
    int delete(SysMenu record);

    @Override
    int delete(List<SysMenu> records);

    @Override
    SysMenu findById(Long id);

    @Override
    PageResult findPage(PageRequest pageRequest);

    @Override
    List<SysMenu> findAll(SysMenu records);
}
