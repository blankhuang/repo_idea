package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.Role_menu_relation;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> list = roleMapper.findAllRole(role);
        return list;
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {
        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleId);
        return menuByRoleId;
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        for (Integer integer : roleMenuVo.getMenuIdList()) {
            Role_menu_relation roleMenuRelation = new Role_menu_relation();
            roleMenuRelation.setMenuId(integer);
            roleMenuRelation.setRoleId(roleMenuVo.getRoleId());

            Date date = new Date();
            roleMenuRelation.setCreatedTime(date);
            roleMenuRelation.setUpdatedTime(date);

            roleMenuRelation.setCreatedBy("system");
            roleMenuRelation.setUpdatedby("system");

            roleMapper.RoleContextMenu(roleMenuRelation);
        }



    }

    @Override
    public void deleteRole(Integer roleID) {

        roleMapper.deleteRoleContextMenu(roleID);

        roleMapper.deleteRole(roleID);
    }

}
