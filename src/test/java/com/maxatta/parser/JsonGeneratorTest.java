package com.maxatta.parser;

import com.fasterxml.jackson.databind.JsonNode;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class JsonGeneratorTest {

  @Test
  public void test_1_extract_successful() {
    //GIVEN
    String expected = "{\n"
        + "  \"cost\" : 10,\n"
        + "  \"titles\" : [ {\n"
        + "    \"titles\" : [ \"Sayings of the Century\", \"Sword of Honour\", \"Moby Dick\", \"The Lord of the Rings\" ]\n"
        + "  }, {\n"
        + "    \"author\" : [ \"Nigel Rees\", \"Evelyn Waugh\", \"Herman Melville\", \"J. R. R. Tolkien\" ]\n"
        + "  } ],\n"
        + "  \"bicycle\" : {\n"
        + "    \"bicycle\" : {\n"
        + "      \"color\" : \"red\",\n"
        + "      \"price\" : 19.95\n"
        + "    }\n"
        + "  },\n"
        + "  \"bookCategory\" : [ {\n"
        + "    \"bookCategory\" : [ \"reference\", \"fiction\", \"fiction\", \"fiction\" ]\n"
        + "  } ]\n"
        + "}";
    String jsonInput = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/store.json");
    JsonNode jsonNode = JsonUtils
        .readJsonFromFileToJsonNode("src/main/resources/outputConfig/validConfig_store.json");

    //WHEN
    JsonGenerator generator = new JsonGenerator(jsonInput);
    JsonNode generate = generator.generate(jsonNode);

    //THEN
    boolean result = jsonNode.equals(generate);
    Assertions.assertThat(true).isEqualTo(result);
  }

  @Test
  public void test_2_NonExistingJsonPath_() {
    //GIVEN
    String jsonInput = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/store.json");
    JsonNode jsonNode = JsonUtils.readJsonFromFileToJsonNode(
        "src/main/resources/outputConfig/NonExistingJsonPathConfiguration.json");
    //WHEN
    JsonGenerator generator = new JsonGenerator(jsonInput);
    //THEN
    Assertions.assertThatThrownBy(() -> generator.generate(jsonNode)).isInstanceOf(
        JSONProcessingException.class);
    Assertions.assertThatThrownBy(() -> generator.generate(jsonNode)).hasMessageContaining(
        "Error while looking for jsonPath");
    Assertions.assertThatThrownBy(() -> generator.generate(jsonNode)).hasMessageContaining(
        "for field:");
  }
}