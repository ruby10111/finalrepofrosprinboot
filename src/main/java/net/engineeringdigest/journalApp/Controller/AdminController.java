package net.engineeringdigest.journalApp.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.dto.UserDTO;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private Userservice userservice;
    @Autowired
    private AppCache appCache;
    @GetMapping("/all-users")
    @Operation(description = "get all users from database")
    public ResponseEntity<?> getallusers() {
        List<User> all = userservice.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
@PostMapping("/create-admin-user")
@Operation(description = "create a new admin")
    public void createadmin(@RequestBody UserDTO user){
        User user1 =new User();
        user1.setUserName(user.getUserName());
        user1.setPassword(user.getPassword());
        user1.setSentimentanalysis(user.isSentimentanalysis());
        user1.setEmail(user.getEmail());
        userservice.saveadmin(user1);
}
    @GetMapping("clear-app-cache")
    public void clearAppCache(){
        appCache.init();
    }

}
