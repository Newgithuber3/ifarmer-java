package cn.tjau.ifarmer.controller;

import cn.tjau.ifarmer.domain.Cart;
import cn.tjau.ifarmer.domain.utilEntity.CartListResponse;
import cn.tjau.ifarmer.service.CartService;
import cn.tjau.ifarmer.utils.R;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    CartService cartService;

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
    public R queryCartList(@RequestParam(value = "uid") String uid,
                           @RequestParam(value = "pageNum",defaultValue = "1")String pageNum,
                           @RequestParam(value = "pageSize",defaultValue = "6")String pageSize){

        PageInfo<CartListResponse> cartPageInfo = cartService.queryCartList(Integer.parseInt(uid), Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        return R.ok().data("pageInfo",cartPageInfo);
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
