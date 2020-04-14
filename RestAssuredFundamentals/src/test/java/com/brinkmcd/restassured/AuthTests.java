package com.brinkmcd.restassured;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

public class AuthTests {

	
	@BeforeClass
	public static void setup() {
		// Fiddler proxy - must be launched on port 8888.
		RestAssured.proxy("localhost", 8888);
	}
	
	/* These tests are just to show syntax. They do not call real endpoints */
	
	@Test
	public void basicPreemptiveAuthTest() {
		given()
			.auth()
			.preemptive()
			.basic("exampleusername", "exmaplepasswrd")
		.when()
			.get("http://localhost:8080/someEndpoint");
	}
	
	@Test
	public void basicChallangedAuthTest() {
		given()
			.auth().basic("exusername", "expassword")
		.when()
			.get("http://localhost:8080/someEndpoint");
	}
	
	@Test
	public void oath1Test() {
		// doesn't work NoClassDefFoundError - Token?
		given()
			.auth().oauth("consumerKey", "consumerSecret", "consumerAccessToken", "secretToken")
			.when()
				.get("http://localhost:8080/someEndpoint");
	}
	
	@Test
	public void oath2Test() {
		given()
			.auth().oauth2("accessToken")
			.when()
			.get("httpsalskdjfa;lskjdf");
	}
	
	@Test
	public void relaxedHTTPSTest() {
		given()
			.relaxedHTTPSValidation()
		.when()
			.get("/dummyendpoint");
	}
	
	@Test
	public void keystoreTest() {
		given()
			.keyStore("/pathToJKS", "password")
		.when()
			.get("/dummyendpoint");
	}
}
