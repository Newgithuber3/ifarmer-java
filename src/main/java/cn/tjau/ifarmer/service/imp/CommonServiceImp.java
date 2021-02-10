package cn.tjau.ifarmer.service.imp;

import cn.tjau.ifarmer.domain.DeliveryAddress;
import cn.tjau.ifarmer.mapper.DeliveryAddressMapper;
import cn.tjau.ifarmer.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImp implements CommonService {
    @Autowired
    DeliveryAddressMapper addressMapper;

    @Override
    public DeliveryAddress getAddress(Integer addressID) {
        try {
            DeliveryAddress address = addressMapper.selectByPrimaryKey(addressID);
            if(address!=null){
                return address;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}