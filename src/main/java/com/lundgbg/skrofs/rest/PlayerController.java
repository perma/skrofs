package com.lundgbg.skrofs.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lundgbg.skrofs.dao.PlayerDao;
import com.lundgbg.skrofs.entity.Player;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerDao playerDao;

    @RequestMapping(method = RequestMethod.GET)
    public List<Player> findAll() {
        return playerDao.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Player findById(@PathVariable final long id) {
        return playerDao.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Player save(@RequestBody final Player player) {
        playerDao.save(player);
        return player;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable final long id) {
        playerDao.deleteById(id);
    }
}
