package com.brinkmcd.restassured;

import org.junit.Test;

import com.brinkmcd.restassured.config.FootballApiConfig;

import static io.restassured.RestAssured.*;

public class FootballApiTests extends FootballApiConfig {
	
	@Test
	public void getAreaDetails() {
		given()
			.queryParam("areas", 2072)
			.when()
			.get("/areas");
	}
}
