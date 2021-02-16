package cn.tjau.ifarmer.controller;

import cn.tjau.ifarmer.domain.SellerInfo;
import cn.tjau.ifarmer.domain.SellerLogin;
import cn.tjau.ifarmer.service.SellerService;
import cn.tjau.ifarmer.utils.R;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController()
@RequestMapping(value = "/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @PostMapping(value = "/add")
    public R addSeller(@RequestParam(value = "loginName")String loginName,
                       @RequestParam(value = "password")String password,
                       @RequestParam(value = "storeName")String storeName){
        SellerLogin sellerLogin = new SellerLogin();
        sellerLogin.setName(loginName);
        sellerLogin.setPassword(password);
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setStorename(storeName);
        sellerLogin.setSellerInfo(sellerInfo);
        Boolean flag = sellerService.addSeller(sellerLogin);
        if(flag){
            return R.ok();
        }
        return R.error();
    }

    @PostMapping(value = "/login")
    public R login(@RequestParam(value = "loginName")String loginName,
                   @RequestParam(value = "password")String password){
        SellerLogin sellerLogin = sellerService.querySellerByNameAndPassword(loginName, password);
        if (sellerLogin!=null){
            if(sellerLogin.getStatus().equals("正常")) {
                return R.ok().data("user", sellerLogin);
            }
        }
        return R.error().message("登录失败");
    }

    @GetMapping(value = "/queryByName")
    public R queryByName(@RequestParam(value = "storeName") String storeName){
        List<SellerLogin> sellerLogins = sellerService.querySellerByName(storeName);
        return R.ok().data("sellers",sellerLogins);
    }

    @GetMapping(value = "/queryByID")
    public R queryByID(@RequestParam(value = "seller ID") Integer id){
        SellerLogin sellerLogin = sellerService.querySellerByID(id);
        return R.ok().data("seller",sellerLogin);
    }

    @GetMapping(value = "/runningCount")
    public R queryRunningCount(){
        int i = sellerService.queryRunningCount();
        return R.ok().data("runningCount", i);
    }

    @GetMapping(value = "/sellerList")
    public R querySellerList( @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "6")Integer pageSize){
        PageInfo<SellerLogin> pageInfo = sellerService.querySellerList(pageNum, pageSize);
        return R.ok().data("pageInfo",pageInfo);

    }

    @GetMapping(value = "/updateStoreName")
    public R updateStoreName(@RequestParam(value = "storeName")String storeName){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setStorename(storeName);
        Boolean flag = sellerService.updateSellerInfo(sellerInfo);
        if (flag){
            return R.ok();
        }
        return R.error();
    }
    @GetMapping(value = "/updateStatus")
    public R updateStatus(@RequestParam(value = "sellerID") Integer sellerID,
                          @RequestParam(value = "status") String status,
                          @RequestParam(value = "statusMsg") String statusMsg){
        Boolean flag = sellerService.updateSellerStatus(sellerID, status, statusMsg);
        if (flag){
            return R.ok();
        }
        return R.ok();
    }
}
