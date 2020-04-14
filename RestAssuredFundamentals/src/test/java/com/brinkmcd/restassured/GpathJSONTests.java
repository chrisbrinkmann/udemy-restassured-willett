package com.brinkmcd.restassured;

import static io.restassured.RestAssured.get;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.brinkmcd.restassured.config.FootballApiConfig;

import io.restassured.response.Response;

public class GpathJSONTests extends FootballApiConfig {

	@Test
	public void extractMapOfElementsWithFind() {

		Response response = get("/competitions/2021/teams");

		Map<String, ?> allTeamDataForSingleTeam = response
				.path("teams.find { it.name=='Manchester United FC' }");

		System.out.println("Map of the team data: " + allTeamDataForSingleTeam);
	}

	@Test
	public void extractSingleValueWithFind() {
		Response response = get("/teams/57");

		String playerName = response
				.path("squad.find { it.shirtNumber == 23}.name");

		System.out.println("Player name: " + playerName);
	}

	@Test
	public void extractListOfValuesWithFindAll() {
		Response response = get("/teams/57");

		List<String> playerNames = response
				.path("squad.findAll {it.shirtNumber >= 23}.name");

		System.out.println("List of players: " + playerNames);
	}

	@Test
	public void extractSingleValueWithHighestNumber() {
		Response response = get("/teams/57");

		String playerName = response.path("squad.max { it.shirtNumber}.name");

		System.out.println("Player name: " + playerName);
	}

	@Test
	public void extractMultipleValuesAndSumThem() {
		Response response = get("/teams/57");

		int sumOfIds = response.path("squad.collect {it.id }.sum()");

		System.out.println("Sum of all IDs = " + sumOfIds);
	}

	@Test
	public void extractMapOfObjectWithFindAndFindAll() {
		Response response = get("/teams/57");

		// findAll returns a collection, then find returns the first match
		Map<String, ?> playerOfCertainPosition = response.path(
				"squad.findAll {it.position == 'Defender'}.find {it.nationality == 'Greece'}");

		System.out.println("Details of player: " + playerOfCertainPosition);
	}

	@Test
	public void extractMapOfObjectWithFindAndFindAllWithParameters() {
		String position = "Defender";
		String nationality = "Greece";

		Response response = get("/teams/57");

		Map<String, ?> player = response.path(
				"squad.findAll {it.position == '%s'}.find {it.nationality == '%s'}",
				position, nationality);

		System.out.println("Player details: " + player);
	}
	
	@Test
	public void extractMultiplePlayers() {
		String position = "Midfielder";
		String nationality = "England";
		
		Response response = get("/teams/57");
		
		List<Map<String, ?>> allPlayers = response.path(
				"squad.findAll {it.position =='%s'}.findAll{it.nationality =='%s'}", position, nationality);
	
		System.out.println("List of players: " + allPlayers);
	}
}
