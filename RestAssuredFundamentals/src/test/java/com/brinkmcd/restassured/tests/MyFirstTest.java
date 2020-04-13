package com.brinkmcd.restassured.tests;
import org.junit.Test;

import com.brinkmcd.restassured.config.VideoGameDbTestConfig;

import static io.restassured.RestAssured.*;

public class MyFirstTest extends VideoGameDbTestConfig {
	@Test
	public void myFirstTest() {
		given()
			.log().all()
		.when().get("videogames")
		.then()
			.log().all();
			
	}
}
