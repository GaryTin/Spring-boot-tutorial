package com.garytin.Springboot.tutorial.service;

import com.garytin.Springboot.tutorial.entity.Department;
import com.garytin.Springboot.tutorial.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {


    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
            return departmentRepository.findAll();
    }

    @Override
    public List<Department> saveAllDepartments(List<Department> departments) {
        return departmentRepository.saveAll(departments);
    }

    @Override
    public Optional<Department> getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId);
    }

    @Override
    public boolean deleteDepartmentById(Long departmentId) {
        if (departmentRepository.findById(departmentId).isPresent())
        {
            departmentRepository.deleteById(departmentId);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Department> updateDepartmentById(Long departmentId, Department department) {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if (departmentOptional.isPresent())
        {
            Department department1 = departmentOptional.get();
            if (Objects.nonNull(department.getDepartmentName())
                    && !"".equalsIgnoreCase(department.getDepartmentName())){
                department1.setDepartmentName(department.getDepartmentName());
            }
            if (Objects.nonNull(department.getDepartmentAddress())
                    && !"".equalsIgnoreCase(department.getDepartmentAddress())){
                department1.setDepartmentAddress(department.getDepartmentAddress());
            }
            if (Objects.nonNull(department.getDepartmentCode())
                    && !"".equalsIgnoreCase(department.getDepartmentCode())){
                department1.setDepartmentCode(department.getDepartmentCode());
            }
            departmentRepository.save(department1);
            return Optional.of(department1);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Department> getDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }
}
