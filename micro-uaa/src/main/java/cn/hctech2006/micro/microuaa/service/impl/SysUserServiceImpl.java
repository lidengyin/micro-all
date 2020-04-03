package cn.hctech2006.micro.microuaa.service.impl;

import cn.hctech2006.micro.microuaa.bean.SysMenu;
import cn.hctech2006.micro.microuaa.bean.SysUser;
import cn.hctech2006.micro.microuaa.bean.SysUserRole;
import cn.hctech2006.micro.microuaa.exception.UserLoginException;
import cn.hctech2006.micro.microuaa.jwt.JWT;
import cn.hctech2006.micro.microuaa.jwt.UserLoginDTO;
import cn.hctech2006.micro.microuaa.jwt.fegin.AuthServiceClient;
import cn.hctech2006.micro.microuaa.mapper.SysUserMapper;
import cn.hctech2006.micro.microuaa.mapper.SysUserRoleMapper;
import cn.hctech2006.micro.microuaa.service.SysMenuService;
import cn.hctech2006.micro.microuaa.service.SysUserService;
import cn.hctech2006.micro.microuaa.util.PassWordEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysMenuService sysMenuService;
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

//    @Override
//    public PageResult findPage(PageRequest pageRequest) {
//        return MybatisPageHelper.findPage(pageRequest, sysUserMapper, "selectAll", pageRequest.getParam("sysUser"));
//    }

    @Override
    public int update(SysUser record) {
        return sysUserMapper.updateByPrimaryKey(record);
    }

    @Override
    public int update(List<SysUser> records) {
        return 0;
    }


    @Override
    public Set<String> findPermissions(String username) {
        Set<String> perms = new HashSet<>();
        List<SysMenu> sysMenus = sysMenuService.findMenusByUsername(username);
        for (SysMenu sysMenu : sysMenus){
            if(sysMenu.getPerms() != null && sysMenu.getPerms() != ""){
                perms.add(sysMenu.getPerms());
            }
        }
        return perms;
    }
    @Override
    public SysUser findByUserName(String username) {
        return sysUserMapper.findByUserName(username);
    }
    @Autowired
    AuthServiceClient authServiceClient;
    @Override
    public UserLoginDTO login(String username, String password) {
        SysUser sysUser = sysUserMapper.findByUserName(username);
        if(sysUser == null){
            throw new UserLoginException("该用户不存在");

        }
        System.out.println(PassWordEncoderUtils.matches(password, sysUser.getPassword()));
        System.out.println(sysUser.getPassword());
        if (!PassWordEncoderUtils.matches(password, sysUser.getPassword())){
            throw new UserLoginException("密码错误");
        }
        JWT jwt = authServiceClient.getToken("Basic dXNlci1zZXJ2aWNlOjEyMzQ1Ng==","password",username,password );
        if (jwt == null){
            throw new UserLoginException("error Internal");
        }
        UserLoginDTO userLoginDTO = new UserLoginDTO(jwt, sysUser);
        return userLoginDTO;
    }

    @Override
    public int saveUserAndRole(SysUserRole sysUserRole) {
        return sysUserRoleMapper.insert(sysUserRole);
    }

    @Override
    public int deleteUserAndRole(Long userId) {
        return sysUserRoleMapper.deleteByPrimaryKey(userId);
    }
}