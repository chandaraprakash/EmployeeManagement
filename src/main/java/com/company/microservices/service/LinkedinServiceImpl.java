package com.company.microservices.service;

import java.net.URISyntaxException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.company.microservices.data.model.Employee;
import com.company.microservices.data.model.Profile;
import com.company.microservices.restclient.RestClient;

@Service
public class LinkedinServiceImpl implements LinkedinService {

	@Autowired
	RestClient restClient;

	@Override
	public Profile getBasicProfileData() {
		String url = "https://api.linkedin.com/v1/people/~:(id,firstName,lastName,headline,num-connections,picture-url)?oauth2_access_token=AQVUjdMWQoI8BjaUKgqp6_tlAB7ZF75F0cb0Idk7IavB_8hQBwmdIgjyfAjl33d9cp8vO1p6SSMEcNVtr34vWp_Wzi5NF7oVsZ6nq9YNm8WgJkI_EyH2r36twF7UY87Pbpj96ZmHmPS6MqwfiMriXTvwEWSdWw1OTwCPUxc71UI1gJWT9Ko&format=json";
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<Profile> response;
		try {
			response = restClient.getResource(url, headers, Profile.class);

			return response.getBody();

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

}
