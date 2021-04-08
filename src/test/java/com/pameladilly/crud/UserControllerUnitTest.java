package com.pameladilly.crud;

import com.pameladilly.crud.controllers.UserController;
import com.pameladilly.crud.entities.User;
import com.pameladilly.crud.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerUnitTest {

    private static UserController userController;
    private static BindingResult mockedBindingResult;
    private static Model mockedModel;

    @BeforeAll
    public static void setUpUserControllerInstance() {
        UserRepository mockedUserRepository = mock(UserRepository.class);
        mockedBindingResult = mock(BindingResult.class);
        mockedModel = mock(Model.class);
        userController = new UserController(mockedUserRepository);
    }

    @Test
    @DisplayName("Chamada ao index")
    public void whenCalledIndex_thenCorrect() {
        Assertions.assertEquals(userController.showUserList(mockedModel), "index");

    }

    @Test
    @DisplayName("Chamada para add usuário")
    public void whenCalledshowSignUpForm_thenCorrect() {
        User user = new User("John", "john@domain.com");

        Assertions.assertEquals(userController.showSignUpForm(user), "add-user");

    }
    
    @Test
    @DisplayName("Redirecionamento ao index após cadastrar usuário")
    public void whenCalledaddUserAndValidUser_thenCorrect() {
        User user = new User("John", "john@domain.com");

        Mockito.when(mockedBindingResult.hasErrors()).thenReturn(false);

        Assertions.assertEquals( userController.addUser(user, mockedBindingResult, mockedModel), "redirect:/index");

    }


    @Test
    @DisplayName("Redirecimento ao form de add usuário quando tiver erro ")
    public void whenCalledaddUserAndInValidUser_thenCorrect() {
        User user = new User("John", "john@domain.com");

        Mockito.when(mockedBindingResult.hasErrors()).thenReturn(true);

        Assertions.assertEquals(userController.addUser(user, mockedBindingResult, mockedModel), "add-user");
    }

    @Test
    @DisplayName("Retornar exceção ao tentar atualizar usuário com id inexistente")
    public void testShowUpdateForm_ExceptionIllegalArgument(){


        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userController.showUpdateForm(0, mockedModel);
        });

    }
    
    @Test
    @DisplayName("Redirecimento ao index ao atualizar usuário")
    public void whenCalledupdateUserAndValidUser_thenCorrect() {
        User user = new User("John", "john@domain.com");

        when(mockedBindingResult.hasErrors()).thenReturn(false);

        assertThat(userController.updateUser(1L, user, mockedBindingResult, mockedModel)).isEqualTo("redirect:/index");
    }

    @Test
    @DisplayName("Redirecionamento ao form de atualização ao inserir dados incorretos")
    public void whenCalledupdateUserAndInValidUser_thenCorrect() {
        User user = new User("John", "john@domain.com");

        when(mockedBindingResult.hasErrors()).thenReturn(true);

        assertThat(userController.updateUser(1L, user, mockedBindingResult, mockedModel)).isEqualTo("update-user");
    }
    
    @Test
    @DisplayName("Retornar exceção ao tentar excluir usuário com id inexistente")
    public void whenCalleddeleteUser_thenIllegalArgumentException() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userController.deleteUser(1L, mockedModel);
        });
    }
}
