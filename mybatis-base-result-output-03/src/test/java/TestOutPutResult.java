import com.hrc.mappers.EmployeeMapper;
import com.hrc.pojo.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestOutPutResult {
    @Test
    public  void testResult() throws IOException {
        //定义配置文件路径
        String mybatisConfigFilePath="mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(mybatisConfigFilePath);

        SqlSessionFactory sessionFactory  = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sessionFactory.openSession();

        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        int emp = mapper.selectEmpCount();
        System.out.println(emp);
        System.out.println();
        System.out.println(mapper.selectEmpById(1));


    }
}
