package com.lundgbg.skrofs.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lundgbg.skrofs.dao.EventDao;
import com.lundgbg.skrofs.entity.Event;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventDao eventDao;

    @RequestMapping(method = RequestMethod.GET)
    public List<Event> findAll() {
        final List<Event> events = eventDao.findAll();
        return events;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Event save(@RequestBody final Event event) {
        eventDao.save(event);
        return event;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable final int id) {
        eventDao.deleteById(id);
    }
}
