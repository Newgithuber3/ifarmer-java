package cn.tjau.ifarmer.mapper;

import cn.tjau.ifarmer.domain.ProductDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDetailMapper {
    int deleteByProductID(Integer id);

    int insert(ProductDetail record);

    int insertSelective(ProductDetail record);

    ProductDetail selectByProductID(Integer id);

    int updateByProductIDSelective(ProductDetail record);

    int updateByProductID(ProductDetail record);
}