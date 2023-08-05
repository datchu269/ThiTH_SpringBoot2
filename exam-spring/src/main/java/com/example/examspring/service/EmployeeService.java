package com.example.examspring.service;

import com.example.examspring.dto.EmployeeDto;
import com.example.examspring.dto.PageDto;

public interface EmployeeService {
    PageDto<EmployeeDto> gets(EmployeeDto criteria);
    EmployeeDto getById(Long id);
    EmployeeDto save(EmployeeDto employeeDto);
    boolean delete(Long id);
}
