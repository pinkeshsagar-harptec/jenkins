package com.maxatta.parser;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
  Class create ObjectMapper object of Jackson-DataBind library
  which should be only one object in application.
 */
final class JacksonObjectMapperSingleton {

  private static ObjectMapper objectMapper;

  private JacksonObjectMapperSingleton() {
  }

  public static synchronized ObjectMapper getInstance() {
    if (objectMapper == null) {
      objectMapper = new ObjectMapper();
      objectMapper.enable(Feature.STRICT_DUPLICATE_DETECTION);
    }
    return objectMapper;
  }
}