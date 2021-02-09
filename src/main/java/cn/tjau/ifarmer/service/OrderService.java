package cn.tjau.ifarmer.service;

import cn.tjau.ifarmer.domain.Order;
import cn.tjau.ifarmer.domain.utilEntity.OrderCondition;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public interface OrderService {
    Boolean makeOrder(Integer addressID,List<Integer> cartIdList)throws Exception;
    PageInfo<Order> queryOrderListByCondition(OrderCondition condition, Integer pageNUm, Integer pageSize );
    Order queryOrder(Long id);
}
