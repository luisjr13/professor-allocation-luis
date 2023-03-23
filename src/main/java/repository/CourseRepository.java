package repository;

import professorallocationLuis.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    static List<Course> findByNameContainingIgnoreCase(String name) {
		return null;
	}
}