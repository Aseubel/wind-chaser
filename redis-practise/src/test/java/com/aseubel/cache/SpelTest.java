package com.aseubel.cache;

import com.aseubel.cache.entity.User;
import com.aseubel.cache.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Aseubel
 * @date 2025/8/9 上午10:56
 */
@SpringBootTest
public class SpelTest {
    @Autowired
    private IUserService myService;

    @Test
    public void testSpel() {
        System.out.println("--- Running SpEL Resolver Tests ---");

        User testUser = new User();
        testUser.setId(99);
        testUser.setName("Gandalf");
        myService.processUser(testUser);

        System.out.println();
        myService.checkCount(5);

        System.out.println();
        myService.checkCount(20);

        System.out.println();
        myService.assignRole("Aragorn", "King");

        System.out.println("\n--- Tests Finished ---");
    }
}
