package com.github.deividasp.hstracker.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
public class MapSerializer extends JsonSerializer<Map<?, ?>> {

	@Override
	public void serialize(Map<?, ?> map, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {
		generator.writeStartArray();

		for (Entry<?, ?> entry : map.entrySet()) {
			generator.writeStartObject();
			generator.writeObjectField("key", entry.getKey());
			generator.writeObjectField("value", entry.getValue());
			generator.writeEndObject();
		}

		generator.writeEndArray();
	}

}