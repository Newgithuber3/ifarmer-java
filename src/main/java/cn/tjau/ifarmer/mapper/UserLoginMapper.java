package cn.tjau.ifarmer.mapper;

import cn.tjau.ifarmer.domain.UserLogin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface  UserLoginMapper {
    int deleteByID(Integer id);

    UserLogin selectByUsernameAndPassword(String username,String password);

    int insert(UserLogin record);

    int insertSelective(UserLogin record);

    UserLogin selectByID(Integer id);

    int updateSelective(UserLogin record);

    int update(UserLogin record);

    UserLogin selectByUsername(String username);
}