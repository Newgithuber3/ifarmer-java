package cn.tjau.ifarmer.service.imp;

import cn.tjau.ifarmer.domain.Product;
import cn.tjau.ifarmer.domain.ProductDetail;
import cn.tjau.ifarmer.domain.ProductParameter;
import cn.tjau.ifarmer.domain.utilEntity.ProductCondition;
import cn.tjau.ifarmer.mapper.ProductDetailMapper;
import cn.tjau.ifarmer.mapper.ProductMapper;
import cn.tjau.ifarmer.mapper.ProductParameterMapper;
import cn.tjau.ifarmer.service.ProductService;
import cn.tjau.ifarmer.utils.UUIDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductParameterMapper productParameterMapper;
    @Autowired
    ProductDetailMapper productDetailMapper;

    @Override
    public Boolean addProduct(Product product) {
        product.setId(UUIDUtils.getUUIDInOrderId());
        ProductParameter productParameter=product.getProductParameter();
        productParameter.setProductid(product.getId());
        ProductDetail productDetail = product.getProductDetail();
        productDetail.setProductid(product.getId());
        try {
            productMapper.insertSelective(product);
            productParameterMapper.insertSelective(productParameter);
            productDetailMapper.insertSelective(productDetail);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteProduct(Integer pid) {
        try {
            productMapper.deleteByPrimaryKey(pid);
            productDetailMapper.deleteByProductID(pid);
            productParameterMapper.deleteByProductID(pid);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean updateProduct(Product product) {
        ProductParameter productParameter=product.getProductParameter();
        ProductDetail productDetail = product.getProductDetail();
        try{
            if(product!=null&&(product.getStoreid()!=null||product.getName()!=null||product.getCategory()!=null)){
                productMapper.updateByPrimaryKeySelective(product);
            }
            if(productParameter!=null) {
                productParameterMapper.updateByProductIDSelective(productParameter);
            }
            if(productDetail!=null) {
                productDetailMapper.updateByProductIDSelective(productDetail);
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public PageInfo<Product> queryProductList(ProductCondition condition, Integer pageNum, Integer pageSize) {
        try{
            PageHelper.startPage(pageNum,pageSize);
            List<Product> productList = productMapper.queryProductList(condition);
            PageInfo<Product> pageInfo = new PageInfo<>(productList);
            return pageInfo;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product queryProductByID(Integer pid) {
        Product product=null;
        try{
            product = productMapper.queryProductByID(pid);
        }catch (Exception e){
            System.out.println(e);
        }
        return product;
    }
}
