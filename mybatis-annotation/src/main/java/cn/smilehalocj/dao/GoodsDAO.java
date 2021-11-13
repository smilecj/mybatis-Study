package cn.smilehalocj.dao;

import cn.smilehalocj.dto.GoodsDTO;
import cn.smilehalocj.entity.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GoodsDAO {
    @Select ("select * from t_goods where current_price between #{min} and #{max} order by current_price limit 0,#{limt}")
    public List<Goods> selectByPriceRange(@Param("min") Float min, @Param("max") Float max, @Param("limt") Integer limt);

    @Insert(" INSERT INTO t_goods(title,sub_title,original_cost,current_price,discount,is_free_delivery,category_id) VALUE(#{title},#{subTitle},#{originalCost},#{currentPrice},#{discount},#{isFreeDelivery},#{categoryId});")
    //<SelectKey>
    @SelectKey(statement = "select last_insert_id()",before = false, keyProperty = "goodsId",resultType = Integer.class)
    public int insert(Goods goods);


    @Select("select * from t_goods")
//    <resultMap>
    @Results({
            @Result(column = "good_id",property = "goodsId",id = true),
            @Result(column = "title",property = "title"),
            @Result(column = "current_price",property = "currentPrice")
    })
    public List<GoodsDTO> selectAll();
}
