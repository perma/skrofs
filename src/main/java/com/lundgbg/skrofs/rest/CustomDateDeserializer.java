package com.lundgbg.skrofs.rest;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomDateDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(final JsonParser parser,
			final DeserializationContext arg1) throws IOException,
			JsonProcessingException {

		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		try {
			return formatter.parse(parser.getText());
		} catch (final ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
