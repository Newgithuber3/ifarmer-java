package cn.tjau.ifarmer;

import cn.tjau.ifarmer.domain.UserInfo;
import cn.tjau.ifarmer.domain.UserLogin;
import cn.tjau.ifarmer.mapper.UserInfoMapper;
import cn.tjau.ifarmer.mapper.UserLoginMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@MapperScan("cn.tjau.ifarmer.mapper") //扫描的mapper
@SpringBootTest
class IfarmerApplicationTests {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserLoginMapper userLoginMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void mappeTest1() {
        UserLogin userLogin = new UserLogin();
        userLogin.setId(1);
        userLogin.setUsername("Tom");
        userLogin.setPassword("123456");
        System.out.println("userLogin");
        userLoginMapper.insert(userLogin);

    }

    @Test
    void mappeTest2() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUid(1);
        userInfo.setUsername("Tom");
        userInfo.setNickname("King");
        userInfoMapper.insert(userInfo);

    }

    @Test
    void mappeTest4() {
        UserLogin userLogin=userLoginMapper.selectByUsernameAndPassword("Tom","123456");
        System.out.println(userLogin);

    }

}