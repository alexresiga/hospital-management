package hospital.management.Hospital.controller;

import hospital.management.Hospital.dto.DepartmentDto;
import hospital.management.Hospital.model.Department;
import hospital.management.Hospital.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/api/departments")
    public List<DepartmentDto> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/api/departments/{id}")
    public DepartmentDto getDepartmentById(@PathVariable("id") Integer id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping("/api/departments")
    public DepartmentDto createDepartment(@RequestBody DepartmentDto departmentDto) {
        return departmentService.createDepartment(departmentDto);
    }

    @PutMapping("/api/departments/{id}")
    public DepartmentDto updateDepartment(@PathVariable("id") Integer id, @RequestBody DepartmentDto data) {
        return departmentService.updateDepartment(id, data);
    }

    @DeleteMapping("/api/departments/{id}")
    public boolean deleteDepartment(@PathVariable("id") Integer id) {
        return departmentService.deleteDepartment(id);
    }
}
