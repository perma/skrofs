package com.lundgbg.skrofs.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.lundgbg.skrofs.entity.PlayerEvent;

@Repository
public class PlayerEventDao extends AbstractDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<PlayerEvent> findAll() {
        return this.jdbcTemplate.query("select * from PlayerEvent", new PlayerEventMapper());
    }

    public List<PlayerEvent> findAllByPlayerId(final long playerId) {
        return this.jdbcTemplate.query("select * from PlayerEvent where PlayerId=?", new PlayerEventMapper(), playerId);
    }

    public PlayerEvent findByPlayerAndEventId(final long playerId, final long eventId) {
        return this.jdbcTemplate.queryForObject("select * from PlayerEvent where PlayerId=? and EventId=?",
                        new PlayerEventMapper(), playerId, eventId);
    }

    public List<PlayerEvent> findByEventId(final long eventId) {
        return this.jdbcTemplate.query("select * from PlayerEvent where EventId=?", new PlayerEventMapper(), eventId);
    }

    public void save(final PlayerEvent playerEvent) {
        // Assume it is an update since this is the most common operation
        if (!update(playerEvent)) {
            create(playerEvent);
        }
    }

    private boolean create(final PlayerEvent playerEvent) {

        final SqlParameterSource params = new BeanPropertySqlParameterSource(playerEvent);
        final int rowsCreated = namedParameterJdbcTemplate
                        .update("insert into PlayerEvent ( PlayerId, EventId, Round3, Round4, Round5, Round6, Round7, Round8, "
                                        + "Round9, Round10, Round11, Round12, Round13, Round14 ) values ( :playerId, :eventId, "
                                        + ":round3, :round4, :round5, :round6, :round7, :round8, :round9, :round10, :round11, :round12, "
                                        + ":round13, :round14 )", params);
        return rowsCreated > 0;
    }

    private boolean update(final PlayerEvent playerEvent) {
        final SqlParameterSource params = new BeanPropertySqlParameterSource(playerEvent);
        final int rowsUpdated = namedParameterJdbcTemplate
                        .update("update PlayerEvent set Round3=:round3, Round4=:round4, "
                                        + "Round5=:round5, Round6=:round6, Round7=:round7, Round8=:round8, Round9=:round9, "
                                        + "Round10=:round10, Round11=:round11, Round12=:round12, Round13=:round13, Round14=:round14 where PlayerId=:playerId and EventId=:eventId",
                                        params);
        return rowsUpdated > 0;
    }

    public void deleteByPlayerAndEventId(final long playerId, final long eventId) {
        jdbcTemplate.update("delete from PlayerEvent where PlayerId=? and EventId=?", playerId, eventId);
    }

    private final class PlayerEventMapper implements RowMapper<PlayerEvent> {

        @Override
        public PlayerEvent mapRow(final ResultSet rs, final int rowNum) throws SQLException {
            final PlayerEvent playerEvent = new PlayerEvent();
            playerEvent.setPlayerId(rs.getLong("PlayerId"));
            playerEvent.setEventId(rs.getLong("EventId"));
            playerEvent.setRound3(rs.getInt("Round3"));
            playerEvent.setRound4(rs.getInt("Round4"));
            playerEvent.setRound5(rs.getInt("Round5"));
            playerEvent.setRound6(rs.getInt("Round6"));
            playerEvent.setRound7(rs.getInt("Round7"));
            playerEvent.setRound8(rs.getInt("Round8"));
            playerEvent.setRound9(rs.getInt("Round9"));
            playerEvent.setRound10(rs.getInt("Round10"));
            playerEvent.setRound11(rs.getInt("Round11"));
            playerEvent.setRound12(rs.getInt("Round12"));
            playerEvent.setRound13(rs.getInt("Round13"));
            playerEvent.setRound14(rs.getInt("Round14"));
            return playerEvent;
        }
    }

}
