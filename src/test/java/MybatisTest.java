import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xzy.mybatis.dao.UserDao;
import com.xzy.mybatis.model.User;
import com.xzy.mybatis.service.IUserService;
import com.xzy.mybatis.service.Impl.RedisServiceImpl;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.print.attribute.standard.JobName;
import java.util.List;

/**
 * Created by XZY on 2017/4/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml", "classpath:spring-redis.xml" })
public class MybatisTest {
    private static Logger logger = Logger.getLogger(MybatisTest.class);
    @Resource
    private IUserService userService ;

    @Resource
    private RedisServiceImpl redisServiceImpl;




    @Test
    public void test(){
        User user = new User();
        user.setAge(26);
        user.setName("许志勇");
        user.setPhone("18054809167");
        userService.insert(user);
    }

    @Test
    public void testSelect(){
        List<User> userList = userService.selectAll();
        System.out.println(userList.size());
    }

    @Test
    public void testSelectCall(){
        List<User> userList = userService.selectUserCall();
        for(User user : userList){
            System.out.println(user.toString());
        }
    }

    @Test
    public void testSelectByID(){
        List<User> userList = userService.selectUserByID(3);
        System.out.println(userList.size());
    }


    @Test
    public void test2(){
        redisServiceImpl.saveRedisString("message","hahahahahaha",4000);
        System.out.println(redisServiceImpl.getRedisString("message"));
    }

    @Test
    public void test3(){
        User user = new User();
        user.setPhone("18054809167");
        user.setName("许志勇");
        redisServiceImpl.saveObject("xzy",user,30);
        User user2 = (User) redisServiceImpl.getObject("xzy");
        System.out.println(user2);
    }

    @Test
    public void test4(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","xzy");
        redisServiceImpl.pushRedis("xzy",jsonObject);
        JSONObject jsonObject1 = (JSONObject)redisServiceImpl.popRedis("xzy");
        System.out.println(jsonObject1.toJSONString());
    }

    @Test
    public void test5(){
        String key = "hash";
        redisServiceImpl.pushRedis(key,"key1");
        redisServiceImpl.pushRedis(key,"key2");
        redisServiceImpl.pushRedis(key,"key3");
        List<Object> list = redisServiceImpl.range(key,0,10);
        System.out.println(list.size());
        for(Object s : list){
            System.out.println((String)s);
        }
    }

}
