package net.engineeringdigest.journalApp.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.engineeringdigest.journalApp.API.response.weatherresponse;
import net.engineeringdigest.journalApp.dto.UserLoginDTO;
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
@Tag(name="User APIS",description = "User can login ,change existing, delete users.")
public class UserController {
    @Autowired
    private Userservice userservice;
    @Autowired
    private userrepository userrepository;
    @Autowired
    private weatherservice weatherservice;
    @PutMapping
    @Operation(description = "Update user")
    public ResponseEntity<?> updateuser(@RequestBody UserLoginDTO user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userindb = userservice.findByUsername(userName);
            userindb.setUserName(user.getUserName());
            userindb.setPassword(user.getPassword());
            userservice.savenewuser(userindb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Operation(description = "delete user")
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userrepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping
    @Operation(description = "Get Weather")
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
