package cn.tjau.ifarmer.service.imp;

import cn.tjau.ifarmer.domain.OrderReturnApply;
import cn.tjau.ifarmer.domain.utilEntity.ReturnApplyCondition;
import cn.tjau.ifarmer.mapper.OrderReturnApplyMapper;
import cn.tjau.ifarmer.service.ReturnApplyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReturnApplyServiceImp implements ReturnApplyService {
    @Autowired
    OrderReturnApplyMapper mapper;

    @Override
    public OrderReturnApply queryByID(Integer id) {
        return mapper.selectByID(id);
    }

    @Override
    public PageInfo<OrderReturnApply> queryListByCondition(ReturnApplyCondition condition) {
        try {
            PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
            List<OrderReturnApply> applyList = mapper.selectListByCondition(condition);
            return new PageInfo<>(applyList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteByID(Integer id) {
        return mapper.deleteByID(id) > 0;
    }

    @Override
    public boolean insert(OrderReturnApply returnApply) {
        return mapper.insertSelective(returnApply) > 0;
    }

    @Override
    public boolean update(OrderReturnApply returnApply) {
        return mapper.updateByPrimaryKeySelective(returnApply) > 0;
    }
}
