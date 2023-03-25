package Service;

import java.util.List;

import org.springframework.stereotype.Service;

import professorallocationLuis.Entity.Allocation;
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
}