package Service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import professorallocationLuis.Entity.Department;
import professorallocationLuis.Service.DepartmentService;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentServiceTest {

    @Autowired
    DepartmentService departmentService;

    @Test
    public void findAll() {
        // Act
        List<Department> departments = departmentService.findAll(null);

        // Print
        departments.forEach(System.out::println);
    }

    @Test
    public void findAllByName() {
        // Arrange
        String name = "department";

        // Act
        List<Department> departments = departmentService.findAll(name);

        // Print
        departments.forEach(System.out::println);
    }

    @Test
    public void findById() {
        // Arrange
        Long id = 1L;

        // Act
        Department department = departmentService.findById(id);

        // Print
        System.out.println(department);
    }

    @Test
    public void save() {
        // Arrange
        Department department = new Department();
        department.setId(null);
        department.setName("Department 1");

        // Act
        department = departmentService.save(department);

        // Print
        System.out.println(department);
    }

    @Test
    public void update() {
        // Arrange
        Department department = new Department();
        department.setId(1L);
        department.setName("Department 2");

        // Act
        department = departmentService.update(department);

        // Print
        System.out.println(department);
    }

    @Test
    public void deleteById() {
        // Arrange
        Long id = 1L;

        // Act
        departmentService.deleteById(id);
    }

    @Test
    public void deleteAll() {
        // Act
        departmentService.deleteAll();
    }
}
