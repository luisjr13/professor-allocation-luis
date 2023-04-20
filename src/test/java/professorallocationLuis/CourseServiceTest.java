package professorallocationLuis;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import professorallocationLuis.Entity.Course;
import professorallocationLuis.Service.CourseService;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class CourseServiceTest {

    @Autowired
    CourseService courseServiceTest;

    @Test
    public void findAll() {        
        List<Course> courses = courseServiceTest.findAll(null);
        
        courses.forEach(System.out::println);
    }

    @Test
    public void findAllByName() {        
        String name = "course";
        
        List<Course> courses = courseServiceTest.findAll(name);
        
        courses.forEach(System.out::println);
    }

    @Test
    public void findById() {        
        Long id = 1L;
        
        Course course = courseServiceTest.findById(id);
        
        System.out.println(course);
    }

    @Test
    public void save() {        
        Course course = new Course();
        course.setId(null);
        course.setName("Course 1");
        
        course = courseServiceTest.save(course);
        
        System.out.println(course);
    }

    @Test
    public void update() {
        
        Course course = new Course();
        course.setId(1L);
        course.setName("Course 2");

        course = courseServiceTest.update(course);

        System.out.println(course);
    }

    @Test
    public void deleteById() {
        
        Long id = 1L;
        
        courseServiceTest.deleteById(id);
    }

    @Test
    public void deleteAll() {
        courseServiceTest.deleteAll();
    }
}