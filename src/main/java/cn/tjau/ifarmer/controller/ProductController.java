package cn.tjau.ifarmer.controller;
import cn.tjau.ifarmer.domain.Product;
import cn.tjau.ifarmer.domain.utilEntity.ProductCondition;
import cn.tjau.ifarmer.service.ProductService;
import cn.tjau.ifarmer.utils.R;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping(value = "/addProduct")
    public R addProduct(@RequestBody Product product){
        System.out.println(product);
        /*Boolean flag = productService.addProduct(product);
        if(flag){
            return R.ok();
        }*/
        return R.error();
    }

    @GetMapping(value = "/deleteProduct")
    public R deleteProduct(@RequestParam(value = "id") String id){
        Boolean flag = productService.deleteProduct(Integer.parseInt(id));
        if(flag){
            return R.ok();
        }
        return R.error();
    }

    @PostMapping(value = "/updateProduct")
    public R updateProduct(@RequestBody Product product){
        System.out.println(product);
        Boolean flag = productService.updateProduct(product);
        if (flag){
            return R.ok();
        }
        return R.error();
    }

    @GetMapping(value = "/queryProduct")
    public R queryProduct(@RequestParam(value = "id") String id){
        Product product = productService.queryProductByID(Integer.parseInt(id));
        return R.ok().data("product",product);
    }

    @PostMapping(value = "/productList")
    public R queryProductList(@RequestBody ProductCondition condition){
        System.out.println(condition);

        PageInfo<Product> productPageInfo = productService.queryProductList(condition,condition.getPageNum(),condition.getPageSize());
        return R.ok().data("pageInfo",productPageInfo);
    }

}
