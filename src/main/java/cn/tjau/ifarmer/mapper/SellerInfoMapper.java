package cn.tjau.ifarmer.mapper;

import cn.tjau.ifarmer.domain.SellerInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SellerInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SellerInfo record);

    int insertSelective(SellerInfo record);

    SellerInfo selectByPrimaryKey(Integer id);

    int updateByStoreIDSelective(SellerInfo record);

    int updateByPrimaryKey(SellerInfo record);
}