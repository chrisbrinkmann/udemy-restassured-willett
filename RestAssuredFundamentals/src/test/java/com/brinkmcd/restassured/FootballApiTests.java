package com.brinkmcd.restassured;

import org.junit.Test;

import com.brinkmcd.restassured.config.FootballApiConfig;

import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

public class FootballApiTests extends FootballApiConfig {
	
	@Test
	public void getAreaDetails() {
		given()
			.queryParam("areas", 2072)
			.when()
			.get("/areas");
	}
	
	@Test
	public void getTeamFoundedDate() {
		given()
			.when()
			.get("/teams/57")
			.then()
			.body("founded", equalTo(1886));
	}
	
	@Test
	public void getFirstTeamName() {
		given()
			.when()
			.get("/competitions/2021/teams")
			.then()
			.body("teams.name[0]", equalTo("Arsenal FC"));
	}
	
	@Test
	public void getAllTeamData() {
		String responseBody = get("/teams/57").asString();
		System.out.println("Printing reponse body:");
		System.out.println(responseBody);
	}
	
	@Test
	public void getAllTeamDataButCheckFirst() {
		Response response = 
			given()
			.when()
			.get("/teams/57")
			.then()
				.contentType(ContentType.JSON)
				.extract().response();
		
		String jsonResponseAsString = response.asString();
		
		System.out.println("Printing repsonse body:");
		System.out.println(jsonResponseAsString);
	}
	
	@Test
	public void extractHeaders() {
		Response response = 
				given()
				.when()
					.get("/teams/57")
					.then()
						.contentType(ContentType.JSON)
						.extract().response();
		
		
		Headers headers = response.getHeaders();
		
		String contentType = response.getHeader("Content-Type");
		
		System.out.println("Content Type: ");
		System.out.println(contentType);
	}
	
	@Test
	public void extractFirstTeamName() {
		String firstTeamName = 
				get("/competitions/2021/teams")
					.jsonPath()
					.getString("teams.name[0]");
		
		System.out.println(firstTeamName);
	}
	
	@Test
	public void extractAllTeamNames() {
		Response response =
				given()
				.when()
					.get("/competitions/2021/teams")
					.then()
						.extract().response();
		
		List<String> teamNames = response.path("teams.name");
		
		System.out.println("Team Names: ");
		for(String teamName : teamNames) {
			System.out.println(teamName);
		}
	}
}
