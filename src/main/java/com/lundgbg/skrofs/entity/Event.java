package com.lundgbg.skrofs.entity;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lundgbg.skrofs.rest.CustomDateTimeDeserializer;
import com.lundgbg.skrofs.rest.CustomDateTimeSerializer;

public class Event {

	private long id;
	private int numberOfPlayers;
	private Date startTime;
	private Date endTime;

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(final int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public Date getStartTime() {
		return startTime;
	}

	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public Date getEndTime() {
		return endTime;
	}

	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", numberOfPlayers=" + numberOfPlayers
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}

}
