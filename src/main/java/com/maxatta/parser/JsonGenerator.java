package com.maxatta.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.InvalidJsonException;
import com.jayway.jsonpath.JsonPath;
import java.util.Iterator;

/*
 * Generate JsonNode by finding value for JsonPath in inputJson and return
 * Object then replace JsonPath with that returned result.
 */
class JsonGenerator {

  private DocumentContext documentContext;

  /**
   * Constructor converts inputJson to DocumentContext object and throws exceptions if inputJson is
   * null, empty or malformed.
   *
   * @param inputJson json input string
   */
  public JsonGenerator(String inputJson) {
    if (!inputJson.isEmpty()) {
      try {
        this.documentContext = JsonPath.parse(inputJson);
      } catch (InvalidJsonException e) {
        throw new JSONProcessingException("Invalid input json", e);
      }
    } else {
      throw new JSONProcessingException("Invalid input json");
    }
  }

  /**
   * Method iterate over jsonNode and throw error if it is null.
   *
   * @param jsonNode json output configuration mapped to jsonNode.
   * @return JsonNode with replaced JsonPath values.
   */
  public JsonNode generate(JsonNode jsonNode) {
    if (jsonNode != null) {
      return iterate(jsonNode);
    } else {
      throw new PathConfigurationException("Empty configuration");
    }
  }

  /**
   * Method iterate over JsonNode and replace JsonPath with extracted JsonPath object value from
   * DocumentContext object.
   *
   * @param jsonNode json output configuration mapped to jsonNode.
   * @return JsonNode with replaced JsonPath values.
   */
  private JsonNode iterate(JsonNode jsonNode) {
    Iterator<String> fieldNames = jsonNode.fieldNames();
    while (fieldNames.hasNext()) {
      String field = fieldNames.next();
      JsonNode node = jsonNode.get(field);
      if (node.isValueNode()) {
        if (node.isTextual()) {
          Object result = JsonExtractor.extract(documentContext, node.toString(), field);
          ((ObjectNode) jsonNode).putPOJO(field, result);
        }
      } else if (node.isArray()) {
        for (int i = 0; i < node.size(); i++) {
          JsonNode objectNode = node.get(i);
          iterate(objectNode);
        }
      } else {
        iterate(node);
      }
    }
    return jsonNode;
  }
}