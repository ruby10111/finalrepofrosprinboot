package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.Userservice;
import net.engineeringdigest.journalApp.service.journalentryservice;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {

    @Autowired
    private journalentryservice journalentryservice;

    @Autowired
    private Userservice  userService;

    @GetMapping
public ResponseEntity<?> getAllJournalEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUsername(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if(all!=null&&!all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createentry(@RequestBody JournalEntry myentry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            journalentryservice.saveentry(myentry,userName);
            return  new ResponseEntity<>(myentry, HttpStatus.CREATED);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

}
@GetMapping("id/{myid}")
public ResponseEntity<?> getjournalentrybyid(@PathVariable ObjectId myid){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = authentication.getName();
    User user = userService.findByUsername(userName);
    List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());
    if(!collect.isEmpty()){
        Optional<JournalEntry> journalEntry = journalentryservice.findbyid(myid);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
    }
    return new ResponseEntity<>( HttpStatus.NOT_FOUND);
}

@DeleteMapping("id/{myid}")
public ResponseEntity<?> dele(@PathVariable ObjectId myid){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = authentication.getName();
    boolean removed = journalentryservice.deletebyid(myid, userName);
    if(removed){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{myid}")
    public ResponseEntity<?> update(@PathVariable ObjectId myid,
                                    @RequestBody JournalEntry newentry
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUsername(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());

        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = journalentryservice.findbyid(myid);
            if(journalEntry.isPresent()){
                JournalEntry old = journalEntry.get();
                old.setTitle(newentry.getTitle()!=null&& !newentry.getTitle().equals("")? newentry.getTitle() : old.getTitle());
                old.setContent(newentry.getContent()!=null&& !newentry.getContent().equals("")?newentry.getContent():old.getContent());
                journalentryservice.saveentry(old);
                return new ResponseEntity<>(old,HttpStatus.OK);          
            }
        }
        
     
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }
}
