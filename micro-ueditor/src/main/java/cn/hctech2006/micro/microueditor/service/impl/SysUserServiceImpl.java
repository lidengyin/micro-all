package cn.hctech2006.micro.microueditor.service.impl;


import cn.hctech2006.micro.microueditor.bean.SysUser;
import cn.hctech2006.micro.microueditor.mapper.SysUserMapper;
import cn.hctech2006.micro.microueditor.page.MybatisPageHelper;
import cn.hctech2006.micro.microueditor.page.PageRequest;
import cn.hctech2006.micro.microueditor.page.PageResult;
import cn.hctech2006.micro.microueditor.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
//    @Autowired
//    private SysUserRoleMapper sysUserRoleMapper;
    @Override
    public int save(SysUser record) {
        return sysUserMapper.insert(record);
    }

    @Override
    public int delete(SysUser record) {
        return 0;
    }

    @Override
    public int delete(List<SysUser> records) {
        return 0;
    }

    @Override
    public SysUser findById(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, sysUserMapper, "selectAll", pageRequest.getParam("sysUser"));
    }

    @Override
    public int update(SysUser record) {
        return sysUserMapper.updateByPrimaryKey(record);
    }

    @Override
    public int update(List<SysUser> records) {
        return 0;
    }

    @Override
    public SysUser findByUsername(String username) {
        return sysUserMapper.findByUsername(username);
    }

    @Override
    public int deleteUserAndRole(Long userId) {
        return 0;
    }

    @Override
    public List<SysUser> findAll(SysUser records) {
        return sysUserMapper.selectAll(records);
    }

}
