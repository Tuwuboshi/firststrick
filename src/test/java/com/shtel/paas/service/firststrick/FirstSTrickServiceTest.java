package com.shtel.paas.service.firststrick;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.shtel.paas.sdk.app.PaasMainApp;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource
@SpringBootTest(classes = { PaasMainApp.class })
@WebAppConfiguration
public class FirstSTrickServiceTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	// before在每个TestCase执行之前执行
	@Before
	public void before() throws Exception {
		this.mockMvc = webAppContextSetup(this.wac).build();
	}

	@Test
	public void testHello() throws Exception {

		String url = "/hello";
		doRequest(url);

	}

	private void doRequest(String url) throws Exception {

		mockMvc.perform((get(url).header("Traceid",
				"363737838")/*
							 * .contentType(MediaType.APPLICATION_JSON)
							 * .accept(MediaType.APPLICATION_JSON) .content("")
							 */)).andExpect(status().isOk()).andDo(print());
	}

}
