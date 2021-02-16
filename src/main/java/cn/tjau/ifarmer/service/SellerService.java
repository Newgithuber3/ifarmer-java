package cn.tjau.ifarmer.service;

import cn.tjau.ifarmer.domain.SellerInfo;
import cn.tjau.ifarmer.domain.SellerLogin;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SellerService {
    Boolean addSeller(SellerLogin sellerLogin);
    Boolean updateSellerInfo(SellerInfo sellerInfo);
    Boolean updateSellerStatus(Integer sellerID,String status,String statusMsg);
    int queryRunningCount();
    PageInfo<SellerLogin> querySellerList(Integer pageNum, Integer  pageSize);
    SellerLogin querySellerByID(Integer id);
    List<SellerLogin> querySellerByName(String name);
    SellerLogin querySellerByNameAndPassword(String name,String password);

}
