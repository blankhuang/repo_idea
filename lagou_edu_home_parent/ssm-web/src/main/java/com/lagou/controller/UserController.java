package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){
        PageInfo allUserByPage = userService.findAllUserByPage(userVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", allUserByPage);
        return responseResult;
    }

    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(@RequestParam int id,@RequestParam String status){
        if ("ENABLE".equals(status)){
            status="DISABLE";
        }else {
            status="ENABLE";
        }
        userService.updateUserStatus(id, status);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", status);
        return responseResult;
    }
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User user1 = userService.login(user);
        if (user1!=null){
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",user1.getId());

            HashMap<String , Object> map = new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id",user1.getId());
            map.put("user",user);
            return new ResponseResult(true,1,"登录成功",map);
        }else {
            return new ResponseResult(true,400,"用户名密码错误",null);
        }
    }

    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(Integer id){
        List<Role> userRelationRoleById = userService.findUserRelationRoleById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", userRelationRoleById);
        return responseResult;
    }

    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){
        userService.userContextRole(userVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "分配角色成功成功", null);
        return responseResult;
    }
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){

        String header_token = request.getHeader("Authorization");

        String session_token =(String) request.getSession().getAttribute("access_token");

        if (header_token.equals(session_token)){
            Integer user_id =(Integer)request.getSession().getAttribute("user_id");
            ResponseResult userPermissions = userService.getUserPermissions(user_id);
            return userPermissions;
        }else {
           return new ResponseResult(false,400,"获取失败",null);
        }

    }
}
