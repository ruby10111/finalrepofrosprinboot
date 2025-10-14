package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.userrepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class Userservice {
    @Autowired
    private userrepository userrepository;
    private static final PasswordEncoder passwordencoder=new BCryptPasswordEncoder();


   public boolean savenewuser(User userentry){
        try {
            userentry.setPassword(passwordencoder.encode(userentry.getPassword()));
            userentry.setRoles(Arrays.asList("USER"));
            userrepository.save(userentry);
            return true;
        } catch (Exception e) {
            log.error("error occurred for {}:",userentry.getUserName(),e);
//            log.info("hahahaha");
//            log.warn("hahahaha");
//            log.trace("hahahaha");
              log.debug("hahahaha");
            return false;
        }

    }

    public void saveuser(User userentry){

        userrepository.save(userentry);
    }
    public List<User> getAll(){
        return userrepository.findAll();
    }
    public Optional<User> findbyid(ObjectId id){
       return userrepository.findById(id);
    }
    public void deletebyid(ObjectId id){
        userrepository.deleteById(id);
    }

    public User findByUsername(String userName){
        return userrepository.findByUserName(userName);

    }

    public void saveadmin(User user) {
        user.setPassword(passwordencoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userrepository.save(user);
    }
}
