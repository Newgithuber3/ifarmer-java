package cn.tjau.ifarmer.service;

import cn.tjau.ifarmer.domain.Admin;
import cn.tjau.ifarmer.domain.UserInfo;
import cn.tjau.ifarmer.domain.UserLogin;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {

    UserLogin doLogin(String username, String password);
    Boolean register(UserLogin userLogin);
    Boolean addUser(UserLogin login,UserInfo userInfo);
    Boolean updateUserInfo(UserInfo userInfo);
    Boolean updateUserLogin(UserLogin login);
    Boolean delete(Integer uid);
    PageInfo<UserInfo> queryUserList(UserInfo user,Integer pageNum,Integer pageSize);
    UserInfo queryUserByID(Integer uid);
    UserLogin queryUserLogin(Integer id);
    Admin adminLogin(String username, String password);


}
