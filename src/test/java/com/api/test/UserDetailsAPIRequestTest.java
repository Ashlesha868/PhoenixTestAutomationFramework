package com.api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import com.api.utils.ConfigManager;
import com.api.utils.Spec_util;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.constants.Role.*;
import com.api.utils.AuthTokenProvider;

public class UserDetailsAPIRequestTest {
     @Test
	public void userDetailsAPIRequest() {
    	 
    	 
    			 given()
		.spec(Spec_util.requestSpecWithAuth(FD))
        .get("userdetails")
        .then()
        .log().all()
        .statusCode(200)
        .time(lessThan(1000L))
        .and()
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsAPIResponseSchema.json"));
        
        
        
        
	}

}
