package net.engineeringdigest.journalApp.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.engineeringdigest.journalApp.dto.JournalDTO;
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
    @Operation(summary = "Get all journal entries of the users")
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
    @Operation(summary = "create a new journal")
    public ResponseEntity<?> createentry(@RequestBody JournalDTO myentry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            JournalEntry journalEntry=new JournalEntry();
            journalEntry.setContent(myentry.getContent());
            journalEntry.setTitle(myentry.getTitle());
            journalEntry.setSentiment(myentry.getSentiment());
            journalentryservice.saveentry(journalEntry,userName);
            return  new ResponseEntity<>(myentry, HttpStatus.CREATED);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

}
@GetMapping("id/{myid}")
@Operation(summary = "Get journal by id")
public ResponseEntity<?> getjournalentrybyid(@PathVariable String myid){
        ObjectId objectId=new ObjectId(myid);
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = authentication.getName();
    User user = userService.findByUsername(userName);
    List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(objectId)).collect(Collectors.toList());
    if(!collect.isEmpty()){
        Optional<JournalEntry> journalEntry = journalentryservice.findbyid(objectId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
    }
    return new ResponseEntity<>( HttpStatus.NOT_FOUND);
}

@DeleteMapping("id/{myid}")
@Operation(summary = "Delete journal by id.")
public ResponseEntity<?> dele(@PathVariable String myid){
    ObjectId objectId=new ObjectId(myid);
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = authentication.getName();
    boolean removed = journalentryservice.deletebyid(objectId, userName);
    if(removed){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{myid}")
    @Operation(summary = "Replace the journal content by id.")
    public ResponseEntity<?> update(@PathVariable String myid,
                                    @RequestBody JournalDTO newentry
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        ObjectId objectId=new ObjectId(myid);
        User user = userService.findByUsername(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(objectId)).collect(Collectors.toList());

        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = journalentryservice.findbyid(objectId);
            if(journalEntry.isPresent()){
                JournalEntry old = journalEntry.get();
                old.setTitle(newentry.getTitle()!=null&& !newentry.getTitle().equals("")? newentry.getTitle() : old.getTitle());
                old.setContent(newentry.getContent()!=null&& !newentry.getContent().equals("")?newentry.getContent():old.getContent());
                old.setSentiment(newentry.getSentiment() != null ?newentry.getSentiment():old.getSentiment());
                journalentryservice.saveentry(old);
                return new ResponseEntity<>(old,HttpStatus.OK);          
            }
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }
}
