package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
@Disabled
@SpringBootTest
public class UserRepositoryImplTests {
    @Autowired
    private UserRepositoryImpl userRepository;
    @Test
    void testUsers(){
        Assertions.assertNotNull(userRepository.getUserForSA());
    }
}