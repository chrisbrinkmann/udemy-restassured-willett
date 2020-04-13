package com.brinkmcd.restassured.tests;

import org.junit.BeforeClass;

import io.restassured.RestAssured;

public class TestConfig {
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.basePath = "/app";
		RestAssured.port = 8080;
	}
}
