package cn.smilehalocj.dao;

import cn.smilehalocj.entity.Goods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GoodsDAO {
    @Select ("select * from t_goods where current_price between #{min} and #{max} order by current_price limit 0,#{limt}")
    public List<Goods> selectByPriceRange(@Param("min") Float min, @Param("max") Float max, @Param("limt") Integer limt);
}
