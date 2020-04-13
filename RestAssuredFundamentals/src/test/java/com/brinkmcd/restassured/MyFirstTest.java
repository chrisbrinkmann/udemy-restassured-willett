package com.brinkmcd.restassured;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

import org.junit.Test;

import com.brinkmcd.restassured.config.VideoGamesDbTestConfig;
import com.brinkmcd.restassured.config.VideoGamesDbTestEndpoints;

public class MyFirstTest extends VideoGamesDbTestConfig {
	@Test
	public void myFirstTest() {
		given()
			.log().all()
		.when().get("videogames")
		.then()
			.log().all();
			
	}
	
	@Test
	public void myFirstTestWithEndpoint() {
		get(VideoGamesDbTestEndpoints.ALL_VIDEO_GAMES)
		.then().log().all();
	}
}
