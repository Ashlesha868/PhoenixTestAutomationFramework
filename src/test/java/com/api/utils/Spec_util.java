package com.api.utils;

import static com.api.utils.ConfigManager.getProperty;

import org.hamcrest.Matchers;

import com.api.constants.Role;
import com.api.pojos.UserCredentials;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Spec_util {
	
	
	//GET-DEL
	public static RequestSpecification requestSpec()
	{
	RequestSpecification request = new RequestSpecBuilder()
	  .setBaseUri(getProperty("BASE_URI"))
	  .setContentType(ContentType.JSON)
	  .setAccept(ContentType.JSON)
	  .log(LogDetail.URI)
	  .log(LogDetail.METHOD)
	  .log(LogDetail.HEADERS)
	  .log(LogDetail.BODY)
	  .build();
	return request;
	}
	
	//POST-PUT-PATCH {body}
	public static RequestSpecification requestSpec(Object usercreds)
	{
	RequestSpecification request = new RequestSpecBuilder()
	  .setBaseUri(getProperty("BASE_URI"))
	  .setContentType(ContentType.JSON)
	  .setAccept(ContentType.JSON)
	  .setBody(usercreds)
	  .log(LogDetail.URI)
	  .log(LogDetail.METHOD)
	  .log(LogDetail.HEADERS)
	  .log(LogDetail.BODY)
	  .build();
	return request;
	
	}
	
	public static ResponseSpecification responseSpec_OK()
	{
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(200)
				.expectResponseTime(Matchers.lessThan(1000L))
				.log(LogDetail.ALL)
				.build();
		return responseSpecification;
		
	}
	
	public static RequestSpecification requestSpecWithAuth(Role role)
	{
		RequestSpecification request = new RequestSpecBuilder()
				  .setBaseUri(getProperty("BASE_URI"))
				  .setContentType(ContentType.JSON)
				  .setAccept(ContentType.JSON)
				  .addHeader("Authorization", AuthTokenProvider.getToken(role))
				  .log(LogDetail.URI)
				  .log(LogDetail.METHOD)
				  .log(LogDetail.HEADERS)
				  .log(LogDetail.BODY)
				  .build();
				return request;
	}
	
	public static ResponseSpecification responseSpec_JSON(int statuscode)
	{
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(statuscode)
				.expectResponseTime(Matchers.lessThan(1000L))
				.log(LogDetail.ALL)
				.build();
		return responseSpecification;
	}
		
		
		public static ResponseSpecification responseSpec_TEXT(int statuscode)
		{
			ResponseSpecification responseSpecification = new ResponseSpecBuilder()
					
					.expectStatusCode(statuscode)
					.expectResponseTime(Matchers.lessThan(1000L))
					.log(LogDetail.ALL)
					.build();
			return responseSpecification;
	
	
				
				
				
				
	}
}
