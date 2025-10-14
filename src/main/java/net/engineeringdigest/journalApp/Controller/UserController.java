package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.API.response.weatherresponse;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.userrepository;
import net.engineeringdigest.journalApp.service.Userservice;
import net.engineeringdigest.journalApp.service.weatherservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private Userservice userservice;
    @Autowired
    private userrepository userrepository;
    @Autowired
    private weatherservice weatherservice;
    @PutMapping
    public ResponseEntity<?> updateuser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userindb = userservice.findByUsername(userName);
            userindb.setUserName(user.getUserName());
            userindb.setPassword(user.getPassword());
            userservice.savenewuser(userindb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userrepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping
    public ResponseEntity<?> greetings(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        weatherresponse weather = weatherservice.getWeather("Mumbai");
        String greet="";
        if(weather!=null){
            greet=" Weather: "+weather.getCurrent().getFeelslike()+"°C";
        }
        return new ResponseEntity<>("Hi "+authentication.getName()+greet,HttpStatus.OK);
    }
}
