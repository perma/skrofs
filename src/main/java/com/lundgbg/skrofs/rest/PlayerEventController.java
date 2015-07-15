package com.lundgbg.skrofs.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lundgbg.skrofs.dao.PlayerEventDao;
import com.lundgbg.skrofs.entity.PlayerEvent;

@RestController
public class PlayerEventController {

    @Autowired
    private PlayerEventDao playerEventDao;

    @RequestMapping(value = "/players/events", method = RequestMethod.GET)
    public List<PlayerEvent> findAll() {
        return playerEventDao.findAll();
    }

    @RequestMapping(value = "/players/{playerId}/events", method = RequestMethod.GET)
    public List<PlayerEvent> findAllByPlayerId(@PathVariable final long playerId) {
        return playerEventDao.findAllByPlayerId(playerId);
    }

    @RequestMapping(value = "/players/{playerId}/events/{eventId}", method = RequestMethod.GET)
    public PlayerEvent findByPlayerAndEventId(@PathVariable final long playerId, @PathVariable final long eventId) {
        return playerEventDao.findByPlayerAndEventId(playerId, eventId);
    }

    @RequestMapping(value = "/players/events/{eventId}", method = RequestMethod.GET)
    public List<PlayerEvent> findByEventId(@PathVariable final long eventId) {
        return playerEventDao.findByEventId(eventId);
    }

    @RequestMapping(value = "/players/{playerId}/events/{eventId}", method = RequestMethod.PUT)
    public void save(@RequestBody final PlayerEvent playerEvent) {
        playerEventDao.save(playerEvent);
    }

    @RequestMapping(value = "/players/{playerId}/events/{eventId}", method = RequestMethod.DELETE)
    public void deleteByPlayerAndEventId(@PathVariable final long playerId, @PathVariable final long eventId) {
        playerEventDao.deleteByPlayerAndEventId(playerId, eventId);
    }
}
