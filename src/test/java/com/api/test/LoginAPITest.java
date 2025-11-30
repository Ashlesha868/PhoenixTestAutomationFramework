package com.api.test;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojos.UserCredentials;
import com.api.utils.ConfigManager;
import com.api.utils.Spec_util;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {

	
		@Test
		public void loginAPITest() throws IOException {
			// RestAssured Code
			
			UserCredentials usercredentails = new UserCredentials("iamfd","password");
			
			
			given()
			 .spec(Spec_util.requestSpec(usercredentails))
			 
			.when()
			 .post("login")
			 .then()
			 .spec(Spec_util.responseSpec_OK())
			 .and()
			 .body("message",equalTo("Success"))
			 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponeSchema.json"));
			
			 
			
			
			
			
			
		}
	}



