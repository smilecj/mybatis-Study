package cn.smilehalocj.dao;

import cn.smilehalocj.entity.Goods;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

public interface GoodsDAO {
    @Select ("select * from t_goods where current_price between #{min} and #{max} order by current_price limit 0,#{limt}")
    public List<Goods> selectByPriceRange(@Param("min") Float min, @Param("max") Float max, @Param("limt") Integer limt);

    @Insert(" INSERT INTO t_goods(title,sub_title,original_cost,current_price,discount,is_free_delivery,category_id) VALUE(#{title},#{subTitle},#{originalCost},#{currentPrice},#{discount},#{isFreeDelivery},#{categoryId});")
    //<SelectKey>
    @SelectKey(statement = "select last_insert_id()",before = false, keyProperty = "goodsId",resultType = Integer.class)
    public int insert(Goods goods);
}
