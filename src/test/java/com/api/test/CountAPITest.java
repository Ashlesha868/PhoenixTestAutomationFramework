package com.api.test;

import static com.api.constants.Role.FD;
import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import com.api.utils.Spec_util;

import org.testng.annotations.Test;



public class CountAPITest {
	
	
	@Test
	public void verifyCountAPIResponse() {
		given()
		
		.spec(Spec_util.requestSpecWithAuth(FD))
		.when()
		.get("/dashboard/count")
		.then()
		
		.body("message",equalToIgnoringCase("Success"))
		.spec(Spec_util.responseSpec_OK())
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
		
		.spec(Spec_util.requestSpec())
		.when()
		.get("/dashboard/count")
		.then()
		.spec(Spec_util.responseSpec_TEXT(401));
		
	}
		
		
		
		
				
		

		
		
		
	

	
}
