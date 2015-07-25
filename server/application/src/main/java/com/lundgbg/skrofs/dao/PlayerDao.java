package com.lundgbg.skrofs.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.lundgbg.skrofs.entity.Player;

@Repository
public class PlayerDao extends AbstractDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertPlayer;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertPlayer = new SimpleJdbcInsert(dataSource).withTableName("Player").usingGeneratedKeyColumns(
                        "PlayerId");
    }

    public List<Player> findAll() {
        return this.jdbcTemplate.query("select * from Player", new PlayerMapper());
    }

    public Player findById(final long id) {
        return this.jdbcTemplate.queryForObject("select * from Player where PlayerId=?", new PlayerMapper(), id);
    }

    public void save(final Player player) {
        if (player.getId() <= 0) {
            create(player);
        } else {
            update(player);
        }
    }

    private void create(final Player player) {
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("Firstname", player.getFirstName(), Types.VARCHAR);
        params.addValue("Surname", player.getSurName(), Types.VARCHAR);
        params.addValue("Gender", player.getGender(), Types.VARCHAR);
        params.addValue("Birthday", player.getBirthday(), Types.DATE);
        final Number newId = insertPlayer.executeAndReturnKey(params);
        player.setId(newId.longValue());
    }

    private void update(final Player player) {
        jdbcTemplate.update("update Player set Firstname=?, Surname=?, Gender=?, Birthday=? where PlayerId=?",
                        player.getFirstName(), player.getSurName(), player.getGender(), player.getBirthday(),
                        player.getId());
    }

    public void deleteById(final long id) {
        jdbcTemplate.update("delete from Player where PlayerId=?", id);
    }

    private final class PlayerMapper implements RowMapper<Player> {

        @Override
        public Player mapRow(final ResultSet rs, final int rowNum) throws SQLException {
            final Player player = new Player();
            player.setId(rs.getInt("PlayerId"));
            player.setFirstName(rs.getString("Firstname"));
            player.setSurName(rs.getString("Surname"));
            player.setGender(rs.getString("Gender"));
            player.setBirthday(toUtilDate(rs.getDate("Birthday")));
            return player;
        }
    }
}
