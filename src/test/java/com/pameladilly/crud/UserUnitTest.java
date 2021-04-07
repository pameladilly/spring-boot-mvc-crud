package com.pameladilly.crud;

import com.pameladilly.crud.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

    @Test
    @DisplayName("Validar criação de usuário")
    public void testCreateNewUser(){
        String name = "Joana";
        String mail = "joana@test.com";
        Long id = 1L;

        User user = new User();
        user.setEmail(mail);
        user.setId(id);
        user.setName(name);

        Assertions.assertEquals(user.getClass(), User.class );
        Assertions.assertEquals(user.getId(), id);
        Assertions.assertEquals(user.getName(), name);
        Assertions.assertEquals(user.getEmail(), mail);
    }

    @Test
    @DisplayName("Validar equals por id e email")
    public void testEquailNameAndId(){
        String name = "Carlos";
        Long id = 71L;
        String email = "carlos@test.com";
        User user = new User(name, email);
        user.setId(71L);

        User userMock = new User("Carlos", "carlos@test.com");
        userMock.setId(71L);

        Assertions.assertEquals(userMock, user);
        Assertions.assertTrue(userMock.equals(user));
    }
}
