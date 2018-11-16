package com.SpringProject.demo;

import com.SpringProject.demo.User.User;
import com.SpringProject.demo.User.UserController;
import com.SpringProject.demo.User.UserRepository;
import com.SpringProject.demo.WebController.RegistrationController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.integration.json.SimpleJsonSerializer;
import org.springframework.integration.leader.Context;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(classes = {TestContext.class, WebApplicationContext.class})
@WebAppConfiguration
@DataJpaTest
public class RegistrationRequestTests {

    @Autowired

    MockMvc mvc;

    @InjectMocks
    User userInfo = new User("testUsername", "testPassword");

    @InjectMocks
    UserController userController;

    @InjectMocks
    private RegistrationController registrationController;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void addUserWhoDoesntExistInDB() {
        when(registrationController.saveUserInfo(userInfo));
        try {
            this.mvc.perform(post("localhost:8080/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(SimpleJsonSerializer.toJson(userInfo)))
                    .andExpect(status().isOk());
            verify(registrationController,times(1)).saveUserInfo(userInfo);
            verifyNoMoreInteractions(registrationController);
        } catch (Exception exception) {

        }
    }

    @Test
    public void addUserWhoExistInDB()
    {

        when(registrationController.saveUserInfo(userInfo));
        try {
            this.mvc.perform(post("localhost:8080/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(SimpleJsonSerializer.toJson(userInfo)))
                    .andExpect(status().isOk());
            verify(registrationController,times(2)).saveUserInfo(userInfo);
            verifyNoMoreInteractions(registrationController);
        } catch (Exception exception) {

        }
    }

}