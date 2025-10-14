package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.service.EmailService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@Disabled
@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;
    @Disabled
    @Test
    void TestsendMail(){
        emailService.sendemail("gsaurav8734@gmail.com",
                "Testing Java mail sender",
                "Hi this is an example");
    }
}
