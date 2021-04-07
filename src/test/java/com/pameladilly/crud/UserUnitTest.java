package com.pameladilly.crud;

import com.pameladilly.crud.entities.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserUnitTest {
    
    @Test
    @DisplayName("Validar nome e e-mail")
    public void testCorrectNameAndMail() {
        String name = "Mario";
        String mail = "mario@gmail.com";
        User user = new User(name, mail);
        
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getEmail()).isEqualTo(mail);
    }
    

    
    @Test
    @DisplayName("Validar setter nome e e-mail")
    public void testSetNameAndSetMail() {
        String name = "Mario";
        String mail = "mario@gmail.com";
        User user = new User(name, mail);
        
        user.setName("Carlos");
        user.setEmail("carlos@gmail.com");
        
        assertThat(user.getName()).isEqualTo("Carlos");
        assertThat(user.getEmail()).isEqualTo("carlos@gmail.com");

    }

    
    @Test
    @DisplayName("Validar toString")
    public void testToString() {
        String name = "Mario";
        String mail = "mario@gmail.com";

        User user = new User(name, mail);
        assertThat(user.toString()).isEqualTo("User{id=0, name=Mario, email=mario@gmail.com}");
    }
}
