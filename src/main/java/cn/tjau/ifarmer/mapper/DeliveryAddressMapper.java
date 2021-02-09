package cn.tjau.ifarmer.mapper;

import cn.tjau.ifarmer.domain.DeliveryAddress;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeliveryAddressMapper {
    int insertSelective(DeliveryAddress deliveryAddress);
    DeliveryAddress selectByPrimaryKey(Integer id);
    int deleteByPrimaryKey(Integer id);
    DeliveryAddress selectByUid(Integer uid);
    int updateByUidSelective(DeliveryAddress address);
}
