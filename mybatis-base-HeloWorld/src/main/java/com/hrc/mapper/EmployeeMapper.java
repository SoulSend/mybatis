package com.hrc.mapper;

import com.hrc.pojo.Employee;

import java.util.List;

/**
 * 这个接口是mybatis对java的映射，java调用这里的方法，mybatis自动映射到xml文件的对应的sql语句
 */
public interface EmployeeMapper {
    Employee getById(Integer id);

    List<Employee> selectEmployee( );
}
