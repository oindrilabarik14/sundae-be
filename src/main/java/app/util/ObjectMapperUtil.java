/* (C) 2021 */
package app.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil<T> {
  private ObjectMapper jsonMapper;

  public ObjectMapperUtil() {
    this.jsonMapper = new ObjectMapper();
  }

  public T castObjectToPojo(Object source, Class<T> clazz) throws JsonProcessingException {
    String jsonString = jsonMapper.writeValueAsString(source);
    return jsonMapper.readValue(jsonString, clazz);
  }
}
