package com.example.project;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.MediaType;


import com.example.project.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)	
public class ProjectTests {
	private MockMvc mockMvc; 

	@Autowired
	  WebApplicationContext context;
	 @Before
	  public void setup() throws Exception {
	    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	  }
	 
	 @Test
	 public void addUserTest_ok() throws Exception{
		User u=new User();
		 u.setUserId(1);
		 u.setUserName("jim");
		 u.setEmailId("jim@split.com");
		 u.setTotalBalance(0.0);
		 u.setBalanceStatus("settled up");
		byte[] iJson = toJson(u);
		mockMvc.perform(post("/user")
				.content(iJson)
	 			.contentType(MediaType.APPLICATION_JSON)
	 			.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());	 
	 }
	 @Test
	 public void addUserTest1_ok() throws Exception{
		User u=new User();
		 u.setUserId(2);
		 u.setUserName("jim1");
		 u.setEmailId("jim1@split.com");
		 u.setTotalBalance(0.0);
		 u.setBalanceStatus("settled up");
		byte[] iJson = toJson(u);
		mockMvc.perform(post("/user")
				.content(iJson)
	 			.contentType(MediaType.APPLICATION_JSON)
	 			.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());	 
	 }
	
	 @Test
		public void retrievetest_ok() throws Exception {
		 addUserTest_ok();
		 mockMvc.perform(get("/user/1" )).andDo(print())
         .andExpect(status().isOk())
         .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1))
         .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("jim"))
         .andExpect(MockMvcResultMatchers.jsonPath("$.emailId").value("jim@split.com"))
         .andExpect(MockMvcResultMatchers.jsonPath("$.totalBalance").value(0.0))
         .andExpect(MockMvcResultMatchers.jsonPath("$.balanceStatus").value("settled up"));

	 }
	 
	 public void updateUser_ok() throws Exception{
		 User u=new User();
		 u.setUserId(2);
		 u.setUserName("jim3");
		 u.setUserName("jim3@split.com");
		 u.setTotalBalance(0.0);
		 u.setBalanceStatus("settled up");
		byte[] iJson = toJson(u);
		mockMvc.perform(put("/user/1")
				.content(iJson)
	 			.contentType(MediaType.APPLICATION_JSON)
	 			.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
		 mockMvc.perform(get("/user/1" )).andDo(print())
         .andExpect(status().isOk())
         .andExpect(MockMvcResultMatchers.jsonPath("$.userID").value(2))
         .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("jim3"))
         .andExpect(MockMvcResultMatchers.jsonPath("$.emailId").value("jim3@split.com"))
         .andExpect(MockMvcResultMatchers.jsonPath("$.totalBalance").value(0.0))
         .andExpect(MockMvcResultMatchers.jsonPath("$.balanceStatus").value(""));

	 }
	 
	 @Test
		public void deleteEmployee_ok() throws Exception{
			User u = new User();
			 u.setUserId(4);
			 u.setUserName("jim4");
			 u.setUserName("jim4@split.com");
			 u.setTotalBalance(0.0);
			 u.setBalanceStatus("settled up");
			byte[] iJson = toJson(u);
			mockMvc.perform(post("/user")
					.content(iJson)
		 			.contentType(MediaType.APPLICATION_JSON)
		 			.accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());	 
			mockMvc.perform(delete("/user/4")
					.content(iJson)
		 			.contentType(MediaType.APPLICATION_JSON)
		 			.accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
		}


	 
	 
	 
	 private byte[] toJson(Object r) throws Exception {
	        ObjectMapper map = new ObjectMapper();
	        return map.writeValueAsString(r).getBytes();

}
}
