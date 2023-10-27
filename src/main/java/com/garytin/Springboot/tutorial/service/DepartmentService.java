package com.garytin.Springboot.tutorial.service;

import com.garytin.Springboot.tutorial.entity.Department;
import com.garytin.Springboot.tutorial.error.DepartmentNotFoundException;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {


    public Department saveDepartment(Department department);

    public List<Department> getAllDepartments();

    public List<Department> saveAllDepartments(List<Department> departments);

    public Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    void deleteDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    Department updateDepartmentById(Long departmentId, Department department) throws DepartmentNotFoundException;

    Department getDepartmentByName(String departmentName) throws DepartmentNotFoundException;
}
