package com.garytin.Springboot.tutorial.controller;

import com.garytin.Springboot.tutorial.entity.Department;
import com.garytin.Springboot.tutorial.error.DepartmentNotFoundException;
import com.garytin.Springboot.tutorial.service.DepartmentService;
import com.garytin.Springboot.tutorial.service.DepartmentServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @PostMapping("/")
    public ResponseEntity<Department> saveDepartment(@Valid @RequestBody Department department) {
        LOGGER.info("Inside saveDepartment of DepartmentController");
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(department.getDepartmentId()).toUri();
        return ResponseEntity.created(location).body(departmentService.saveDepartment(department));

    }

    @PostMapping("/saveAll")
    public ResponseEntity<List<Department>> saveAllDepartments(@Valid @RequestBody List<Department> departments) {
        LOGGER.info("Inside saveAllDepartments of DepartmentController");
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(departments.get(0).getDepartmentId()).toUri();
        return ResponseEntity.created(location).body(departmentService.saveAllDepartments(departments));
    }

    @GetMapping("/")
    public ResponseEntity<List<Department>> getAllDepartments() {
        LOGGER.info("Inside getAllDepartments of DepartmentController");
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        LOGGER.info("Inside getDepartmentById of DepartmentController");
        Department department = departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(department);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Department> getDepartmentByName(@PathVariable("name") String departmentName) throws DepartmentNotFoundException {
        LOGGER.info("Inside getDepartmentByName of DepartmentController");
        Department department = departmentService.getDepartmentByName(departmentName);
        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        LOGGER.info("Inside deleteDepartmentById of DepartmentController");
        departmentService.deleteDepartmentById(departmentId);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartmentById(@PathVariable("id") Long departmentId, @RequestBody Department department) throws DepartmentNotFoundException {
        LOGGER.info("Inside updateDepartmentById of DepartmentController");
        Department UpdatedDepartment = departmentService.updateDepartmentById(departmentId,department);
        return ResponseEntity.ok(UpdatedDepartment);
    }

}
