package com.lundgbg.skrofs.rest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomDateTimeSerializer extends JsonSerializer<Date> {
	@Override
	public void serialize(final Date value, final JsonGenerator gen,
			final SerializerProvider arg2) throws IOException,
			JsonProcessingException {

		final SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss");
		final String formattedDate = formatter.format(value);

		gen.writeString(formattedDate);

	}
}
