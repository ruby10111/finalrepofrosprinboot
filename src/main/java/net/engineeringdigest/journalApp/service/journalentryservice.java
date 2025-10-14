package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.journalentryrepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class journalentryservice {
    @Autowired
    private journalentryrepository repos;
    @Autowired
    private Userservice userService;




    @Transactional
    public void saveentry(JournalEntry journalEntry, String userName){
try {
    User user = userService.findByUsername(userName);
    journalEntry.setDate(LocalDateTime.now());
    JournalEntry saved = repos.save(journalEntry);
    user.getJournalEntries().add(saved);
    userService.saveuser(user);
} catch (Exception e) {

    throw new RuntimeException(e);
}

        }
    public void saveentry(JournalEntry journalEntry){

       repos.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return repos.findAll();
    }
    public Optional<JournalEntry> findbyid(ObjectId id){
       return repos.findById(id);
    }
    @Transactional
    public boolean deletebyid(ObjectId id, String userName){
        boolean removed=false;
        try {
            User user = userService.findByUsername(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed){
                userService.saveuser(user);
                repos.deleteById(id);
            }

        } catch (Exception e) {
            log.error("Error: ",e);
            throw new RuntimeException("An exception occurred while deleting the entry. ",e);
        }
        return removed;
    }

}
