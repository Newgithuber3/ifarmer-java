package cn.tjau.ifarmer.service;

import cn.tjau.ifarmer.domain.DeliveryAddress;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommonService {
    DeliveryAddress getAddress(Integer addressID);
    List<DeliveryAddress> getUserAddress(Integer uid);
    Boolean deleteAddress(Integer id);
    Boolean addAddress(DeliveryAddress address);
}
