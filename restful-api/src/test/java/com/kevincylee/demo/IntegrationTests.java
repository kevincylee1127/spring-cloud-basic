package com.kevincylee.demo;

import org.apache.http.client.utils.URIBuilder;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.JsonObject;
import com.kevincylee.demo.entity.User;

/**
 * ClassName: IntegrationTests
 * 
 * @author Chen Yuan Lee (Kevin)
 * @version 1.0
 * @createTime: 2018/07/27 10:05:30
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGet() throws Exception {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("/restful").queryParam("id", 1);
		ResponseEntity<User> response = restTemplate.getForEntity(builder.toUriString(), User.class);

		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody().getName()).isEqualTo("john");
		Assertions.assertThat(response.getBody().getEmail()).isEqualTo("john@test.com");
	}

	@Test
	public void testPost() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("name", "kevin");
		params.add("email", "kevin@test.com");

		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(params,
				httpHeader);
		ResponseEntity<User> response = restTemplate.postForEntity("/restful", httpEntity, User.class);

		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody().getName()).isEqualTo("kevin");
		Assertions.assertThat(response.getBody().getEmail()).isEqualTo("kevin@test.com");
	}

	@Test
	public void testPatch() throws Exception {
		JsonObject json = new JsonObject();
		json.addProperty("id", 1);
		json.addProperty("name", "mark");
		json.addProperty("email", "mark@test.com");

		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity = new HttpEntity<String>(json.toString(), httpHeader);
		ResponseEntity<User> response = restTemplate.exchange("/restful", HttpMethod.PATCH, httpEntity, User.class);

		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody().getName()).isEqualTo("mark");
		Assertions.assertThat(response.getBody().getEmail()).isEqualTo("mark@test.com");
	}

	@Test
	public void testDel() throws Exception {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("/restful").queryParam("id", 2);
		HttpHeaders httpHeader = new HttpHeaders();
		HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeader);
		ResponseEntity<String> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.DELETE, httpEntity,
				String.class);

		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody()).isEqualTo("SUCCESS");
	}

	@Test
	public void testPut() throws Exception {
//		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("/restful").queryParam("id", 1);
		URIBuilder uri = new URIBuilder();
		uri.setPath("/restful");
		uri.addParameter("id", "1");
		HttpHeaders httpHeader = new HttpHeaders();
		HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeader);
		ResponseEntity<String> response = restTemplate.exchange(uri.build(), HttpMethod.PUT, httpEntity, String.class);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody()).isEqualTo("SUCCESS");
	}

}
