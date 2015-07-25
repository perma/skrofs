package com.lundgbg.skrofs.dao;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

import com.lundgbg.skrofs.entity.PlayerEvent;

public class PlayerEventDaoTest extends DaoTest {

	private PlayerEventDao playerEventDao;

	@Override
	void init(DataSource dataSource) {
		playerEventDao = new PlayerEventDao();
		playerEventDao.setDataSource(dataSource);
	}

	@Test
	public void shouldBePossibleToFindAllPlayerEvents() {

		assertEquals(1, playerEventDao.findAll().size());
	}

	@Test
	public void shouldBePossibleToCreatePlayerEvent() throws ParseException {
		PlayerEvent playerEvent = createPlayerEvent();
		playerEventDao.save(playerEvent);

		List<PlayerEvent> allPlayerEvents = playerEventDao.findAll();
		assertEquals(2, allPlayerEvents.size());
		PlayerEvent dbPlayerEvent = allPlayerEvents.get(1);
		ReflectionAssert.assertReflectionEquals(playerEvent, dbPlayerEvent);
	}

	@Test
	public void shouldBePossibleToUpdatePlayerEvent() throws ParseException {
		PlayerEvent playerEvent = createPlayerEvent();
		playerEventDao.save(playerEvent);

		playerEvent.setRound14(21);
		playerEventDao.save(playerEvent);

		List<PlayerEvent> allPlayerEvents = playerEventDao.findAll();
		assertEquals(2, allPlayerEvents.size());
		PlayerEvent dbPlayerEvent = allPlayerEvents.get(1);
		ReflectionAssert.assertReflectionEquals(playerEvent, dbPlayerEvent);
	}

	@Test
	public void shouldBePossibleToFindAllPlayerEventsByPlayerId()
			throws ParseException {
		PlayerEvent expectedPlayerEvent = createPlayerEvent();
		expectedPlayerEvent.setPlayerId(1);
		List<PlayerEvent> allPlayerEventsForPlayer = playerEventDao
				.findAllByPlayerId(1);

		assertEquals(1, allPlayerEventsForPlayer.size());
		PlayerEvent actualPlayerEvent = allPlayerEventsForPlayer.get(0);
		ReflectionAssert.assertReflectionEquals(expectedPlayerEvent,
				actualPlayerEvent);
	}

	@Test
	public void shouldBePossibleToFindAllPlayerEventsByEventId()
			throws ParseException {
		PlayerEvent expectedPlayerEvent = createPlayerEvent();
		expectedPlayerEvent.setPlayerId(1);
		List<PlayerEvent> allPlayerEventsForPlayer = playerEventDao
				.findByEventId(1);

		assertEquals(1, allPlayerEventsForPlayer.size());
		PlayerEvent actualPlayerEvent = allPlayerEventsForPlayer.get(0);
		ReflectionAssert.assertReflectionEquals(expectedPlayerEvent,
				actualPlayerEvent);
	}

	@Test
	public void shouldBePossibleToFindAllPlayerEventsByPlayerAndEventId()
			throws ParseException {
		PlayerEvent expectedPlayerEvent = createPlayerEvent();
		expectedPlayerEvent.setPlayerId(1);

		PlayerEvent actualPlayerEvent = playerEventDao.findByPlayerAndEventId(
				1, 1);
		ReflectionAssert.assertReflectionEquals(expectedPlayerEvent,
				actualPlayerEvent);
	}

	@Test
	public void shouldBePossibleToDeletePlayerEvent() throws ParseException {
		playerEventDao.deleteByPlayerAndEventId(1, 1);

		assertEquals(0, playerEventDao.findAll().size());
	}

	private PlayerEvent createPlayerEvent() throws ParseException {
		PlayerEvent playerEvent = new PlayerEvent();
		playerEvent.setEventId(1);
		playerEvent.setPlayerId(2);
		playerEvent.setRound3(3);
		playerEvent.setRound4(4);
		playerEvent.setRound5(5);
		playerEvent.setRound6(6);
		playerEvent.setRound7(7);
		playerEvent.setRound8(8);
		playerEvent.setRound9(9);
		playerEvent.setRound10(10);
		playerEvent.setRound11(11);
		playerEvent.setRound12(12);
		playerEvent.setRound13(13);
		playerEvent.setRound14(14);
		return playerEvent;
	}

}
