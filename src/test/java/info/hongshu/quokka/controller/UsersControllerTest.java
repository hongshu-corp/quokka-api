package info.hongshu.quokka.controller;

import info.hongshu.quokka.repository.UserRepository;
import info.hongshu.quokka.model.User;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static info.hongshu.quokka.controller.UsersController.DEFAULT_EMAIL;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersControllerTest {

    private UserRepository userRepository = mock(UserRepository.class);
    UsersController controller = new UsersController(userRepository);

    @Nested
    public class DefaultUser {
        @Test
        public void when_user_exists() {
            when(userRepository.findByEmail(DEFAULT_EMAIL)).thenReturn(new User());
            String result = controller.createDefaultUser();
            assertEquals("User already exists", result);
        }

        @Test
        public void when_user_does_not_exist() {

            when(userRepository.findByEmail(DEFAULT_EMAIL)).thenReturn(null);
            String result = controller.createDefaultUser();
            assertEquals(8, result.length());
        }
    }
}
