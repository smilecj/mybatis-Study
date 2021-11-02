package cn.smilehalo.mybatis;

import cn.smilehalo.entity.Goods;
import cn.smilehalo.utils.MyBatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.util.List;

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
    public void testMyBatisUtils(){
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
    public void testSelectAll(){
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


}
