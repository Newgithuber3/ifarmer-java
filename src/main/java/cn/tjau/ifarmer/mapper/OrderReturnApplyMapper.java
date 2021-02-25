package cn.tjau.ifarmer.mapper;

import cn.tjau.ifarmer.domain.OrderReturnApply;
import cn.tjau.ifarmer.domain.utilEntity.ReturnApplyCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderReturnApplyMapper {
    OrderReturnApply selectByID(Integer id);

    List<OrderReturnApply> selectListByCondition(ReturnApplyCondition condition);

    int deleteByID(Integer id);

    int insertSelective(OrderReturnApply returnApply);

    int updateByPrimaryKeySelective(OrderReturnApply returnApply);
}
