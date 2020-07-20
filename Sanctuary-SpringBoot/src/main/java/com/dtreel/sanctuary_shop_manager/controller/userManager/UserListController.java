package com.dtreel.sanctuary_shop_manager.controller.userManager;


import com.dtreel.sanctuary_shop_manager.domain.ResponseVO;
import com.dtreel.sanctuary_shop_manager.domain.eneity.UserDO;
import com.dtreel.sanctuary_shop_manager.service.RoleService;
import com.dtreel.sanctuary_shop_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author DtreeL
 */
@RestController
@RequestMapping("/home/userManager/userList/api/")
public class UserListController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    //获取用户信息
    @GetMapping("user")
    public ResponseVO listUser(String keyword){
        return userService.listUser(keyword);
    }

    //获取所有职位
    @GetMapping("allRole")
    public ResponseVO listAllRole(){
        return roleService.listAllRole();
    }

    //增加用户
    @PostMapping("user")
    public ResponseVO insertUser(@RequestBody UserDO user) {
        return userService.insertUser(user);
    }

    //修改用户
    @PutMapping("user")
    public ResponseVO updateUser(Integer[] addRoleIds, Integer[] deleteRoleIds, @RequestBody UserDO user) {
        return userService.updateUser(user,addRoleIds,deleteRoleIds);
    }

    //删除用户
    @DeleteMapping("user/{id}")
    public ResponseVO deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }
}
