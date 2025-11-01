package com.api.test;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojos.UserCredentials;
import com.api.utils.ConfigManager;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {

	
		@Test
		public void loginAPITest() throws IOException {
			// RestAssured Code
			
			UserCredentials usercredentails = new UserCredentials("iamfd","password");
			
			
			given()
			  .baseUri(ConfigManager.getProperty("BASE_URI"))
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



