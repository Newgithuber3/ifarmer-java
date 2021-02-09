package cn.tjau.ifarmer.service;

import cn.tjau.ifarmer.domain.DeliveryAddress;
import org.springframework.stereotype.Service;

@Service
public interface CommonService {
    DeliveryAddress getAddress(Integer addressID);
}
