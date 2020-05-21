package com.maxatta.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class JsonUtils {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  public static String readJsonFromFileToString(String location) {
    String jsonString = null;
    try {
      jsonString = OBJECT_MAPPER.readTree(new File(location)).toString();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return jsonString;
  }

  public static JsonNode readJsonFromStringToJsonNode(String jsonString) {
    JsonNode jsonNode = null;
    try {
      jsonNode = OBJECT_MAPPER.readTree(jsonString);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return jsonNode;
  }

  public static JsonNode readJsonFromFileToJsonNode(String location) {
    JsonNode jsonNode = null;
    try {
      jsonNode = OBJECT_MAPPER.readTree(new File(location));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return jsonNode;
  }

}
