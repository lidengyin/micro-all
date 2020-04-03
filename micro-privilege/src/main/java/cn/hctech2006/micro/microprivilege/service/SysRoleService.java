package cn.hctech2006.micro.microprivilege.service;


import cn.hctech2006.micro.microprivilege.bean.SysRole;
import cn.hctech2006.micro.microprivilege.bean.SysRoleMenu;
import cn.hctech2006.micro.microprivilege.page.PageRequest;
import cn.hctech2006.micro.microprivilege.page.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SysRoleService extends CurdService<SysRole>{
    @Override
    int save(SysRole record);

    @Override
    int delete(SysRole record);

    @Override
    int delete(List<SysRole> records);

    @Override
    SysRole findById(Long id);

    @Override
    PageResult findPage(PageRequest pageRequest);

    int saveRoleAndMenu(SysRoleMenu sysRoleMenu);

    int updateRoleAndMenuDelFlag(Long roleId);

    @Override
    List<SysRole> findAll(SysRole records);
}
