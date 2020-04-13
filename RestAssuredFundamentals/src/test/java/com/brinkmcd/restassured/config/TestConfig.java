package com.brinkmcd.restassured.config;

import org.junit.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/* Deprecated class. Use App specific config class instead */

public class TestConfig {
	@BeforeClass
	public static void setup() {
		// RestAssured.proxy("localhost", 8888);
		
		RestAssured.baseURI = "http://localhost";
		RestAssured.basePath = "/app";
		RestAssured.port = 8080;
		
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.addHeader("Content-Type", "application/json")
				.addHeader("Accept", "application/json")
				.build();
		
		RestAssured.requestSpecification = requestSpecification;
		
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.build();
		
		RestAssured.responseSpecification = responseSpecification;
	}
}
