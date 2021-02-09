package cn.tjau.ifarmer;

import cn.tjau.ifarmer.domain.Product;
import cn.tjau.ifarmer.domain.ProductParameter;
import cn.tjau.ifarmer.domain.utilEntity.ProductCondition;
import cn.tjau.ifarmer.mapper.ProductMapper;
import cn.tjau.ifarmer.mapper.ProductParameterMapper;
import cn.tjau.ifarmer.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Map;

@MapperScan("cn.tjau.ifarmer.mapper") //扫描的mapper
@SpringBootTest
public class ProductTest {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductParameterMapper productParameterMapper;

    @Test
    public void test1() {
        try {
            Product product = productMapper.queryProductByID(101);
            System.out.println(product);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void test2() {
        try {
            Product product = productMapper.selectByPrimaryKey(102);
            System.out.println(product);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void test3() {
        ProductParameter product = productParameterMapper.selectByProductID(1);
        System.out.println(product);

    }

    @Test
    public void test4() {
        ProductCondition condition =new ProductCondition();
        condition.setCategory(1);
        List<Product> maps = productMapper.queryProductList(condition);
        System.out.println(maps);
    }

    @Test
    public void test7() {
       Product query = productMapper.queryProductByID(101);
        System.out.println(query);
    }
    @Test
    public void test8() {
        ProductParameter parameter=new ProductParameter();
        parameter.setProductid(101);
        parameter.setNote("优质大米！");
        productParameterMapper.updateByProductIDSelective(parameter);
    }
    @Test
    public void test9() {
        Date date = DateUtils.strDayToDate("2019-03-23");
        System.out.println(date);

    }
}

