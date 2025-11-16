package com.api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.constants.Role.*;
import com.api.utils.AuthTokenProvider;

public class UserDetailsAPIRequestTest {
     @Test
	public void userDetailsAPIRequest() {
    	 
    	 Header headerone = new Header("Authorization",AuthTokenProvider.getToken(FD));	
    			 given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
        .header(headerone)
        .and()
        .accept(ContentType.JSON)
        .log().uri()
        .log().method()
        .log().body()
        .log().headers()
        .when()
        .get("userdetails")
        .then()
        .log().all()
        .statusCode(200)
        .time(lessThan(1000L))
        .and()
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsAPIResponseSchema.json"));
        
        
        
        
	}

}
