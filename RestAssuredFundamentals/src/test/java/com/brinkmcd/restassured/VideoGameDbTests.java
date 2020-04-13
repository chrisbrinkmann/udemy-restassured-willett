package com.brinkmcd.restassured;

import org.junit.Test;

import com.brinkmcd.restassured.config.VideoGamesDbTestConfig;
import com.brinkmcd.restassured.config.VideoGamesDbTestEndpoints;

import static io.restassured.RestAssured.*;

public class VideoGameDbTests extends VideoGamesDbTestConfig {

//	@Test
//	public void getAllGames() {
//		given()
//		.when()
//		.get(VideoGamesDbTestEndpoints.ALL_VIDEO_GAMES)
//		.then().log().all();
//	}
	
//	@Test
//	public void createNewGameByJSON() {
//		String requestBodyJson = "{\r\n" + 
//				"  \"id\": 11,\r\n" + 
//				"  \"name\": \"Final Fantasy 8\",\r\n" + 
//				"  \"releaseDate\": \"2020-04-13T18:14:18.704Z\",\r\n" + 
//				"  \"reviewScore\": 10,\r\n" + 
//				"  \"category\": \"JRPG\",\r\n" + 
//				"  \"rating\": \"Teen\"\r\n" + 
//				"}";
//		
//		given()
//			.body(requestBodyJson)
//			.when()
//			.post(VideoGamesDbTestEndpoints.ALL_VIDEO_GAMES)
//			.then()
//			.log().all();
//	}
	
	@Test
	public void createNewGameByXML( ) {
		String requestBodyXml = "  <videoGame category=\"JRPG\" rating=\"Teen\">\r\n" + 
				"    <id>12</id>\r\n" + 
				"    <name>Final Fantasy 9</name>\r\n" + 
				"    <releaseDate>2020-04-13T00:00:00-04:00</releaseDate>\r\n" + 
				"    <reviewScore>97</reviewScore>\r\n" + 
				"  </videoGame>";
		
		given()
			.body(requestBodyXml)
			.header("Accept", "application/xml")
			.header("Content-Type", "application/xml")
			.when()
			.post(VideoGamesDbTestEndpoints.ALL_VIDEO_GAMES)
			.then()
			.log().all();
	}
}
