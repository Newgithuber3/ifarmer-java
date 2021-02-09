package cn.tjau.ifarmer.mapper;

import cn.tjau.ifarmer.domain.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    Admin selectByUsernameAndPassword(String username, String password);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

}