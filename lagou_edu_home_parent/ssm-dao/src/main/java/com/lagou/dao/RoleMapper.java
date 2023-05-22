package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {
    public List<Role> findAllRole(Role role);

    public List<Integer> findMenuByRoleId(Integer roleId);

    public void deleteRoleContextMenu(Integer id);

    public void RoleContextMenu(Role_menu_relation roleMenuRelation);

    public void deleteRole(Integer roleID);

}
