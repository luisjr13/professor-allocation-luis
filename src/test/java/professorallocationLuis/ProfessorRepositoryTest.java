package professorallocationLuis;

import professorallocationLuis.Entity.Professor;
import repository.ProfessorRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorRepositoryTest {
	
	@Autowired
	ProfessorRepository professorrepository;
	
	@Test
	public void findAll1() {
		//Act
		List<Professor> professors = professorrepository.findAll();
		
		//Print
		professors.forEach(System.out::println);
	}
	
	@Test
	public void findAll() {
		// Arrange
        Long id = 1L;
        
        // Act
        Professor professor = professorrepository.findById(id).orElse(null);
        
        // Print
        System.out.println(professor);   

}
	
	@Test
    public void findByNameContainingIgnoreCase() {
        // Arrange
        String name = "Professor";

        // Act
        List<Professor> professors = professorrepository.findByNameContainingIgnoreCase(name);

        // Print
        professors.forEach(System.out::println);
    }

    @Test
    public void findByDepartmentId() {
        // Arrange
        Long departmentId = 1L;

        // Act
        List<Professor> professors = professorrepository.findByDepartmentId(departmentId);

        // Print
        professors.forEach(System.out::println);
    }
    
    @Test
    public void save_create() {
        // Arrange
        Professor professor = new Professor();
        professor.setId(null);
        professor.setName("Professor 1");
        professor.setCpf("111.111.111-11");
        professor.setDepartmentId(1L);

        // Act
        professor = professorrepository.save(professor);

        // Print
        System.out.println(professor);
    }

    @Test
    public void save_update() {
        // Arrange
        Professor professor = new Professor();
        professor.setId(1L);
        professor.setName("Professor 2");
        professor.setCpf("222.222.222-22");
        professor.setDepartmentId(1L);

        // Act
        professor = professorrepository.save(professor);

        // Print
        System.out.println(professor);
    }

    @Test
    public void deleteById() {
        // Arrange
        Long id = 1L;

        // Act
        professorrepository.deleteById(id);
    }

    @Test
    public void deleteAll() {
        // Act
        professorrepository.deleteAllInBatch();
    }
}
