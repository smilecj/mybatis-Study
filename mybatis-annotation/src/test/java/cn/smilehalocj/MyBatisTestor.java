package cn.smilehalocj;

import cn.smilehalocj.dao.GoodsDAO;
import cn.smilehalocj.dto.GoodsDTO;
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

    @Test
    public void testInsert() throws Exception{
        SqlSession sqlSession = null;
        try {
            sqlSession  = MyBatisUtils.openSession();
            Goods goods = new Goods();
            goods.setTitle("测试商品");
            goods.setTitle("测试子标题");
            goods.setOriginalCost(200f);
            goods.setCurrentPrice(100f);
            goods.setDiscount(0.5f);
            goods.setIsFreeDelivery(1);
            goods.setCategoryId(43);
            GoodsDAO goodsDAO = sqlSession.getMapper(GoodsDAO.class);
            int num = goodsDAO.insert(goods);
            sqlSession.commit();
            System.out.println(goods.getGoodsId());


        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

//selectAll 映射对象
    @Test
    public void testSelectAll() throws Exception{
        SqlSession sqlSession = null;
        try {
            sqlSession  = MyBatisUtils.openSession();
            GoodsDAO goodsDAO = sqlSession.getMapper(GoodsDAO.class);
            List<GoodsDTO> list = goodsDAO.selectAll();
            sqlSession.commit();
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }
















    /**
     *  测试模板
     * @throws Exception
     */
    @Test
    public void MUBan() throws Exception{
        SqlSession sqlSession = null;
        try {
            sqlSession  = MyBatisUtils.openSession();
            GoodsDAO goodsDAO = sqlSession.getMapper(GoodsDAO.class);
            sqlSession.commit();
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }



}