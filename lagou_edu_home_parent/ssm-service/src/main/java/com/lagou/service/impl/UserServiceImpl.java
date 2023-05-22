package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.dc.pr.PRError;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {

        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());

        List<User> allUserByPage = userMapper.findAllUserByPage(userVo);

        PageInfo<User> userPageInfo = new PageInfo<>(allUserByPage);
        return userPageInfo;
    }

    @Override
    public void updateUserStatus(int id, String status) {
        userMapper.updateUserStatus(id, status);
    }

    @Override
    public User login(User user) throws Exception {
        User user2 = userMapper.login(user);
        if (user2!=null&& Md5.verify(user.getPassword(),"lagou",user2.getPassword())){
            return user2;
        }else {
            return null;
        }
    }

    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        List<Role> userRelationRoleById = userMapper.findUserRelationRoleById(id);
        return userRelationRoleById;
    }

    @Override
    public void userContextRole(UserVo userVo) {
        userMapper.deleteUserContextRole(userVo.getUserId());

        for (Integer integer : userVo.getRoleIdList()) {
            User_Role_relation userRoleRelation = new User_Role_relation();
            userRoleRelation.setUserId(userVo.getUserId());
            userRoleRelation.setRoleId(integer);

            Date date = new Date();
            userRoleRelation.setCreatedTime(date);
            userRoleRelation.setUpdatedTime(date);

            userRoleRelation.setCreatedBy("system");
            userRoleRelation.setUpdatedby("system");

            userMapper.userContextRole(userRoleRelation);
        }
    }

    @Override
    public ResponseResult getUserPermissions(Integer userId) {

        List<Role> userRelationRoleById = userMapper.findUserRelationRoleById(userId);

        ArrayList<Integer> roleIds = new ArrayList<>();
        for (Role role : userRelationRoleById) {
            roleIds.add(role.getId());
        }

        List<Menu> parentMenuByRoleId = userMapper.findParentMenuByRoleId(roleIds);

        for (Menu menu : parentMenuByRoleId) {
            List<Menu> subMenuByPid = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenuByPid);
        }

        List<Resource> resourceByRole = userMapper.findResourceByRole(roleIds);

        HashMap<String, Object> map = new HashMap<>();
        map.put("meniList",parentMenuByRoleId);
        map.put("resourceList",resourceByRole);

        return new ResponseResult(true,200,"获取信息成功",map);
    }


}
