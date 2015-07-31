package com.lundgbg.skrofs.it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

import com.jayway.restassured.response.Response;
import com.lundgbg.skrofs.entity.Player;
import com.lundgbg.skrovs.restapi.Players;

public class PlayerTest {

	@Test
	public void shouldBePossibleToFindAllPlayers() throws ParseException {

		Response response = Players.findAll();

		assertEquals(200, response.statusCode());
		assertEquals(3, response.as(Player[].class).length);
	}

	// @Test
	public void shouldBePossibleToCreatePlayer() throws ParseException {
		Player player = createPlayer();
		Response response = Players.save(player);
		Player playerResponse = response.as(Player.class);
		assertTrue(playerResponse.getId() > 0);

		player.setId(playerResponse.getId());
		ReflectionAssert.assertReflectionEquals(player, playerResponse);
	}

	// @Test
	public void shouldBePossibleToDeletePlayer() throws ParseException {
		Players.deleteById(3);

		assertEquals(2, Players.findAll().as(Player[].class).length);
	}

	private Player createPlayer() throws ParseException {
		Player player = new Player();
		player.setFirstName("Rut");
		player.setSurName("Akesson");
		player.setGender("F");
		player.setBirthday(DateUtils.parseDate("1985-07-01", "yyyy-MM-dd"));
		return player;
	}

}
