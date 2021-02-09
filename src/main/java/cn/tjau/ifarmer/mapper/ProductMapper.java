package cn.tjau.ifarmer.mapper;

import cn.tjau.ifarmer.domain.Product;
import cn.tjau.ifarmer.domain.utilEntity.ProductCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Properties;

@Mapper
public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    Product queryProductByID(Integer id);

    List<Map<String, Object>> queryProductListToMap(ProductCondition condition);

    List<Product> queryProductList(ProductCondition condition);
}