package cn.tjau.ifarmer.service.imp;

import cn.tjau.ifarmer.domain.*;
import cn.tjau.ifarmer.domain.utilEntity.OrderCondition;
import cn.tjau.ifarmer.mapper.*;
import cn.tjau.ifarmer.service.OrderService;
import cn.tjau.ifarmer.utils.DateUtils;
import cn.tjau.ifarmer.utils.UUIDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    CartMapper cartMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderDetailMapper orderDetailMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    DeliveryAddressMapper deliveryAddressMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductDetailMapper productDetailMapper;

    @Override
    public Boolean makeOrder(Integer addressID, List<Integer> cartIdList) throws Exception {
        /**
         * 1.通过用户id查询购物车，从购物车获取数据
         * 2.计算出订单的总价（获得总价）
         * 3.生成订单
         * 4、将订单批量插入到数据库
         * 5、减少产品库存
         * 6、清空购物车
         */
        for (Integer cartID : cartIdList) {
            //获取购物车和商品信息
            Cart cart = cartMapper.selectByPrimaryKey(cartID);
            UserInfo userInfo = userInfoMapper.selectByUid(cart.getUid());
            Product product = productMapper.queryProductByID(cart.getProductid());

            //创建订单
            Order order = new Order();

            order.setId((UUIDUtils.getUUIDInOrderId().longValue()));
            order.setStatus("交易进行中");
            order.setIspay(false);

            Double money = cart.getPrice() * cart.getNumber();
            order.setMoney(money);

            order.setBuyername(userInfo.getUsername());
            order.setBuyerphone(userInfo.getTelephone());
            order.setBuyeraddress(addressID);

            order.setCreatetime(new Date());
            order.setUpdatetime(new Date());
            order.setProductid(cart.getProductid());
            int i = 0;
            try {
                i = orderMapper.insertSelective(order);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (i == 0) {
                throw new Exception("创建订单失败!");
            }
            //写入订单细节
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderid(order.getId());
            orderDetail.setProductid(cart.getProductid());
            orderDetail.setProductname(product.getName());
            orderDetail.setProductnumber(cart.getNumber());
            orderDetail.setProductprice(cart.getPrice());
            orderDetail.setProductimageUrl(product.getProductDetail().getImageurl());
            try {
                orderDetailMapper.insertSelective(orderDetail);
            } catch (Exception e) {
                throw new Exception("写入订单细节失败");
            }

            // 减少库存
            ProductDetail productDetail = new ProductDetail();
            productDetail.setProductid(cart.getProductid());
            Integer num = product.getProductDetail().getNumber() - cart.getNumber();
            productDetail.setNumber(num);
            try {
                productDetailMapper.updateByProductIDSelective(productDetail);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 清空购物车
            try {
                cartMapper.deleteByPrimaryKey(cartID);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return true;
    }

    @Override
    public PageInfo<Order> queryOrderListByCondition(OrderCondition condition, Integer pageNUm, Integer pageSize) {
        List<Order> orders = null;
        try {
             orders = orderMapper.selectOrderListByCondition(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (orders!=null){
            for (Order order : orders){
                try {
                    Product product = productMapper.queryProductByID(order.getProductid());
                    OrderDetail orderDetail = orderDetailMapper.selectByOrderID(order.getId());
                    order.setProduct(product);
                    order.setOrderDetail(orderDetail);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        PageHelper.startPage(pageNUm,pageSize);
        PageInfo<Order> pageInfo = new PageInfo<>(orders);

        return pageInfo;
    }

    @Override
    public List<Order> queryOrderListByIds(String[] ids) {
        List<Order> list = new ArrayList<>();
        try {
            for (String id : ids) {
                Order order = queryOrder(Long.valueOf(id));
                list.add(order);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Order queryOrder(Long id) {
        Order order = null;
        try {
            order = orderMapper.selectByPrimaryKey(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (order!=null){
            try {
                Product product = productMapper.queryProductByID(order.getProductid());
                OrderDetail orderDetail = orderDetailMapper.selectByOrderID(order.getId());
                order.setProduct(product);
                order.setOrderDetail(orderDetail);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return order;
    }
}
