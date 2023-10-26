package com.garytin.Springboot.tutorial.controller;

import com.garytin.Springboot.tutorial.entity.Department;
import com.garytin.Springboot.tutorial.service.DepartmentService;
import com.garytin.Springboot.tutorial.service.DepartmentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public ResponseEntity<Department> saveDepartment(@Valid @RequestBody Department department) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(department.getDepartmentId()).toUri();
        return ResponseEntity.created(location).body(departmentService.saveDepartment(department));

    }

    @PostMapping("/saveAll")
    public ResponseEntity<List<Department>> saveAllDepartments(@Valid @RequestBody List<Department> departments) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(departments.get(0).getDepartmentId()).toUri();
        return ResponseEntity.created(location).body(departmentService.saveAllDepartments(departments));
    }

    @GetMapping("/")
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long departmentId) {
        Optional<Department> department = departmentService.getDepartmentById(departmentId);
        return department.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Department> getDepartmentByName(@PathVariable("name") String departmentName) {
        Optional<Department> department = departmentService.getDepartmentByName(departmentName);
        return department.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartmentById(@PathVariable("id") Long departmentId) {
        boolean isRemoved = departmentService.deleteDepartmentById(departmentId);
        return isRemoved
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartmentById(@PathVariable("id") Long departmentId, @RequestBody Department department) {
        Optional<Department> UpdatedDepartment = departmentService.updateDepartmentById(departmentId,department);
        return UpdatedDepartment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
