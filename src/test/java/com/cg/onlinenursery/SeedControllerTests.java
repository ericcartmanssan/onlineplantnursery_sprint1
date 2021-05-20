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
@AutoConfigureMockMvc
@SpringBootTest
public class SeedControllerTests {

	@Autowired
	private MockMvc mvc;

	@Test
	@Ignore
	public void givenSeed_whenGetSeed_thenStatus200() throws Exception {
		mvc.perform(get("http://localhost:9292/courses").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].name", is("Oracle")));
	}

	@Test
	public void givenSeed_whennoSeed_thenStatus400() throws Exception {
		mvc.perform(get("http://localhost:8888/plant")).andExpect(status().isNotFound());

	}

	@Test
	@Ignore
	public void test_get_all_success() throws Exception {
		mvc.perform(get("http://localhost:8888/seed")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}
}