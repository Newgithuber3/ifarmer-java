package cn.tjau.ifarmer.controller;

import cn.tjau.ifarmer.domain.Cart;
import cn.tjau.ifarmer.domain.utilEntity.CartListResponse;
import cn.tjau.ifarmer.service.CartService;
import cn.tjau.ifarmer.utils.JwtUtils;
import cn.tjau.ifarmer.utils.R;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    HttpServletRequest request;

    @GetMapping(value = "/addCart")
    public R addCart(@RequestParam(value = "uid") String uid,
                        @RequestParam(value = "productid") String productid,
                        @RequestParam(value = "number") String number){
        Cart cart = new Cart();
        cart.setUid(Integer.parseInt(uid));
        cart.setProductid(Integer.parseInt(productid));
        cart.setNumber(Integer.parseInt(number));
        try{
            cartService.addCart(cart);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
       return R.ok();
    }

    @GetMapping(value = "/cartList")
    public R queryCartList(@RequestParam(value = "pageNum",defaultValue = "1")String pageNum,
                           @RequestParam(value = "pageSize",defaultValue = "6")String pageSize){
        String token = request.getHeader("token");
        String uid = JwtUtils.getUserId(token);
        if (Objects.equals(uid, "") || uid == null){
            return R.error();
        }
        PageInfo<CartListResponse> cartPageInfo = cartService.queryCartList(Integer.parseInt(uid), Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        return R.ok().data("pageInfo",cartPageInfo);
    }

    @PostMapping(value = "/carts")
    public R queryCartListByIds(@RequestBody String[] ids){
        List<CartListResponse> list = cartService.queryOrderListByIds(ids);
        return R.ok().data("list",list);
    }

    @GetMapping(value = "/delete")
    public R deleteCart(@RequestParam(value = "cartID") String cartID){
        Boolean flag = cartService.deleteCart(Integer.parseInt(cartID));
        if(flag){
            return R.ok();
        }
        return R.error();
    }

    @GetMapping(value = "/updateNum")
    public R updateNum(@RequestParam(value = "cartID") Integer cartID,@RequestParam(value = "num") Integer num){
        System.out.println(cartID.toString()+":"+num.toString());
        Boolean flag = cartService.updateNum(cartID,num);
        if(flag){
            return R.ok().message("更新成功");
        }
        return R.error().message("更新失败");    }

}
