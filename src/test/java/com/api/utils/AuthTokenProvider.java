package com.api.utils;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import com.api.pojos.UserCredentials;
import com.api.constants.*;
import io.restassured.http.ContentType;
import static com.api.constants.Role.*;

public class AuthTokenProvider {
	private AuthTokenProvider() {
		
	}


	public static String getToken(Role role) {
		//I want to make the request for login API and we want to extract the token and print it on the console.
		
		UserCredentials userCredentials = null;
		if(role == FD){
			userCredentials = new UserCredentials("iamfd", "password");
			
		}
		
		if(role  == QC){
			userCredentials = new UserCredentials("iamqc", "password");
			
		}
		
		if(role == SUP){
			userCredentials = new UserCredentials("iamsup", "password");
			
		}
		
		if(role == ENG){
			userCredentials = new UserCredentials("iameng", "password");
			
		}
		
		
		
		
	String token =	given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
				.contentType(ContentType.JSON)
				.body(userCredentials)
				.when()
				.post("login")
				.then()
				.log().ifValidationFails()
				.statusCode(200)
				.body("message",equalToCompressingWhiteSpace("Success"))
				.extract()
				.body()
				.jsonPath()
				.getString("data.token");
				

	
	  System.out.println("----------------------------------------------");
	 
	  return token;
	}
	}


