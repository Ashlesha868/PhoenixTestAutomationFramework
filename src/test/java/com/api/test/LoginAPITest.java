package com.api.test;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.pojos.UserCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {

	
		@Test
		public void loginAPITest() {
			// RestAssured Code
			
			UserCredentials usercredentails = new UserCredentials("iamfd","password");
			
			given()
			  .baseUri("http://64.227.160.186:9000/v1")
			.and()
			.contentType(ContentType.JSON)
			.and()
			.accept(ContentType.JSON)
			.body(usercredentails)
			.log().uri()
			.log().body()
			.log().headers()
			.log().method()
			.when()
			 .post("login")
			 .then()
			 .log().all()
			 .statusCode(200)
			 .time(lessThan(1000L))
			 .and()
			 .body("message",equalTo("Success"))
			 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponeSchema.json"));
			
			 
			
			
			
			
			
		}
	}



