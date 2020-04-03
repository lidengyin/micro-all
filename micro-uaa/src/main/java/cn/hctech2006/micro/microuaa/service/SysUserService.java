package cn.hctech2006.micro.microuaa.service;



import cn.hctech2006.micro.microuaa.bean.SysUser;
import cn.hctech2006.micro.microuaa.bean.SysUserRole;
import cn.hctech2006.micro.microuaa.jwt.UserLoginDTO;

import java.util.List;
import java.util.Set;

public interface SysUserService extends CurdService<SysUser>{
    @Override
    int save(SysUser record);

    @Override
    int delete(SysUser record);

    @Override
    int delete(List<SysUser> records);

    @Override
    SysUser findById(Long id);


    @Override
    int update(SysUser record);

    @Override
    int update(List<SysUser> records);

    public SysUser findByUserName(String username);
    public Set<String> findPermissions(String username);
    UserLoginDTO login(String username, String password);

    int saveUserAndRole(SysUserRole sysUserRole);

    int deleteUserAndRole(Long userId);
}
