package cn.tjau.ifarmer.service;

import cn.tjau.ifarmer.domain.Cart;
import cn.tjau.ifarmer.domain.Order;
import cn.tjau.ifarmer.domain.Product;
import cn.tjau.ifarmer.domain.utilEntity.CartListResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    Boolean addCart(Cart cart);
    List<CartListResponse> queryOrderListByIds(String [] ids);
    PageInfo<CartListResponse> queryCartList(Integer uid, Integer pageNum, Integer pageSize);
    Boolean deleteCart(Integer cartID);
    Boolean updateNum(Integer cartID,Integer num);


}
