package com.company.microservices.restclient;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("restClient")
public class RestClient<T> {

	@Autowired
	private RestTemplate restTemplate;

	public ResponseEntity<T> getResource(String url, HttpHeaders headers, Class<T> response) throws URISyntaxException {
		RequestEntity<T> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, new URI(url));

		return restTemplate.exchange(requestEntity, response);
	}

}
