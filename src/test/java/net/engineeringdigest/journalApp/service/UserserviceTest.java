package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.userrepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;
@Disabled
@SpringBootTest
public class UserserviceTest {
//    @Autowired
//    private userrepository userrepository;
//
//    @Autowired
//    private journalentryservice journalentryservice;
//    @Autowired
//    private Userservice userservice;
//
//    @BeforeEach
//    void setup(){
//
//    }
//
//    @ParameterizedTest
//    @ArgumentsSource(userargumentsprovider.class)
//    public void testfindbyusername(User username){
//
//assertTrue(userservice.savenewuser(username));
//    }
//
//    @ParameterizedTest
//    @CsvSource({
//            "1,1,2",
//            "3,4,7",
//            "2,10,12"
//    })
//    public void test(int a,int b,int expected){
//        assertEquals(expected,a+b,"failed for: "+expected);
//    }
}
