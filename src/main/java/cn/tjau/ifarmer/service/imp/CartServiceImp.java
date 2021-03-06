package cn.tjau.ifarmer.service.imp;

import cn.tjau.ifarmer.domain.Cart;
import cn.tjau.ifarmer.domain.Product;
import cn.tjau.ifarmer.domain.utilEntity.CartListResponse;
import cn.tjau.ifarmer.mapper.CartMapper;
import cn.tjau.ifarmer.mapper.ProductMapper;
import cn.tjau.ifarmer.service.CartService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class CartServiceImp implements CartService {
    @Autowired
    CartMapper cartMapper;
    @Autowired
    ProductMapper productMapper;

    @Override
    public Boolean addCart(Cart cart) {
        Cart result=null;
        try{
             result = cartMapper.select(cart);
        }catch (Exception e){
            e.printStackTrace();
        }
        Product product = productMapper.queryProductByID(cart.getProductid());
        Date now= new Date();
        cart.setAddtime(now);
        cart.setPrice(product.getProductDetail().getPrice());
        try {
            if (result == null) {
                cartMapper.insertSelective(cart);
            } else {
                Integer number = result.getNumber() + cart.getNumber();
                cart.setNumber(number);
                cartMapper.updateSelective(cart);
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<CartListResponse> queryOrderListByIds(String[] ids) {
        List<CartListResponse> list =new ArrayList<>();
        try{
            for (String id :ids){
                Cart cart = cartMapper.selectByPrimaryKey(Integer.valueOf(id));
                Product product = productMapper.queryProductByID(cart.getProductid());
                CartListResponse cs = new CartListResponse(cart, product.getName(), product.getProductDetail().getImageurl());
                list.add(cs);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PageInfo<CartListResponse> queryCartList(Integer uid, Integer pageNum, Integer pageSize) {
        try{
            PageHelper.startPage(pageNum,pageSize);
            List<Cart> carts = cartMapper.selectCartList(uid);

            List<CartListResponse> cartListResponses = new LinkedList<>();

            for(Cart cart : carts){
                Product product = productMapper.queryProductByID(cart.getProductid());
                CartListResponse cr = new CartListResponse(cart, product.getName(), product.getProductDetail().getImageurl());
                cartListResponses.add(cr);
            }

            return new PageInfo<>(cartListResponses);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean deleteCart(Integer cartID) {
        try{
            int i = cartMapper.deleteByPrimaryKey(cartID);
        }catch (Exception e){
            e.printStackTrace();;
            return false;
        }
        return true;
    }

    @Override
    public Boolean updateNum(Integer cartID, Integer num) {
        if(num>0) {
            Cart cart = new Cart();
            cart.setId(cartID);
            cart.setNumber(num);
            try {
                cartMapper.updateSelectiveByID(cart);
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
            return true;

        }
        return false;
    }
}
