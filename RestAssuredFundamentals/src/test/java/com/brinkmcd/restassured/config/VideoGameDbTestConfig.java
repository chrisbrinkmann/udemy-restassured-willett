package com.brinkmcd.restassured.config;

import org.junit.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class VideoGameDbTestConfig {
	
	private static RequestSpecification videoGameRequestSpec;
	private static ResponseSpecification videoGameResponseSpec;
	
	@BeforeClass
	public static void setup() {
		videoGameRequestSpec = new RequestSpecBuilder()
				.setBaseUri("http://localhost")
				.setBasePath("/app/")
				.setPort(8080)
				.addHeader("Content-Type", "application/json")
				.addHeader("Accept", "application/json")
				.build();
		
		videoGameResponseSpec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.build();
		
		RestAssured.requestSpecification = videoGameRequestSpec;
		RestAssured.responseSpecification = videoGameResponseSpec;
	}
}
