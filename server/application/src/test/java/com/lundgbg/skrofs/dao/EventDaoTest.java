package com.lundgbg.skrofs.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

import com.lundgbg.skrofs.entity.Event;

public class EventDaoTest extends DaoTest {

	private EventDao eventDao;

	@Override
	void init(DataSource dataSource) {
		eventDao = new EventDao();
		eventDao.setDataSource(dataSource);
	}

	@Test
	public void shouldBePossibleToFindAllEvents() {

		assertEquals(2, eventDao.findAll().size());
	}

	@Test
	public void shouldBePossibleToCreateEvent() throws ParseException {
		Event event = createEvent();
		event.setEndTime(null);
		eventDao.save(event);
		assertTrue(event.getId() > 0);

		List<Event> allEvents = eventDao.findAll();
		assertEquals(3, allEvents.size());
		Event dbEvent = allEvents.get(2);
		ReflectionAssert.assertReflectionEquals(event, dbEvent);
	}

	@Test
	public void shouldBePossibleToUpdateEvent() throws ParseException {
		Event event = createEvent();
		event.setEndTime(null);
		eventDao.save(event);
		assertTrue(event.getId() > 0);

		event.setEndTime(DateUtils.parseDate("2015-07-01 16:18:03",
				"yyyy-MM-dd HH:mm:ss"));
		eventDao.save(event);

		List<Event> allEvents = eventDao.findAll();
		assertEquals(3, allEvents.size());
		Event dbEvent = allEvents.get(2);
		ReflectionAssert.assertReflectionEquals(event, dbEvent);
	}

	@Test
	public void shouldBePossibleToDeleteEvent() throws ParseException {
		eventDao.deleteById(2);

		assertEquals(1, eventDao.findAll().size());
	}

	private Event createEvent() throws ParseException {
		Event event = new Event();
		event.setNumberOfPlayers(2);
		event.setStartTime(DateUtils.parseDate("2015-07-01 15:22:11",
				"yyyy-MM-dd HH:mm:ss"));
		event.setEndTime(DateUtils.parseDate("2015-07-01 16:18:03",
				"yyyy-MM-dd HH:mm:ss"));
		return event;
	}

}
