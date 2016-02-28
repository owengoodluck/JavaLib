package com.owen.wms.lekani.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jxl.common.Logger;

public class JsonUtil {
	private static Logger log = Logger.getLogger(JsonUtil.class);

	public static <T> T convertJson2Object(String jsonString, Class<T> clazz) {
//		jsonString = jsonString.replaceAll("\\\\", "");
		ObjectMapper objectMapper = new ObjectMapper();
		T resp = null;
		try {
			resp = objectMapper.readValue(jsonString, clazz);
		} catch (JsonParseException e) {
			log.error(e.getMessage());
		} catch (JsonMappingException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
        return resp;
    }
	
}
