package dhbw.soundtrack_guesser.dataproviders.database.entities;

import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Default database example from SpringBoot, maybe useful for login

@Entity(name = "users") // This tells Hibernate to make a table out of this class
public class UserEntity {

    @Id
    private String username;

    private String passwordHash;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}