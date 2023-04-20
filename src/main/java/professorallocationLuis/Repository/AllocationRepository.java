package professorallocationLuis.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllocationRepository extends JpaRepository<professorallocationLuis.Entity.Allocation, Long> {

    <Allocation> List<Allocation> findByProfessorId(Long professorId);

    List<professorallocationLuis.Entity.Allocation> findByCourseId(Long courseId);
}