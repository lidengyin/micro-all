package cn.hctech2006.micro.microprivilege.service.impl;

import cn.hctech2006.micro.microprivilege.bean.SysRole;
import cn.hctech2006.micro.microprivilege.bean.SysRoleMenu;
import cn.hctech2006.micro.microprivilege.mapper.SysRoleMapper;
import cn.hctech2006.micro.microprivilege.mapper.SysRoleMenuMapper;
import cn.hctech2006.micro.microprivilege.page.MybatisPageHelper;
import cn.hctech2006.micro.microprivilege.page.PageRequest;
import cn.hctech2006.micro.microprivilege.page.PageResult;
import cn.hctech2006.micro.microprivilege.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Override
    public int save(SysRole record) {
        return sysRoleMapper.insert(record);
    }

    @Override
    public int delete(SysRole record) {
        return 0;
    }

    @Override
    public int delete(List<SysRole> records) {
        return 0;
    }

    @Override
    public SysRole findById(Long id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {


        return  MybatisPageHelper.findPage(pageRequest,sysRoleMapper, "selectAll", pageRequest.getParam("sysRole"));
    }

    @Override
    public int saveRoleAndMenu(SysRoleMenu record) {
        return sysRoleMenuMapper.insert(record);
    }

    @Override
    public int updateRoleAndMenuDelFlag(Long roleId) {
        return sysRoleMenuMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public List<SysRole> findAll(SysRole records) {
        return sysRoleMapper.selectAll(records);
    }

    @Override
    public int update(SysRole record) {
        return sysRoleMapper.updateByPrimaryKey(record);
    }

    @Override
    public int update(List<SysRole> records) {
        return sysRoleMapper.updateByPrimaryKey(records);
    }
}
