package Service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import professorallocationLuis.Entity.Allocation;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationServiceTest {

    @Autowired
    AllocationService allocationService;
    
    @Test
    public void findAll() {
    	
        // Act
        List<Allocation> allocations = allocationService.findAll();

        // Print
        allocations.forEach(System.out::println);
    }
}