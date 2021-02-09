package cn.tjau.ifarmer.mapper;

import cn.tjau.ifarmer.domain.UserInfo;
import cn.tjau.ifarmer.domain.UserLogin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    int deleteByUid(Integer uid);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByUid(Integer uid);

    List<UserInfo> queryUserList(UserInfo userpara);

    int updateByUidSelective(UserInfo record);

    int updateByUid(UserInfo record);


}