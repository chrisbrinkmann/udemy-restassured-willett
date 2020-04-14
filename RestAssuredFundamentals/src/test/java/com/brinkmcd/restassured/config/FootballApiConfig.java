package com.brinkmcd.restassured.config;

import org.junit.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class FootballApiConfig {
	
	public static RequestSpecification footballReqSpec;
	public static ResponseSpecification footballResSpec;
	
	@BeforeClass
	public static void setup() {
		footballReqSpec = new RequestSpecBuilder()
				.setBaseUri("https://api.football-data.org")
				.setBasePath("/v2")
				.addHeader("X-Auth-Token", "20c7dac147514a40a0cb0ea2973663b6")
				.addHeader("X-Response-Control", "minified")
				.addFilter(new RequestLoggingFilter())
				.addFilter(new ResponseLoggingFilter())
				.build();
				
		footballResSpec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.build();
		
		RestAssured.requestSpecification = footballReqSpec;
		RestAssured.responseSpecification = footballResSpec;
	}

}
