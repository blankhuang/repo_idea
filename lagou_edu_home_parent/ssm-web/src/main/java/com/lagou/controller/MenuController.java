package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> list = menuService.findAllMenu();
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);
        return responseResult;
    }


    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){
        if (id==-1){
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",subMenuListByPid);

            ResponseResult responseResult = new ResponseResult(true, 200, "添加回显成功", map);
            return responseResult;
        }else {
            Menu menu= menuService.findMenuById(id);
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);
            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",subMenuListByPid);

            ResponseResult responseResult = new ResponseResult(true, 200, "修改回显成功", map);
            return responseResult;
        }

    }
}
