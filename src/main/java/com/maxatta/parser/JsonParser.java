package com.maxatta.parser;

import com.fasterxml.jackson.databind.JsonNode;

/*
 class parses input json based on output json configuration.
 */
public class JsonParser {

  private JsonNode jsonNode;

  public JsonParser() {
  }

  /**
   * Constructor map json output configuration to JsonNode so that it can be used to extract values
   * from related input json string.
   *
   * @param jsonOutputConfiguration has json output format and JsonPath.
   */
  public JsonParser(String jsonOutputConfiguration) {
    ConfigurationValidator validator = new ConfigurationValidator();
    this.jsonNode = validator.validate(jsonOutputConfiguration);
  }

  /**
   * Method extracts values from input json string based on JsonPath specified in
   * jsonOutputConfiguration.
   *
   * @param jsonInput json string from which values are tobe extracted.
   * @return json string replaced with values of JsonPath.
   */
  public String lookUp(String jsonInput) {
    JsonGenerator jsonGenerator = new JsonGenerator(jsonInput);
    return jsonGenerator.generate(jsonNode).toPrettyString();
  }

  /**
   * Method extracts values from input json string based on JsonPath specified in
   * jsonOutPutConfiguration.
   *
   * @param jsonOutPutConfiguration has json output format and JsonPath
   * @param jsonInput               json string from which values are tobe extracted.
   * @return json string replaced with values of JsonPath.
   */
  public String lookUp(String jsonOutPutConfiguration, String jsonInput) {
    ConfigurationValidator validator = new ConfigurationValidator();
    JsonNode jsonNode = validator.validate(jsonOutPutConfiguration);
    JsonGenerator generator = new JsonGenerator(jsonInput);
    return generator.generate(jsonNode).toPrettyString();
  }
}
