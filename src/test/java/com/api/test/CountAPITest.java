package com.api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalToIgnoringCase;

import  static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constants.Role;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CountAPITest {
	
	
	@Test
	public void verifyCountAPIResponse() {
		given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.header("Authorization",getToken(Role.FD))
		.log().uri()
		.log().headers()
		.log().method()
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.statusCode(200)
		.body("message",equalToIgnoringCase("Success"))
		.time(lessThan(2000L))
		.body("data", notNullValue())
		.body("data.size()",equalTo(3))
		.body("data.count",everyItem(greaterThanOrEqualTo(0)))
		.body("data.label",everyItem(not(blankOrNullString())))
		.body("data.key", containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment") )
		.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchmea-FD.json"));
	}
	
	@Test
	public void countAPITest_MissingAuthToken() {
		given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.log().uri()
		.log().headers()
		.log().method()
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.statusCode(401);
		
	}
		
		
		
		
				
		

		
		
		
	

	
}
