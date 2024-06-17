package com.hrc.mappers;

import com.hrc.pojo.Employee;

public interface EmployeeMapper {

    int selectEmpCount();
    Employee selectEmpById(Integer id);
}
