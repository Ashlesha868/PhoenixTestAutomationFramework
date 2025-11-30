package com.api.test;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.utils.Spec_util;

import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

import static io.restassured.RestAssured.*;

public class MasterAPITest {
	
	@Test
	
	public void MasterAPITest()
	{
		given()
		.spec(Spec_util.requestSpecWithAuth(FD))
		.when()
		.post("master")
		.then()
		.spec(Spec_util.responseSpec_OK())
		.body("message", equalTo("Success"))
		.body("data",notNullValue())
		.body("data",hasKey("mst_oem"))
		.body("data", hasKey("mst_model"))
		.body("$", hasKey("message"))
		.body("$", hasKey("data"))
		.body("data.mst_oem.size()",greaterThan(0))
		.body("data.mst_model.size()", greaterThan(0))
		.body("data.mst_oem.id", everyItem(notNullValue()))
		.body("data.mst_oem.name", everyItem(notNullValue()))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
	}
		
		
		@Test
		
		public void invalidTokenMasterAPIRequest()
		{
			given()
			
			.spec(Spec_util.requestSpec())
			.log().all()
			.when()
			.post("master")
			.then()
			.spec(Spec_util.responseSpec_TEXT(401));
		}
        	
		
	}
	

	


