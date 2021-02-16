package cn.tjau.ifarmer.mapper;

import cn.tjau.ifarmer.domain.SellerLogin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SellerLoginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SellerLogin record);

    int insertSelective(SellerLogin record);

    int selectRunningCount();

    SellerLogin selectByID(Integer id);

    SellerLogin selectByNameAndPassword(String name,String password);

    List<SellerLogin> selectByName(String name);

    List<SellerLogin> selectSellerList();

    int updateByPrimaryKeySelective(SellerLogin record);

    int updateByPrimaryKey(SellerLogin record);
}