import com.hrc.mapper.EmployeeMapper;
import com.hrc.pojo.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TestMybatis {
    @Test
    public void testInput() throws IOException {
        //定义配置文件路径
        String mybatisConfigFilePath="mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(mybatisConfigFilePath);

        SqlSessionFactory sessionFactory  = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sessionFactory.openSession();

        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        Employee emp = mapper.getById(1);
        System.out.println(emp);

        //测试单个基础类型传参
        int id=mapper.queryIdByName("tom");
        System.out.println(id);

        //测试多个基础类型传参
        Employee employee=mapper.queryByNameAndId(1,"tom");
        System.out.println(employee);

        //测试实体类对象传参
        int insert=mapper.insertEmp(new Employee(4,"胡歌",999.0));
        System.out.println("插入数据条数:"+insert);
        System.out.println(mapper.getById(4));

        //测试map键值对传参
        Map<String, Object> map=new HashMap<>();
        map.put("id",1);
        map.put("name","Jerry");
        map.put("salary",888.1);
        mapper.updateEmp(map);
    }
}
