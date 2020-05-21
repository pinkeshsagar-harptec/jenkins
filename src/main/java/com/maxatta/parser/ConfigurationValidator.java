package com.maxatta.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Iterator;

/*
 Class validates json output configuration by mapping it to JsonNode and iterating over JsonNode
 to check for proper JsonPath.
 */
class ConfigurationValidator {

  /**
   * Method validates json output configuration and throws exception if null, empty or invalid
   * JsonPath.
   *
   * @param jsonOutputConfiguration json output configuration which will be converted to JsonNode.
   * @return JsonNode which is valid json output configuration.
   */
  public JsonNode validate(String jsonOutputConfiguration) {
    ObjectMapper objectMapper = JacksonObjectMapperSingleton.getInstance();
    JsonNode validatedJson = null;
    try {
      JsonNode node = objectMapper.readTree(jsonOutputConfiguration);
      if (!node.isEmpty()) {
        validatedJson = iterate(node);
      } else {
        throw new PathConfigurationException("Empty configuration");
      }
    } catch (JsonProcessingException e) {
      throw new JSONProcessingException("Invalid Json Configuration ", e);
    }
    return validatedJson;
  }

  /*
  Method iterate over jsonNode and throws exception if invalid JsonPath.
   */
  private JsonNode iterate(JsonNode jsonNode) {
    JsonPathValidator jsonPathValidator = new JsonPathValidator();
    Iterator<String> fieldNames = jsonNode.fieldNames();
    while (fieldNames.hasNext()) {
      String field = fieldNames.next();
      JsonNode node = jsonNode.get(field);
      if (node.isValueNode()) {
        if (node.isTextual()) {
          String path = node.toString();
          String truncatedPath = path.subSequence(1, path.length() - 1).toString();
          jsonPathValidator.validate(truncatedPath);
        } else {
          throw new PathConfigurationException(
              "Invalid JsonPath at field: '" + field + "' with JsonPath: '" + node.toString()
                  + "'");
        }
      } else if (node.isArray()) {
        for (int i = 0; i < node.size(); i++) {
          JsonNode arrayNode = node.get(i);
          if (arrayNode.isNull()) {
            throw new PathConfigurationException(
                "Array can not contain NULL element but found at field: '" + field
                    + "' with index: " + i);
          } else {
            iterate(arrayNode);
          }
        }
      } else {
        iterate(node);
      }
    }
    return jsonNode;
  }
}