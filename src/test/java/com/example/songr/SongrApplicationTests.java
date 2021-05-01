package com.example.songr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest()
class SongrApplicationTests {
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	public void givenHomePageURI_whenMockMVC_thenReturnsIndexJSPViewName() throws Exception {
		this.mockMvc.perform(get("/album")).andDo(print())
				.andExpect(view().name("album.html"));
	}

	@Test
	public void givenHomePageURI_whenMockMVC_thenReturnsIndexJSPViewName2() throws Exception {
		this.mockMvc.perform(get("/add-song")).andDo(print())
				.andExpect(view().name("add-song.html"));
	}

	@Test
	public void givenGreetURI_whenMockMVC_thenVerifyResponse() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/capitalize/hello world!"))
				.andDo(print()).andExpect(status().isOk())
				.andReturn();

		assertEquals("text/plain;charset=UTF-8",
				mvcResult.getResponse().getContentType());
	}

}
