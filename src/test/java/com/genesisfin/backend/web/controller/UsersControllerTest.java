package com.genesisfin.backend.web.controller;

import com.genesisfin.backend.web.model.User;
import com.genesisfin.backend.web.repository.UserRepository;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MvcResult;

import static com.genesisfin.backend.web.controller.UsersController.DEFAULT_EMAIL;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(UsersController.class)
public class UsersControllerTest extends ControllerTestBase {

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testCreateDefaultUser_whenUserExists() throws Exception {
        given(userRepository.findByEmail(DEFAULT_EMAIL)).willReturn(new User());
        mockMvc.perform(post("/users/default"))
                .andExpect(content().string("User already exists"));
    }

    @Test
    public void testCreateDefaultUser_whenUserDoesNotExists() throws  Exception{
        given(userRepository.findByEmail(DEFAULT_EMAIL)).willReturn(null);
        MvcResult result = mockMvc.perform(post("/users/default")).andReturn();
        String content = result.getResponse().getContentAsString();

        assertEquals(8, content.length());
    }
}
