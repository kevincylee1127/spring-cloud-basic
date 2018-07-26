package com.kevincylee.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevincylee.demo.bean.UserVo;
import com.kevincylee.demo.controller.Controller;
import com.kevincylee.demo.entity.User;
import com.kevincylee.demo.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
public class RestFulApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService service;

	@Test
	public void testGet() throws Exception {
		BDDMockito.given(service.findById(ArgumentMatchers.anyInt())).willReturn(new User("john", "john@test.com"));
		mockMvc.perform(MockMvcRequestBuilders.get("/restful").param("id", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("name").value("john"));
	}

	@Test
	public void testPost() throws Exception {
		BDDMockito.given(service.addUser(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
				.willReturn(new User("kevin", "kevin@test.com"));

		MultiValueMap<String, String> user = new LinkedMultiValueMap<>();
		user.add("name", "kevin");
		user.add("email", "kevin@test.com");
		mockMvc.perform(MockMvcRequestBuilders.post("/restful").params(user))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("name").value("kevin"))
				.andExpect(MockMvcResultMatchers.jsonPath("email").value("kevin@test.com"));
	}

	@Test
	public void testPatch() throws Exception {
		BDDMockito.given(
				service.editUser(ArgumentMatchers.anyInt(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
				.willReturn(new User("mark", "mark@test.com"));
		UserVo user = new UserVo();
		user.setId(1);
		user.setName("mark");
		user.setEmail("mark@test.com");
		ObjectMapper om = new ObjectMapper();
		mockMvc.perform(MockMvcRequestBuilders.patch("/restful").contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(user))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("name").value("mark"));
	}

	@Test
	public void testDel() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.delete("/restful").param("id", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("SUCCESS"));
	}
	
	@Test
	public void testPut() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.put("/restful").param("id", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("SUCCESS"));
	}

}
