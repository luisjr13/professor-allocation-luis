package professorallocationLuis.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import professorallocationLuis.Entity.Allocation;
import professorallocationLuis.Entity.Course;
import professorallocationLuis.Entity.Professor;
import professorallocationLuis.Repository.AllocationRepository;

@Service
public class AllocationService {

    private final AllocationRepository allocationRepository;
    private final ProfessorService professorService;
    private final CourseService courseService;

    public AllocationService(AllocationRepository allocationRepository, ProfessorService professorService,
                             CourseService courseService) {
        super();
        this.allocationRepository = allocationRepository;
        this.professorService = professorService;
        this.courseService = courseService;
    }

    public List<Allocation> findAll() {
        return allocationRepository.findAll();
    }

    public Allocation findById(Long id) {
        return allocationRepository.findById(id).orElse(null);
    }

    public List<Allocation> findByProfessor(Long professorId) {
        return allocationRepository.findByProfessorId(professorId);
    }

    public List<Allocation> findByCourse(Long courseId) {
        return allocationRepository.findByCourseId(courseId);
    }

    public Allocation save(Allocation allocation) {
        allocation.setId(null);
        return saveInternal(allocation);
    }

    public Allocation update(Allocation allocation) {
        Long id = allocation.getId();
        if (id != null && allocationRepository.existsById(id)) {
            return saveInternal(allocation);
        } else {
            return null;
        }
    }

    public void deleteById(Long id) {
        if (id != null && allocationRepository.existsById(id)) {
            allocationRepository.deleteById(id);
        }
    }

    public void deleteAll() {
        allocationRepository.deleteAllInBatch();
    }

    private Allocation saveInternal(Allocation allocation) {
        if (!isEndHourGreaterThanStartHour(allocation) || hasCollision(allocation)) {
            throw new RuntimeException();
        } else {
            allocation = allocationRepository.save(allocation);

            Professor professor = professorService.findById(allocation.getProfessorId());
            allocation.setProfessor(professor);

            Course course = courseService.findById(allocation.getCourseId());
            allocation.setCourse(course);

            return allocation;
        }
    }

    boolean isEndHourGreaterThanStartHour(Allocation allocation) {
        return allocation != null && allocation.getStartHour() != null && allocation.getEndHour() != null
                && allocation.getEndHour().compareTo(allocation.getStartHour()) > 0;
    }

    boolean hasCollision(Allocation newAllocation) {
        boolean hasCollision = false;

        List<Allocation> currentAllocations = allocationRepository.findByProfessorId(newAllocation.getProfessorId());

        for (Allocation currentAllocation : currentAllocations) {
            hasCollision = hasCollision(currentAllocation, newAllocation);
            if (hasCollision) {
                break;
            }
        }

        return hasCollision;
    }

    private boolean hasCollision(Allocation currentAllocation, Allocation newAllocation) {
        return !currentAllocation.getId().equals(newAllocation.getId())
                && currentAllocation.getDayOfWeek() == newAllocation.getDayOfWeek()
                && currentAllocation.getStartHour().compareTo(newAllocation.getEndHour()) < 0
                && newAllocation.getStartHour().compareTo(currentAllocation.getEndHour()) < 0;
    }
}