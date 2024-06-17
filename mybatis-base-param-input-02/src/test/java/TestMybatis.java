import com.hrc.mapper.EmployeeMapper;
import com.hrc.pojo.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestMybatis {
    @Test
    public void testInput() throws IOException {
        //定义配置文件路径
        String mybatisConfigFilePath="mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(mybatisConfigFilePath);

        SqlSessionFactory sessionFactory  = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sessionFactory.openSession();

        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        Employee emp = mapper.getById(3);
        System.out.println(emp);


    }
}
