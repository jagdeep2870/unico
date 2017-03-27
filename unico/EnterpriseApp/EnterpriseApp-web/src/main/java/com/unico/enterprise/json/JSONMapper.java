package com.unico.enterprise.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

public interface JSONMapper {
	
	/**
	 * @param object
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public String toJsonString(Object object) throws JsonGenerationException, JsonMappingException, IOException;
	
}
