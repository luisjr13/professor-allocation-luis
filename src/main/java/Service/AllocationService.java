package Service;

import java.util.List;

import org.springframework.stereotype.Service;

import professorallocationLuis.Entity.Allocation;
import professorallocationLuis.Entity.Course;
import professorallocationLuis.Entity.Professor;
import repository.AllocationRepository;

@Service
public class AllocationService {

    private final AllocationRepository allocationRepository;
    
    public Allocation findById(Long id) {
    	return allocationRepository.findById(id).orElse(null);
    }
    
    public List<Allocation> findByProfessor(Long professorId) {
    	return allocationRepository.findByProfessorId(professorId);
    }
    
    public List<Allocation> findByCourse(Long courseId) {
    	return allocationRepository.findByCourseId(courseId);
    }

    public AllocationService(AllocationRepository allocationRepository) {
        super();
        this.allocationRepository = allocationRepository;
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
    		
    private Allocation saveInternal(Allocation allocation) {
        if (!isEndHourGreaterThanStartHour(allocation) || hasCollision(allocation)) {
            throw new RuntimeException();
        } else {
            allocation = allocationRepository.save(allocation);

            return allocation;
        }
    }

    boolean isEndHourGreaterThanStartHour(Allocation allocation) {
        return allocation != null && allocation.getStartHour() != null && allocation.getEndHour() != null
                && allocation.getEndHour().compareTo(allocation.getStartHour()) > 0;
    }

	public void deleteById(Long id) {
    	if (allocationRepository.existsById(id)) {
    	allocationRepository.deleteById(id);
    			}
    		}
    
    public void deleteAll() {
    	allocationRepository.deleteAllInBatch();
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