package cn.smilehalo.mybatis;

import cn.smilehalo.dto.GoodsDTO;
import cn.smilehalo.entity.Goods;
import cn.smilehalo.entity.GoodsDetail;
import cn.smilehalo.utils.MyBatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisTestor {
    @Test
    public void testSqlSessionFactory() throws IOException {
        //利用Reader加载classpath下的mybatis-config.xml核心配置文件
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        //初始化SqlSessionFactory对象，同时解析mybatis-config.xml文件
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        System.out.println("yes  SessionFactory加载成功");

        //创建SqlSession对象
        SqlSession sqlSession = null;

        try {
            //SqlSession是JDBC的扩展类，用于数据库交互
            sqlSession = sqlSessionFactory.openSession();
            //创建数据库链接（测试用）
            Connection connection = sqlSession.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null){
                //如果 type="POOLED" ，代表使用连接池，close则是将连接回收到连接池中
                //如果 type=:"UNPOOLED",代表直连，close直接调用Connection.close()方法关闭
                sqlSession.close();
            }
        }
    }
    @Test
    public void testMyBatisUtils()throws Exception{
        SqlSession sqlSession = null;

        try {
            sqlSession = MyBatisUtils.openSession();
            Connection connection = sqlSession.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }
    @Test
    public void testSelectAll()throws Exception{
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            List<Goods> list = sqlSession.selectList("goods.selectAll");
            for (Goods goods:list
                 ) {
                System.out.println(goods.getTitle());

            }

        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }
    @Test
    public void testSelectById()throws Exception{
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Goods goods = sqlSession.selectOne("goods.selectById", 1603);
            System.out.println(goods.getTitle());
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testSelectByPriceRange()throws Exception{
        SqlSession sqlSession = null;
        try {
            sqlSession  = MyBatisUtils.openSession();
            Map param = new HashMap();
            param.put("min",100);
            param.put("max",500);
            param.put("limit",10);
            List<Goods> list = sqlSession.selectList("selectByPriceRange", param);
            for (Goods g:list){
                System.out.println(g.getTitle()+":"+g.getCurrentPrice());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }
    @Test
    public void testSelectGoodsMap()throws Exception{
        SqlSession sqlSession = null;
        try {
            sqlSession=MyBatisUtils.openSession();
            List<Map> list = sqlSession.selectList("goods.selectGoodsMap");
            for (Map map :
                    list) {
                System.out.println(map);
            }

            /*List<Goods> list1 = sqlSession.selectList("goods.selectGoodsMap");
            for (Goods goods :
                    list1) {
                System.out.println(goods.getTitle());
            }*/



        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }
    @Test
    public void testSelectGoodsDTO()throws Exception{
        SqlSession sqlSession = null;
        try {
            sqlSession  = MyBatisUtils.openSession();
            List<GoodsDTO> list = sqlSession.selectList("selectGoodsDTO");
            for (GoodsDTO g:list){
                System.out.println(g.getGoods().getTitle());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }
    @Test
    public void testInsert()throws Exception{
        SqlSession sqlSession = null;
        try {
            sqlSession  = MyBatisUtils.openSession();
            Goods goods = new Goods();
            goods.setTitle("测试商品");
            goods.setSubTitle("测试子标签");
            goods.setOriginalCost(200f);
            goods.setCurrentPrice(100f);
            goods.setDiscount(0.5f);
            goods.setIsFreeDelivery(1);
            goods.setCategoryId(43);

            //insert（）方法返回值代表本次成功插入的记录总数
            int insert = sqlSession.insert("goods.insert", goods);
            //提交事物数据 commit();
            sqlSession.commit();
            System.out.println(goods.getGoodsId());
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();//回滚事务
            }
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    //  更新操作   (无法完成)
    @Test
    public void testUpdate() throws Exception{
        SqlSession sqlSession = null;
        try {
            sqlSession  = MyBatisUtils.openSession();
            //通过selectById获取id为739的一个数据 并实例为goods对象
            Goods goods = sqlSession.selectOne("goods.selectById", 1603);
            System.out.println(goods.getCurrentPrice());
            //为此goods对象修改一条Title数据
            goods.setTitle("111");
            //利用定义的updata语句，向数据库传递goods对象  并更新数据库
            int num = sqlSession.update("goods.update", goods);
            //输出num 即成功更新的条数
            System.out.println(num);
            sqlSession.commit();
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();//回滚事务
            }
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }
    //删除
    @Test
    public void testDelete() throws Exception{
        SqlSession sqlSession = null;
        try {
            sqlSession  = MyBatisUtils.openSession();
            //通过selectById获取id为739的一个数据 并实例为goods对象
            int num = sqlSession.delete("goods.delete", 739);
            System.out.println(num);
            sqlSession.commit();
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();//回滚事务
            }
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testDynamicSql() throws Exception{
        SqlSession sqlSession = null;
        try {
            sqlSession  = MyBatisUtils.openSession();
            //通过selectById获取id为739的一个数据 并实例为goods对象
            Map param = new HashMap();
            param.put("categryId",44);
            param.put("currentPrice",500);
            List<Goods> list = sqlSession.selectList("goods.dynamicSQL", param);
            for (Goods g :
                    list) {
                System.out.println(g.getTitle()+":"+g.getCategoryId()+":"+g.getCurrentPrice());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }



//    对象关联查询
    //一对多
    @Test
    public void testOneToMany() throws Exception{
        SqlSession sqlSession = null;
        try {
            sqlSession  = MyBatisUtils.openSession();
            List<Goods> list = sqlSession.selectList("goods.selectOneToMany");
            for (Goods goods:list) {
                System.out.println(goods.getTitle()+":"+goods.getGoodsDetails().size());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    //多对一
    @Test
    public void testManyToOne() throws Exception{
        SqlSession sqlSession = null;
        try {
            sqlSession  = MyBatisUtils.openSession();
            List<GoodsDetail> list = sqlSession.selectList("goodsDetail.selectManyToOne");
            for (GoodsDetail gd:list) {
                System.out.println(gd.getGdPicUrl()+":"+gd.getGoods().getTitle());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }
}
