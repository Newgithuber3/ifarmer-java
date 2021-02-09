package cn.tjau.ifarmer.controller;


import cn.tjau.ifarmer.domain.DeliveryAddress;
import cn.tjau.ifarmer.service.CommonService;
import cn.tjau.ifarmer.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/common")
public class CommonController {
    @Autowired
    CommonService commonService;

   @GetMapping(value = "/getAddress")
   public R getAddress(@RequestParam(value = "addressID") String addressID){
       System.out.println("sss"+ addressID);
       DeliveryAddress address = commonService.getAddress(Integer.valueOf(addressID));
       if (address!=null){
           return R.ok().data("address",address);
       }
       return R.error();
   }
}
