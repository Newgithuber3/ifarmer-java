package cn.tjau.ifarmer.service.imp;

import cn.tjau.ifarmer.domain.SellerInfo;
import cn.tjau.ifarmer.domain.SellerLogin;
import cn.tjau.ifarmer.mapper.SellerInfoMapper;
import cn.tjau.ifarmer.mapper.SellerLoginMapper;
import cn.tjau.ifarmer.service.SellerService;
import cn.tjau.ifarmer.utils.UUIDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SellerServiceImp implements SellerService {

    @Autowired
    SellerLoginMapper sellerLoginMapper;
    @Autowired
    SellerInfoMapper sellerInfoMapper;

    @Override
    public Boolean addSeller(SellerLogin sellerLogin) {
        if (sellerLogin!=null){
            sellerLogin.setId(UUIDUtils.getUUIDInOrderId());
            sellerLogin.setStatus("正常");
            try {
                sellerLoginMapper.insertSelective(sellerLogin);
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
            SellerInfo sellerInfo = sellerLogin.getSellerInfo();
            if (sellerInfo!=null){
                sellerInfo.setStoreid(sellerLogin.getId());
                sellerInfo.setRegistTime(new Date());
                try {
                    sellerInfoMapper.insertSelective(sellerInfo);
                }catch(Exception e){
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        }
        return null;
    }

    @Override
    public Boolean updateSellerInfo(SellerInfo sellerInfo) {
        if(sellerInfo!=null)
        try {
            int i = sellerInfoMapper.updateByStoreIDSelective(sellerInfo);
            if (i>0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public Boolean updateSellerStatus(Integer sellerID,String status,String statusMsg) {
        if(status!=null) {
            SellerLogin sellerLogin = new SellerLogin();
            sellerLogin.setId(sellerID);
            sellerLogin.setStatus(status);
            sellerLogin.setStatusInfo(statusMsg);
            try {
                sellerLoginMapper.updateByPrimaryKeySelective(sellerLogin);
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public PageInfo<SellerLogin> querySellerList(Integer pageNum,Integer pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<SellerLogin> sellerLogins = sellerLoginMapper.selectSellerList();
            PageInfo<SellerLogin> pageInfo = new PageInfo<>(sellerLogins);
            return pageInfo;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SellerLogin querySellerByID(Integer id) {
        try {
            return sellerLoginMapper.selectByID(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SellerLogin> querySellerByName(String name) {
        try {
            return sellerLoginMapper.selectByName(name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SellerLogin querySellerByNameAndPassword(String name,String password) {
        try {
            return sellerLoginMapper.selectByNameAndPassword(name,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
