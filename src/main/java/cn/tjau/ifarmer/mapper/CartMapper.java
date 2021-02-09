package cn.tjau.ifarmer.mapper;

import cn.tjau.ifarmer.domain.Cart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    /**
     * 查询商品是否已经添加在该用户的购物车中
     * @param cart{cid,productid}
     * @return
     */
    Cart select(Cart cart);

    /**
     * 添加到购物车
     * @param cart
     * @return
     */
    int insertSelective(Cart cart);

    /**
     * 更新购物车
     * @param cart
     * @return
     */
    int updateSelective(Cart cart);

    List<Cart> selectCartList(Integer uid);

    int updateSelectiveByID(Cart cart);
}