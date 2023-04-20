package Service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import professorallocationLuis.Entity.Professor;
import professorallocationLuis.Service.ProfessorService;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorServiceTest {

    @Autowired
    ProfessorService professorService;

    @Test
    public void findAll() {
        // Act
        List<Professor> professors = professorService.findAll(null);

        // Print
        professors.forEach(System.out::println);
    }

    @Test
    public void findAllByName() {
        // Arrange
        String name = "professor";

        // Act
        List<Professor> professors = professorService.findAll(name);

        // Print
        professors.forEach(System.out::println);
    }

    @Test
    public void findByDepartment() {
        // Arrange
        Long departmentId = 1L;

        // Act
        List<Professor> professors = professorService.findByDepartment(departmentId);

        // Print
        professors.forEach(System.out::println);
    }

    @Test
    public void findById() {
        // Arrange
        Long id = 1L;

        // Act
        Professor professor = professorService.findById(id);

        // Print
        System.out.println(professor);
    }

    @Test
    public void save() {
        // Arrange
        Professor professor = new Professor();
        professor.setId(null);
        professor.setName("Professor 1");
        professor.setCpf("111.111.111-11");
        professor.setDepartmentId(1L);

        // Act
        professor = professorService.save(professor);

        // Print
        System.out.println(professor);
    }

    @Test
    public void update() {
        // Arrange
        Professor professor = new Professor();
        professor.setId(1L);
        professor.setName("Professor 2");
        professor.setCpf("222.222.222-22");
        professor.setDepartmentId(1L);

        // Act
        professor = professorService.update(professor);

        // Print
        System.out.println(professor);
    }

    @Test
    public void deleteById() {
        // Arrange
        Long id = 1L;

        // Act
        professorService.deleteById(id);
        
        // Print
        Professor professor = professorService.findById(id);
        System.out.println(professor);
    }

    @Test
    public void deleteAll() {
        // Act
        professorService.deleteAll();
        
        // Print
        List<Professor> professors = professorService.findAll(null);
        professors.forEach(System.out::println);
    }
}
