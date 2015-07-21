package com.lundgbg.skrofs.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import javax.sql.DataSource;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

import com.lundgbg.skrofs.entity.Player;

public class PlayerDaoTest extends DaoTest {

	private PlayerDao playerDao;

	@Override
	void init(DataSource dataSource) {
		playerDao = new PlayerDao();
		playerDao.setDataSource(dataSource);
	}

	@Test
	public void shouldBePossibleToFindAllPlayers() {

		assertEquals(3, playerDao.findAll().size());
	}

	@Test
	public void shouldBePossibleToCreatePlayer() throws ParseException {
		Player player = createPlayer();
		playerDao.save(player);
		assertTrue(player.getId() > 0);

		Player dbPlayer = playerDao.findById(player.getId());
		ReflectionAssert.assertReflectionEquals(player, dbPlayer);
	}

	@Test
	public void shouldBePossibleToUpdatePlayer() throws ParseException {
		Player player = createPlayer();
		player.setId(1);
		playerDao.save(player);
		assertTrue(player.getId() > 0);

		Player dbPlayer = playerDao.findById(player.getId());
		ReflectionAssert.assertReflectionEquals(player, dbPlayer);
	}

	@Test
	public void shouldBePossibleToDeletePlayer() throws ParseException {
		playerDao.deleteById(3);

		assertEquals(2, playerDao.findAll().size());
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
