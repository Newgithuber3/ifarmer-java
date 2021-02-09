package cn.tjau.ifarmer.mapper;

import cn.tjau.ifarmer.domain.ProductParameter;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductParameterMapper {
    int deleteByProductID(Integer id);

    int insert(ProductParameter record);

    int insertSelective(ProductParameter record);

    ProductParameter selectByProductID(Integer id);

    int updateByProductIDSelective(ProductParameter record);

    int updateByProductID(ProductParameter record);
}