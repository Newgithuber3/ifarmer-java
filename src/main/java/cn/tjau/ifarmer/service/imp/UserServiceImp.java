package cn.tjau.ifarmer.service.imp;

import cn.tjau.ifarmer.domain.Admin;
import cn.tjau.ifarmer.domain.UserInfo;
import cn.tjau.ifarmer.domain.UserLogin;
import cn.tjau.ifarmer.mapper.AdminMapper;
import cn.tjau.ifarmer.mapper.UserInfoMapper;
import cn.tjau.ifarmer.mapper.UserLoginMapper;
import cn.tjau.ifarmer.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserLoginMapper userLoginMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public UserLogin doLogin(String username, String password) {
        UserLogin userLogin = null;
        System.out.println(username + password);
        try {
            userLogin = userLoginMapper.selectByUsernameAndPassword(username, password);
            System.out.println(userLogin);
        } catch (Exception e) {
            System.out.println(e);
        }
        return userLogin;
    }

    @Override
    public Boolean register(UserLogin userLogin) {
        UserLogin user = null;
        try {
            user = userLoginMapper.selectByUsername(userLogin.getUsername());
            System.out.println(user);
        } catch (Exception e) {
            System.out.println(e);
        }
        if (user == null) {
            try {
                userLoginMapper.insertSelective(userLogin);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean updateUserInfo(UserInfo userInfo) {
        UserInfo userInfo1 = null;
        try {
            userInfo1 = userInfoMapper.selectByUid(userInfo.getUid());
            System.out.println(userInfo1);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            if (userInfo1 == null) {
                userInfoMapper.insertSelective(userInfo);
            } else {
                userInfoMapper.updateByUidSelective(userInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }

    @Override
    public Boolean delete(Integer uid) {
        try {
            userInfoMapper.deleteByUid(uid);
            userLoginMapper.deleteByID(uid);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public PageInfo<UserInfo> queryUserList(UserInfo user, Integer pageNum, Integer pageSize) {
        System.out.println("Service:" + user + "page" + pageNum + ":" + pageSize);
        List<UserInfo> userInfoList = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            userInfoList = userInfoMapper.queryUserList(user);
            System.out.println(userInfoList);
            PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(userInfoList);
            return pageInfo;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public UserInfo queryUserByID(Integer uid) {
        UserInfo user = null;
        try {
            user = userInfoMapper.selectByUid(uid);
        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }

    @Override
    public Admin adminLogin(String username, String password) {
        try {
            return adminMapper.selectByUsernameAndPassword(username, password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
