package cn.hctech2006.micro.microuaa.service;



import cn.hctech2006.micro.microuaa.bean.SysMenu;

import java.util.List;

public interface SysMenuService {
    public List<SysMenu> findMenusByUsername(String username);
}
