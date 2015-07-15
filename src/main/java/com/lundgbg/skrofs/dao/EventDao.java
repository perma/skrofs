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

import com.lundgbg.skrofs.entity.Event;

@Repository
public class EventDao extends AbstractDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertEvent;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertEvent = new SimpleJdbcInsert(dataSource).withTableName("Event").usingGeneratedKeyColumns("EventId");
    }

    public List<Event> findAll() {
        return this.jdbcTemplate.query("select * from Event", new EventMapper());
    }

    public void save(final Event event) {
        if (event.getId() <= 0) {
            create(event);
        } else {
            update(event);
        }
    }

    private void create(final Event event) {
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("NumberOfPlayers", event.getNumberOfPlayers(), Types.INTEGER);
        params.addValue("StartTime", event.getStartTime(), Types.TIMESTAMP);
        params.addValue("EndTime", event.getEndTime(), Types.TIMESTAMP);
        final Number newId = insertEvent.executeAndReturnKey(params);
        event.setId(newId.longValue());
    }

    private void update(final Event event) {
        jdbcTemplate.update("update Event set NumberOfPlayers=?, StartTime=?, EndTime=? where EventId=?",
                        event.getNumberOfPlayers(), event.getStartTime(), event.getEndTime(), event.getId());
    }

    public void deleteById(final int id) {
        jdbcTemplate.update("delete from Event where EventId=?", id);
    }

    private final class EventMapper implements RowMapper<Event> {

        @Override
        public Event mapRow(final ResultSet rs, final int rowNum) throws SQLException {
            final Event event = new Event();
            event.setId(rs.getInt("EventId"));
            event.setNumberOfPlayers(rs.getInt("NumberOfPlayers"));
            event.setStartTime(toUtilDate(rs.getTimestamp("StartTime")));
            event.setEndTime(toUtilDate(rs.getTimestamp("EndTime")));
            return event;
        }
    }

}
