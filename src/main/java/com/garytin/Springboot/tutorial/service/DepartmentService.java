package com.garytin.Springboot.tutorial.service;

import com.garytin.Springboot.tutorial.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {


    public Department saveDepartment(Department department);

    public List<Department> getAllDepartments();

    public List<Department> saveAllDepartments(List<Department> departments);

    public Optional<Department> getDepartmentById(Long departmentId);

    boolean deleteDepartmentById(Long departmentId);

    Optional<Department> updateDepartmentById(Long departmentId, Department department);

    Optional<Department> getDepartmentByName(String departmentName);
}
