package cn.tjau.ifarmer.controller;

import cn.tjau.ifarmer.domain.Order;
import cn.tjau.ifarmer.domain.UserInfo;
import cn.tjau.ifarmer.domain.utilEntity.OrderCondition;
import cn.tjau.ifarmer.domain.utilEntity.OrderRequest;
import cn.tjau.ifarmer.service.OrderService;
import cn.tjau.ifarmer.utils.R;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/makeOrder")
    public R makeOrder(@RequestBody OrderRequest request){
        System.out.println(request.getAddressID().toString()+":"+request.getCartList());
        try{
            orderService.makeOrder(request.getAddressID(),request.getCartList());
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
        return R.ok();
    }

    @PostMapping(value = "/orderList")
    public R queryOrderList(@RequestBody OrderCondition condition){
        System.out.println(condition.getCreateTime());
        PageInfo<Order> orderPageInfo = orderService.queryOrderListByCondition(condition, condition.getPageNum(), condition.getPageSize());
        return R.ok().data("pageInfo",orderPageInfo);
    }

    @GetMapping(value = "/queryOrder")
    public R queryOrder(@RequestParam(value = "orderID") String orderID){
        System.out.println(orderID);
        if(orderID!=null) {
            Order order = orderService.queryOrder(Long.valueOf(orderID));
            if(order!=null) {
                return R.ok().data("order", order);
            }
            return R.error();
        }
        return R.error();
    }
}
