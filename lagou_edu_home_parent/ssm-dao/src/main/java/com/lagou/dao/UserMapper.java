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
    
    public List<Resource> findResourceByRole2(List<Integer> ids);
    
    public void test11();
    public void test21();
    public void test31();
    public void test41();
    public void test51();
    public void test61();
    public void test71();
}
