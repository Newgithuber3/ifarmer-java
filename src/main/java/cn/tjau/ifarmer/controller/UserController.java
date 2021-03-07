package cn.tjau.ifarmer.controller;

import cn.tjau.ifarmer.annotation.PassToken;
import cn.tjau.ifarmer.domain.Admin;
import cn.tjau.ifarmer.domain.UserInfo;
import cn.tjau.ifarmer.domain.UserLogin;
import cn.tjau.ifarmer.domain.utilEntity.UserResponse;
import cn.tjau.ifarmer.service.UserService;
import cn.tjau.ifarmer.utils.DateUtils;
import cn.tjau.ifarmer.utils.JwtUtils;
import cn.tjau.ifarmer.utils.R;
import cn.tjau.ifarmer.utils.UUIDUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@CrossOrigin
@RestController()
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @PassToken
    @PostMapping(value = "/login")
    public R login(@RequestBody UserLogin user) {
        System.out.println("shoudao");
        System.out.println(user.getUsername() + user.getPassword());
        UserLogin userLogin = userService.doLogin(user.getUsername(), user.getPassword());
        if (userLogin != null) {
            userLogin.setPassword("xxxxxxx");
            String token = JwtUtils.sign(userLogin.getId().toString());
            return R.ok().data("user", userLogin).data("token",token);
        }
        return R.error().message("用户名或密码错误");
    }

    @PassToken
    @PostMapping(value = "/register")
    public R register(UserLogin user) {
        Boolean flag = userService.register(user);
        if (flag) {
            return R.ok();
        }
        return R.error().message("用户已存在 ");
    }

    @PostMapping(value = "/add")
    public R addUser(@RequestBody Map<String, String> params) {
        System.out.println(params.get("username"));
        System.out.println(params);

        UserLogin userLogin = new UserLogin();
        userLogin.setId(UUIDUtils.getUUIDInOrderId());
        userLogin.setUsername(params.get("username"));
        userLogin.setPassword(params.get("password"));

        UserInfo userInfo = new UserInfo();
        userInfo.setUid(userLogin.getId());
        userInfo.setUsername(params.get("username"));
        userInfo.setNickname(params.get("nickname"));
        userInfo.setTelephone(params.get("telephone"));
        userInfo.setBirthday(DateUtils.strDayToDate(params.get("birthday")) );
        userInfo.setGender(params.get("gender"));
        userInfo.setAddress(params.get("address"));

        Boolean flag = userService.addUser(userLogin, userInfo);

        if (flag) {
            return R.ok();
        }
        return R.error().message("用户已存在");
    }

    @PostMapping(value = "/updateUserInfo")
    public R updateUserInfo(UserInfo user) {
        System.out.println(user);
        Boolean flag = userService.updateUserInfo(user);
        if (flag) {
            return R.ok();
        }
        return R.error();
    }

    @PostMapping(value = "/updateUser")
    public R updateUser(@RequestBody Map<String, String> params) {
        UserLogin userLogin = new UserLogin();
        userLogin.setId(Integer.valueOf(params.get("uid")));
        userLogin.setUsername(params.get("username"));
        userLogin.setPassword(params.get("password"));

        UserInfo userInfo = new UserInfo();
        userInfo.setUid(userLogin.getId());
        userInfo.setUsername(params.get("username"));
        userInfo.setNickname(params.get("nickname"));
        userInfo.setTelephone(params.get("telephone"));
        userInfo.setBirthday(DateUtils.strDayToDate(params.get("birthday")) );
        userInfo.setGender(params.get("gender"));
        userInfo.setAddress(params.get("address"));

        Boolean flag1 = userService.updateUserLogin(userLogin);
        Boolean flag2 = userService.updateUserInfo(userInfo);
        if (flag1&&flag2){
            return R.ok();
        }
        return R.error().message("更新用户信息失败");

    }

    @PostMapping(value = "/userList")
    public R userList(@RequestBody JSONObject params) {
        System.out.println(params);
        Integer pageSize =(Integer) params.get("pageSize");
        Integer pageNum = (Integer) params.get("pageNum");
        UserInfo user = (UserInfo) params.get("user");
        System.out.println(pageNum+":"+pageSize);
        System.out.println("user"+user);
        PageInfo<UserInfo> pageInfo = userService.queryUserList(user, pageNum,pageSize);
        return R.ok().data("pageInfo", pageInfo);
    }

    @GetMapping(value = "/queryUser")
    public R queryUserAll(@RequestParam(value = "uid") String uid) {
        System.out.println("uid:"+uid);
        Integer id = Integer.parseInt(uid);
        UserInfo userInfo = userService.queryUserByID(id);
        UserLogin userLogin = userService.queryUserLogin(id);
        System.out.println(userInfo);
        UserResponse userResponse = new UserResponse(userInfo, userLogin);
        return R.ok().data("user", userResponse);
    }

    @GetMapping(value = "/queryUserInfo")
    public R queryUser(@RequestParam(value = "uid") String uid) {
        UserInfo userInfo = userService.queryUserByID(Integer.parseInt(uid));
        return R.ok().data("user", userInfo);
    }

    @GetMapping(value = "/deleteUser")
    public R deleteUser(@RequestParam(value = "uid") String uid) {
        Boolean flag = userService.delete(Integer.parseInt(uid));
        if (flag) {
            return R.ok();
        }
        return R.error();
    }

    @PassToken
    @PostMapping(value = "/adminLogin")
    public R adminLogin(@RequestBody Admin admin) {
        System.out.println(admin);
        Admin login = userService.adminLogin(admin.getUsername(), admin.getPassword());
        System.out.println(login);
        if (login != null) {
            String token = JwtUtils.sign(admin.getId().toString());
            login.setPassword("xxxxxxx");
            return R.ok().data("user", login).data("token",token);
        }
        return R.error().message("用户名或密码错误！");
    }
}
