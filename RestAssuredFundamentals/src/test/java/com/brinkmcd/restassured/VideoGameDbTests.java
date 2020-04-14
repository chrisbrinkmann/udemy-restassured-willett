package com.brinkmcd.restassured;

import org.junit.Test;


import com.brinkmcd.restassured.config.VideoGamesDbConfig;
import com.brinkmcd.restassured.config.VideoGamesDbEndpoints;
import com.brinkmcd.videogame.VideoGame;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class VideoGameDbTests extends VideoGamesDbConfig {

	@Test
	public void getAllGames() {
		given()
		.when()
		.get(VideoGamesDbEndpoints.ALL_VIDEO_GAMES)
		.then().log().all();
	}
	
	@Test
	public void createNewGameByJSON() {
		String requestBodyJson = "{\r\n" + 
				"  \"id\": 11,\r\n" + 
				"  \"name\": \"Final Fantasy 8\",\r\n" + 
				"  \"releaseDate\": \"2020-04-13T18:14:18.704Z\",\r\n" + 
				"  \"reviewScore\": 10,\r\n" + 
				"  \"category\": \"JRPG\",\r\n" + 
				"  \"rating\": \"Teen\"\r\n" + 
				"}";
		
		given()
			.body(requestBodyJson)
			.when()
			.post(VideoGamesDbEndpoints.ALL_VIDEO_GAMES)
			.then()
			.log().all();
	}
	
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
			.post(VideoGamesDbEndpoints.ALL_VIDEO_GAMES)
			.then()
			.log().all();
	}
	
	@Test
	public void updateGame() {
		String gameBodyJson = "{\r\n" + 
				"  \"id\": 11,\r\n" + 
				"  \"name\": \"Final Fantasy VIII\",\r\n" + 
				"  \"releaseDate\": \"2020-04-13T18:14:18.705Z\",\r\n" + 
				"  \"reviewScore\": 98,\r\n" + 
				"  \"category\": \"JRPG\",\r\n" + 
				"  \"rating\": \"Teen\"\r\n" + 
				"}";
		
		given()
			.body(gameBodyJson)
			.when()
			.put("/videogames/11")
			.then().log().all();
	}
	
	@Test
	public void deleteGame() {
		given()
			.when()
			.delete("/videogames/1")
			.then().log().all();
	}
	
	@Test
	public void getGame() {
		given()
		.pathParam("videoGameId", 5)
		.when()
		.get(VideoGamesDbEndpoints.SINGLE_VIDEO_GAME)
		.then().log().all();
	}
	
	@Test
	public void testVideoGameSerializationByJSON() {
		VideoGame videoGame = new VideoGame("99", "2010", "Assassin's Creed: Pirates", "Mature", "15", "Action");
	
		given()
			.body(videoGame)
		.when()
			.post(VideoGamesDbEndpoints.ALL_VIDEO_GAMES)
		.then();
	}
	
	@Test
	public void testVideoGameSchemaXML() {
		given()
			.pathParam("videoGameId", 5)
			.header("Content-Type", "application/xml")
			.header("Accept", "application/xml")
		.when()
			.get(VideoGamesDbEndpoints.SINGLE_VIDEO_GAME)
		.then()
			.body(matchesXsdInClasspath("VideoGameXSD.xsd"));
	}
	
	@Test
	public void testVideoGameSchemaJson() {
		given()
		.pathParam("videoGameId", 5)
	.when()
		.get(VideoGamesDbEndpoints.SINGLE_VIDEO_GAME)
	.then()
		.body(matchesJsonSchemaInClasspath("VideoGameJsonSchema.json"));
	}
	
	@Test
	public void convertJsonToPojo() {
		Response response =
				given()
					.pathParam("videoGameId", 5)
				.when()
					.get(VideoGamesDbEndpoints.SINGLE_VIDEO_GAME);
				
		VideoGame videoGame = response.getBody().as(VideoGame.class);
		
		System.out.println("Printing Pojo object converted from JSON res:");
		System.out.println(videoGame.toString());
	}
	
	@Test
	public void captureResponseTime() {
		long responseTime = get(VideoGamesDbEndpoints.ALL_VIDEO_GAMES).time();
		
		System.out.println("Response time (milliseconds): " + responseTime);
	}

	@Test
	public void assertOnResponseTime() {
		when()
			.get(VideoGamesDbEndpoints.ALL_VIDEO_GAMES)
		.then()
			.time(lessThan(10000L));
	}
}
