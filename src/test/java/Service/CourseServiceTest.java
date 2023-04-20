package Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import java.util.List;

import professorallocationLuis.Entity.Course;
import professorallocationLuis.Service.CourseService;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class CourseServiceTest {

    @Autowired
    CourseService courseService;

    @Test
    public void findAll() {
        // Act
        List<Course> courses = courseService.findAll(null);

        // Print
        courses.forEach(System.out::println);
    }

    @Test
    public void findAllByName() {
        // Arrange
        String name = "course";

        // Act
        List<Course> courses = courseService.findAll(name);

        // Print
        courses.forEach(System.out::println);
    }

    @Test
    public void findById() {
        // Arrange
        Long id = 1L;

        // Act
        Course course = courseService.findById(id);

        // Print
        System.out.println(course);
    }

    @Test
    public void save() {
        // Arrange
        Course course = new Course();
        course.setId(null);
        course.setName("Course 1");

        // Act
        course = courseService.save(course);

        // Print
        System.out.println(course);
    }

    @Test
    public void update() {
        // Arrange
        Course course = new Course();
        course.setId(1L);
        course.setName("Course 2");

        // Act
        course = courseService.update(course);

        // Print
        System.out.println(course);
    }

    @Test
    public void deleteById() {
        // Arrange
        Long id = 1L;

        // Act
        courseService.deleteById(id);
    }

    @Test
    public void deleteAll() {
        // Act
        courseService.deleteAll();
    }
}
