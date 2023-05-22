package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleService {
    public List<Role> findAllRole(Role role);

    public List<Integer> findMenuByRoleId(Integer roleId);

    public void roleContextMenu(RoleMenuVo roleMenuVo);

    public void deleteRole(Integer roleID);
}
