package com.brinkmcd.restassured;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

import org.junit.Test;

import com.brinkmcd.restassured.config.VideoGamesDbConfig;
import com.brinkmcd.restassured.config.VideoGamesDbEndpoints;

public class MyFirstTest extends VideoGamesDbConfig {
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
		get(VideoGamesDbEndpoints.ALL_VIDEO_GAMES)
		.then().log().all();
	}
}
