package com.SpringProject.demo;

import com.SpringProject.demo.User.User;
import com.SpringProject.demo.User.UserController;
import com.SpringProject.demo.User.UserRepository;
import com.SpringProject.demo.WebController.RegistrationController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.integration.json.SimpleJsonSerializer;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class DemoApplicationTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserRepository userRepository;

    @Test
    public void addUserWhoDoesntExistInDB()
    {
        User userInfo = new User("testUsername","testPassword");
        try
        {

            mvc.perform(post("register").contentType(MediaType.APPLICATION_JSON).content(SimpleJsonSerializer.toJson(userInfo))).andExpect(status().isOk());
        }
        catch (Exception exception)
        {

        }
    }

}