package com.lagou.dao;

import com.lagou.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    public List<User> findAllUserByPage(UserVo userVo);

    public void updateUserStatus(@Param("id") int id,@Param("status") String status);

    public User login(User user);

    public List<Role> findUserRelationRoleById(Integer id);

    public void deleteUserContextRole(Integer userId);

    public void userContextRole(User_Role_relation userRoleRelation);

    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    public List<Menu> findSubMenuByPid(Integer pid);

    public List<Resource> findResourceByRole(List<Integer> ids);
}
