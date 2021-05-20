package com.cg.onlinenursery;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PlantersControllerTest {
	@Autowired
	private MockMvc mvc;

	@Test
	@Ignore
	public void givenPlanters_whengetPlanters_thenStatus200() throws Exception {
		mvc.perform(get("http://localhost:8888/planters").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].orderId", is("1")));
	}

	@Test
	public void givenPlanters_whennoPlanters_thenStatus400() throws Exception {
		mvc.perform(get("http://localhost:8888/planters")).andExpect(status().isNotFound());

	}

	@Test
	@Ignore
	public void test_get_all_success() throws Exception {
		mvc.perform(get("http://localhost:8888/order")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}
}