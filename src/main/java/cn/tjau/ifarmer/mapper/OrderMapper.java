package cn.tjau.ifarmer.mapper;

import cn.tjau.ifarmer.domain.Order;
import cn.tjau.ifarmer.domain.utilEntity.OrderCondition;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    List<Order> selectOrderListByCondition(OrderCondition condition);

    Order selectByUsernameAndTime(String username, Date date);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

}