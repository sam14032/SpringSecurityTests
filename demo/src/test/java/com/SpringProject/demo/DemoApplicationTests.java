package com.SpringProject.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.plugin2.util.PojoUtil.toJson;
import com.SpringProject.demo.UserInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	MockMvc mvc;

	@MockBean UserInfoRepository userInfoRepository;

	@Test
	public void addUserWhoDoesntExistInDB()
	{
		UserInfo userInfo = new UserInfo("testUsername","testPassword");
		try
		{

			mvc.perform(post("register").contentType(MediaType.APPLICATION_JSON).content(toJson(userInfo))).andExpect(status().isOk());
		}
		catch (Exception exception)
		{

		}
	}

}
