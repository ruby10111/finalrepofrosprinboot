package net.engineeringdigest.journalApp.repository;


import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface userrepository extends MongoRepository<User, ObjectId> {
User findByUserName(String userName);

    void deleteByUserName(String username);
}
