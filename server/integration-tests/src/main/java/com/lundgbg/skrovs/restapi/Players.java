package com.lundgbg.skrovs.restapi;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.lundgbg.skrofs.entity.Player;

public class Players {

	public static Response findAll() {
		return getPlayersBasePath().get();
	}

	public static Response findById(long playerId) {
		return getPlayersBasePath().get("{0}", playerId);
	}

	public static Response save(Player player) {
		return getPlayersBasePath().contentType(ContentType.JSON).body(player)
				.put();
	}

	public static Response deleteById(long playerId) {
		return getPlayersBasePath().delete("{0}", playerId);
	}

	private static RequestSpecification getPlayersBasePath() {
		return given().baseUri("http://localhost:8080/players");
	}

}
