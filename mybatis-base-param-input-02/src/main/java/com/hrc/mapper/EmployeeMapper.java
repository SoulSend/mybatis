package com.hrc.mapper;

import com.hrc.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface EmployeeMapper {
    Employee getById(Integer id);

    int queryIdByName(String name);

    Employee queryByNameAndId(@Param("id") Integer id, @Param("name") String name);

    int insertEmp(Employee emp);

    int  updateEmp(Map<String,Object> map);
}
