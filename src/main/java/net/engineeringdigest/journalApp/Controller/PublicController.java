package net.engineeringdigest.journalApp.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.dto.UserDTO;
import net.engineeringdigest.journalApp.dto.UserLoginDTO;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserDetailsServiceImpl;
import net.engineeringdigest.journalApp.service.Userservice;
import net.engineeringdigest.journalApp.utilis.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private Userservice userservice;
    @PostMapping("/signup")
    @Operation(description = "SignUp")
    public ResponseEntity<?> signup(@RequestBody UserDTO user){
        User newuser=new User();
        newuser.setEmail(user.getEmail());
        newuser.setPassword(user.getPassword());
        newuser.setUserName(user.getUserName());
        newuser.setSentimentanalysis(user.isSentimentanalysis());
        boolean result=userservice.savenewuser(newuser);
        if(result){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
    @GetMapping("/health-check")
    @Operation(description = "health-check")
    public String healthcheck(){
        return "ok";
    }

    @PostMapping("/login")
    @Operation(description = "Login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO user){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
            String jwt=jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt,HttpStatus.OK);
        } catch (Exception e) {
        log.error("Error in login: ",e);
        return new ResponseEntity<>("Incorrect Username or password",HttpStatus.BAD_REQUEST);
        }
    }
}
