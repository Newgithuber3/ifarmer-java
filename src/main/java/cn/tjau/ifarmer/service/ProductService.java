package cn.tjau.ifarmer.service;

import cn.tjau.ifarmer.domain.Product;
import cn.tjau.ifarmer.domain.utilEntity.ProductCondition;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;


@Service
public interface ProductService {

    Boolean addProduct(Product product);
    Boolean deleteProduct(Integer pid);
    Boolean updateProduct(Product product);
    PageInfo<Product> queryProductList(ProductCondition condition, Integer pageNum, Integer pageSize);
    Product queryProductByID(Integer pid);

}
