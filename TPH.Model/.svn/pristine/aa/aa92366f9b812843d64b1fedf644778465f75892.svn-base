package com.artica.telesalud.tph.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.stereotype.Component;

@Component
public class JsonDateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date date, JsonGenerator gen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		String formattedDate = dateFormat.format(date);
		gen.writeString(formattedDate);
	}

}
