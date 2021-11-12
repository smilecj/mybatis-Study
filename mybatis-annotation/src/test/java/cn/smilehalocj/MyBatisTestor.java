package cn.smilehalocj;

import cn.smilehalocj.dao.GoodsDAO;
import cn.smilehalocj.entity.Goods;
import cn.smilehalocj.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyBatisTestor {
    @Test
    public void testSelectByPriceRange() throws Exception{
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            GoodsDAO goodsDAO = sqlSession.getMapper(GoodsDAO.class);
            List<Goods> list = goodsDAO.selectByPriceRange(100f, 500f, 20);
            System.out.println(list.size());
        }catch (Exception e){
            throw e;
        }finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }
}