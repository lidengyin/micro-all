package cn.hctech2006.micro.microuaa.service.impl;

import cn.hctech2006.micro.microuaa.bean.SysMenu;
import cn.hctech2006.micro.microuaa.mapper.SysMenuMapper;
import cn.hctech2006.micro.microuaa.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Override
    public List<SysMenu> findMenusByUsername(String username) {
        return sysMenuMapper.selectMenusByUsername(username);
    }
}
