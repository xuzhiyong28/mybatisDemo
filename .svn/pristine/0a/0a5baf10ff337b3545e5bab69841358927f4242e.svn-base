import com.xzy.mybatis.dao.UserDao;
import com.xzy.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by XZY on 2017/5/5.
 */
public class mybatisTest2 {


    public SqlSessionFactory getSqlSessionFactory(){
        SqlSessionFactory sqlSessionFactory = null ;
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlSessionFactory;
    }


    @Test
    public void test(){
        SqlSession sqlSession = this.getSqlSessionFactory().openSession();
        try {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            User user = userDao.selectByPrimaryKey(1);
        } finally {
            sqlSession.close();
        }
    }
}
