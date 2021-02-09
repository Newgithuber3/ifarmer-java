package cn.tjau.ifarmer.controller;

import cn.tjau.ifarmer.domain.Admin;
import cn.tjau.ifarmer.domain.UserInfo;
import cn.tjau.ifarmer.domain.UserLogin;
import cn.tjau.ifarmer.service.UserService;
import cn.tjau.ifarmer.utils.R;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController()
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping(value = "/login")
    public R login(@RequestBody UserLogin user) {
        System.out.println("shoudao");
        System.out.println(user.getUsername() + user.getPassword());
        UserLogin userLogin = userService.doLogin(user.getUsername(), user.getPassword());
        if(userLogin!=null){
            return R.ok().data("user",userLogin);
        }
        return R.error();
    }

    @PostMapping(value = "/register")
    public R register(UserLogin user) {
        Boolean flag = userService.register(user);
        if (flag){
            return R.ok();
        }
        return R.error().message("用户已存在 ");
    }

    @PostMapping(value = "/updateUserInfo")
    public R updateUser(UserInfo user) {
        System.out.println(user);
        Boolean flag = userService.updateUserInfo(user);
        if(flag){
            return R.ok();
        }
        return R.error();
    }

    @PostMapping(value = "/userList")
    public R userList(UserInfo user,
                      @RequestParam(value = "pageNum",defaultValue = "1")String pageNum,
                      @RequestParam(value = "pageSize",defaultValue = "6")String pageSize) {
        System.out.println("shoudao:"+user);
        System.out.println(pageNum+pageSize);
        PageInfo<UserInfo> pageInfo = userService.queryUserList(user, Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        return R.ok().data("pageInfo",pageInfo);
    }

    @GetMapping(value = "/queryUser")
    public R queryUser(@RequestParam(value = "uid") String uid) {
        UserInfo userInfo = userService.queryUserByID(Integer.parseInt(uid));
        return R.ok().data("user",userInfo);
    }

    @GetMapping(value = "/deleteUser")
    public R deleteUser(@RequestParam(value = "uid") String uid) {
        Boolean flag = userService.delete(Integer.parseInt(uid));
        if (flag){
            return R.ok();
        }
        return R.error();
    }

    @PostMapping(value = "/adminLogin")
    public R adminLogin(@RequestBody Admin admin){
        System.out.println(admin);
        Admin login = userService.adminLogin(admin.getUsername(), admin.getPassword());
        System.out.println(login);
        if (login!=null){
            return R.ok().data("user",login);
        }
        return R.error().message("用户名或密码错误！");
    }
}
