package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private Userservice userservice;
    @PostMapping("/create-user")
    public ResponseEntity<?> createuser(@RequestBody User user){

        boolean result=userservice.savenewuser(user);
        if(result){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
    @GetMapping("/health-check")
    public String healthcheck(){
        return "ok";
    }
}
