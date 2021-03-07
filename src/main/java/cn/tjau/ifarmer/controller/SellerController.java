package cn.tjau.ifarmer.controller;

import cn.tjau.ifarmer.annotation.PassToken;
import cn.tjau.ifarmer.domain.SellerInfo;
import cn.tjau.ifarmer.domain.SellerLogin;
import cn.tjau.ifarmer.service.SellerService;
import cn.tjau.ifarmer.utils.DateUtils;
import cn.tjau.ifarmer.utils.JwtUtils;
import cn.tjau.ifarmer.utils.R;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Date;
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

    @PassToken
    @PostMapping(value = "/login")
    public R login(@RequestBody JSONObject params){
        String loginName =(String) params.get("name");
        String password = (String) params.get("password");
        SellerLogin sellerLogin = sellerService.querySellerByNameAndPassword(loginName, password);
        if (sellerLogin!=null){
            sellerLogin.setPassword("xxxxxxx");
            String token = JwtUtils.sign(sellerLogin.getId().toString());
            if(sellerLogin.getStatus().equals("正常")) {
                return R.ok().data("status","normal").data("user", sellerLogin).data("token",token);
            }else if(sellerLogin.getStatus().equals("未激活")){
                return R.ok().data("status","inactive");
            }else {
                return R.error().message(sellerLogin.getStatusInfo());
            }
        }
        return R.error().message("登录失败! 用户名或密码错误！");
    }

    @GetMapping(value = "/queryByName")
    public R queryByName(@RequestParam(value = "storeName") String storeName){
        System.out.println(storeName);
        List<SellerLogin> sellerLogins = sellerService.querySellerByName(storeName);
        return R.ok().data("sellers",sellerLogins);
    }

    @GetMapping(value = "/queryByID")
    public R queryByID(@RequestParam(value = "sellerID") Integer id){
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

    @PostMapping(value = "/updateStatus")
    public R updateStatus(@RequestBody JSONObject params){
        Integer sellerID = (Integer) params.get("sellerID");
        String type = (String) params.get("type");
        String statusMsg;
        Boolean flag = false;
        if (type.equals("banned")){
            try {
                String msg = (String) params.get("statusMsg");
                JSONArray bannedTime = params.getJSONArray("bannedTime");
                List<Date> dates = JSONObject.parseArray(bannedTime.toJSONString(), Date.class);
                Date startDate = dates.get(0);
                Date endDate = dates.get(1);
                statusMsg = "因"+msg+"而封禁 "+ "从"+ DateUtils.getDateStr(startDate)+
                        "至"+ DateUtils.getDateStr(endDate);
                flag = sellerService.updateSellerStatus(sellerID, "封禁中", statusMsg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            flag = sellerService.updateSellerStatus(sellerID,"正常","");
        }

        if (flag){
            return R.ok();
        }
        return R.error();
    }
}
