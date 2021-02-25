package cn.tjau.ifarmer.service;

import cn.tjau.ifarmer.domain.OrderReturnApply;
import cn.tjau.ifarmer.domain.utilEntity.ReturnApplyCondition;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

@Service
public interface ReturnApplyService {
    OrderReturnApply queryByID(Integer id);
    PageInfo<OrderReturnApply> queryListByCondition(ReturnApplyCondition condition);
    boolean deleteByID(Integer id);
    boolean insert(OrderReturnApply returnApply);
    boolean update(OrderReturnApply returnApply);
}
