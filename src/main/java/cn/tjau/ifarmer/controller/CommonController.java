package cn.tjau.ifarmer.controller;


import cn.tjau.ifarmer.domain.DeliveryAddress;
import cn.tjau.ifarmer.service.CommonService;
import cn.tjau.ifarmer.utils.JwtUtils;
import cn.tjau.ifarmer.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/common")
public class CommonController {
    @Autowired
    CommonService commonService;
    @Autowired
    HttpServletRequest request;

   @GetMapping(value = "/getAddress")
   public R getAddress(@RequestParam(value = "addressID") String addressID){
       System.out.println("sss"+ addressID);
       DeliveryAddress address = commonService.getAddress(Integer.valueOf(addressID));
       if (address!=null){
           return R.ok().data("address",address);
       }
       return R.error();
   }

    @GetMapping(value = "/getUserAddress")
    public R getUserAddress(){
        String token = request.getHeader("token");
        String userId = JwtUtils.getUserId(token);
        if (userId!=null) {
            List<DeliveryAddress> userAddress = commonService.getUserAddress(Integer.parseInt(userId));
            return R.ok().data("addressList",userAddress);
        }
        return R.error();
    }
}
