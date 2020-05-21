package com.maxatta.parser;

import com.fasterxml.jackson.databind.JsonNode;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class JsonParserTest {

  @Test
  public void test_1_lookUp_ValidJsonOutputConfig_store_successful() {
    //GIVEN
    String inputJson = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/store.json");
    String outputConfig = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/validConfig_store.json");
    //WHEN
    JsonParser jsonParser = new JsonParser(outputConfig);
    String actual = jsonParser.lookUp(inputJson);
    //THEN
    JsonNode actualJsonNode = JsonUtils.readJsonFromStringToJsonNode(actual);
    JsonNode expectedJsonNode = JsonUtils
        .readJsonFromFileToJsonNode("src/main/resources/result/validConfigResult.json");
    boolean result = expectedJsonNode.equals(actualJsonNode);
    Assertions.assertThat(true).isEqualTo(result);
  }

  @Test
  public void test_1_lookUp_ValidJsonOutputConfig_EmptyInputJson_Exception() {
    //GIVEN
    String inputJson = JsonEx.INVALID_STORE_JSON;
    String outputConfig = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/validConfig_store.json");
    //WHEN
    JsonParser jsonParser = new JsonParser(outputConfig);
    //THEN
    Assertions.assertThatThrownBy(() -> jsonParser.lookUp(""))
        .isInstanceOf(JSONProcessingException.class);
    Assertions.assertThatThrownBy(() -> jsonParser.lookUp(""))
        .hasMessageContaining("Invalid input json");

    Assertions.assertThatThrownBy(() -> jsonParser.lookUp("{}"))
        .isInstanceOf(JSONProcessingException.class);
    Assertions.assertThatThrownBy(() -> jsonParser.lookUp("{}"))
        .hasMessageContaining("Error while looking for jsonPath");
    Assertions.assertThatThrownBy(() -> jsonParser.lookUp("{}"))
        .hasMessageContaining("for field:");

    Assertions.assertThatThrownBy(() -> jsonParser.lookUp(inputJson))
        .isInstanceOf(JSONProcessingException.class);
    Assertions.assertThatThrownBy(() -> jsonParser.lookUp(inputJson))
        .hasMessageContaining("Invalid input json");
  }

  @Test
  public void test_2_lookUp_No_JsonOutputConfig_store_Exception() {
    //GIVEN
    String inputJson = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/store.json");
    //WHEN
    JsonParser jsonParser = new JsonParser();
    //THEN
    Assertions.assertThatThrownBy(() -> jsonParser.lookUp(inputJson)).isInstanceOf(
        PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> jsonParser.lookUp(inputJson)).hasMessageContaining(
        "Empty configuration");
  }

  @Test
  public void test_3_lookUp_ValidJsonOutputConfig_store_Successful() {
    //GIVEN
    String inputJson = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/store.json");
    String outputConfig = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/validConfig_store.json");
    //WHEN
    JsonParser jsonParser = new JsonParser();
    String actual = jsonParser.lookUp(outputConfig, inputJson);
    //THEN
    JsonNode actualJsonNode = JsonUtils.readJsonFromStringToJsonNode(actual);
    JsonNode expectedJsonNode = JsonUtils
        .readJsonFromFileToJsonNode("src/main/resources/result/validConfigResult.json");
    boolean result = expectedJsonNode.equals(actualJsonNode);
    Assertions.assertThat(true).isEqualTo(result);
  }

  @Test
  public void test_4_lookUp_ValidJsonOutputConfig_twitter_Successful() {
    //GIVEN
    String inputJson = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/twitter.json");
    String outputConfig = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/validConfig_twitter.json");
    //WHEN
    JsonParser jsonParser = new JsonParser();
    String actual = jsonParser.lookUp(outputConfig, inputJson);
    //THEN
    JsonNode actualJsonNode = JsonUtils.readJsonFromStringToJsonNode(actual);
    JsonNode expectedJsonNode = JsonUtils
        .readJsonFromFileToJsonNode(
            "src/main/resources/result/validConfigResult_twitter.json");
    boolean result = expectedJsonNode.equals(actualJsonNode);
    Assertions.assertThat(true).isEqualTo(result);
  }

  @Test
  public void test_5_lookUp_InvalidOutputConfig_Null_Exception() {
    //GIVEN
    String outputConfig = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/invalidConfigNullJsonPath.json");
    //WHEN
    //THEN
    Assertions.assertThatThrownBy(() -> new JsonParser(outputConfig))
        .isInstanceOf(
            PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> new JsonParser(outputConfig))
        .hasMessageContaining("Invalid JsonPath at field:");
  }

  @Test
  public void test_5_lookUp_InvalidOutputConfig_Null_01_Exception() {
    //GIVEN
    String outputConfig = JsonUtils
        .readJsonFromFileToString(
            "src/main/resources/outputConfig/invalidConfigNullJsonPath_1.json");
    //WHEN
    //THEN
    Assertions.assertThatThrownBy(() -> new JsonParser(outputConfig))
        .isInstanceOf(
            PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> new JsonParser(outputConfig))
        .hasMessageContaining("Invalid JsonPath at field:");
  }

  @Test
  public void test_5_lookUp_InvalidOutputConfig_Null_02_Exception() {
    //GIVEN
    String outputConfig = JsonUtils
        .readJsonFromFileToString(
            "src/main/resources/outputConfig/invalidConfigNullJsonPath_2.json");
    //WHEN
    //THEN
    Assertions.assertThatThrownBy(() -> new JsonParser(outputConfig))
        .isInstanceOf(
            PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> new JsonParser(outputConfig))
        .hasMessageContaining("Invalid JsonPath at field:");
  }

  @Test
  public void test_5_lookUp_InvalidOutputConfig_Null_03_Exception() {
    //GIVEN
    String outputConfig = JsonUtils
        .readJsonFromFileToString(
            "src/main/resources/outputConfig/invalidConfigNullJsonPath_3.json");
    //WHEN
    //THEN
    Assertions.assertThatThrownBy(() -> new JsonParser(outputConfig))
        .isInstanceOf(
            PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> new JsonParser(outputConfig))
        .hasMessageContaining("Array can not contain NULL element but found at field");
    Assertions.assertThatThrownBy(() -> new JsonParser(outputConfig))
        .hasMessageContaining("with index:");
  }

  @Test
  public void test_6_lookUp_InvalidOutputConfig_Null_04_Exception() {
    //GIVEN
    String outputConfig = "{\n"
        + "  \"cost\": \"$.expensive\",\n"
        + "  \"titles\": [\n"
        + "    {\n"
        + "      \"titles\" : \"$.store.book[*].title\"\n"
        + "    },\n"
        + "    {\n"
        + "      \"author\" : \"$.store.book[*].author\"\n"
        + "    }\n"
        + "  ],\n"
        + "  \"bicycle\": {\n"
        + "    \"bicycle\": \"$.store.bicycle\"\n"
        + "  },\n"
        + "  \"bookCategory\": [\n"
        + "    {\n"
        + "      \"bookCategory\": \"$.store.book[*].category\"\n"
        + "    }\n"
        + "  ],\n"
        + "  null\n"
        + "}";
    //WHEN
    //THEN
    Assertions.assertThatThrownBy(() -> new JsonParser(outputConfig))
        .isInstanceOf(
            JSONProcessingException.class);
    Assertions.assertThatThrownBy(() -> new JsonParser(outputConfig))
        .hasMessageContaining("Invalid Json Configuration");
  }

  @Test
  public void test_7_lookUp_empty_OutputConfiguration_Exception() {
    //GIVEN
    //WHEN
    //THEN
    Assertions.assertThatThrownBy(() -> new JsonParser(""))
        .isInstanceOf(PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> new JsonParser(""))
        .hasMessageContaining("Empty configuration");
    Assertions.assertThatThrownBy(() -> new JsonParser("{}"))
        .isInstanceOf(PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> new JsonParser("{}"))
        .hasMessageContaining("Empty configuration");
  }

  @Test
  public void test_8_invalidConfigInvalidJsonPath_Exception() {
    //GIVEN
    String outputConfig = JsonUtils
        .readJsonFromFileToString(
            "src/main/resources/outputConfig/invalidConfigInvalidJsonPath_0.json");
    //WHEN
    //THEN
    Assertions.assertThatThrownBy(() -> new JsonParser(outputConfig))
        .isInstanceOf(PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> new JsonParser(outputConfig))
        .hasMessageContaining("Invalid json-path =>");

    String outputConfig2 = JsonUtils
        .readJsonFromFileToString(
            "src/main/resources/outputConfig/invalidConfigInvalidJsonPath_0.json");
    //assertions
    Assertions.assertThatThrownBy(() -> new JsonParser(outputConfig2))
        .isInstanceOf(PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> new JsonParser(outputConfig2))
        .hasMessageContaining("Invalid json-path =>");
  }

  @Test
  public void test_8_invalidConfigInvalidJsonPath_boolean_as_JsonPath_Exception() {
    //GIVEN
    String outputConfig = JsonUtils
        .readJsonFromFileToString(
            "src/main/resources/outputConfig/invalidConfigInvalidJsonPath_2.json");
    //WHEN
    //THEN
    Assertions.assertThatThrownBy(() -> new JsonParser(outputConfig))
        .isInstanceOf(PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> new JsonParser(outputConfig))
        .hasMessageContaining("Invalid JsonPath at field:");
    Assertions.assertThatThrownBy(() -> new JsonParser(outputConfig))
        .hasMessageContaining("with JsonPath:");
  }

  @Test
  public void test_9_invalidConfigInvalidJsonPath_integer_as_JsonPath_Exception() {
    //GIVEN
    String outputConfig = JsonUtils
        .readJsonFromFileToString(
            "src/main/resources/outputConfig/invalidConfigInvalidJsonPath_3.json");
    //WHEN
    //THEN
    Assertions.assertThatThrownBy(() -> new JsonParser(outputConfig))
        .isInstanceOf(PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> new JsonParser(outputConfig))
        .hasMessageContaining("Invalid JsonPath at field:");
    Assertions.assertThatThrownBy(() -> new JsonParser(outputConfig))
        .hasMessageContaining("with JsonPath:");
  }

  @Test
  public void test_9_invalidConfigInvalidJsonPath_commaSeparated_JsonPath_Exception() {
    //GIVEN
    String outputConfig = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/commaSeparatedJsonPath.json");
    //WHEN
    //THEN
    Assertions.assertThatThrownBy(() -> new JsonParser(outputConfig))
        .isInstanceOf(PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> new JsonParser(outputConfig))
        .hasMessageContaining("Invalid json-path");
  }

  @Test
  public void test_10_NonExisting_JsonPath_store_Exception() {
    //GIVEN
    String inputJson = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/store.json");
    String outputConfig = JsonUtils
        .readJsonFromFileToString(
            "src/main/resources/outputConfig/NonExistingJsonPathConfiguration.json");
    //WHEN
    JsonParser jsonParser = new JsonParser(outputConfig);
    //THEN
    Assertions.assertThatThrownBy(() -> jsonParser.lookUp(inputJson)).isInstanceOf(
        JSONProcessingException.class);
    Assertions.assertThatThrownBy(() -> jsonParser.lookUp(inputJson)).hasMessageContaining(
        "Error while looking for jsonPath");
    Assertions.assertThatThrownBy(() -> jsonParser.lookUp(inputJson)).hasMessageContaining(
        "for field:");
  }

  @Test
  public void test_11_lookUp_invalidJsonOutputConfig_Exception() {
    //GIVEN
    String inputJson = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/store.json");
    String outputConfig = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/invalidConfigNullJsonPath.json");
    //WHEN
    JsonParser parser = new JsonParser();
    //THEN
    Assertions.assertThatThrownBy(() -> parser.lookUp(outputConfig, inputJson))
        .isInstanceOf(
            PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> parser.lookUp(outputConfig, inputJson))
        .hasMessageContaining("Invalid JsonPath at field:");
  }

  @Test
  public void test_12_lookUp_invalidJsonOutputConfig_Exception() {
    //GIVEN
    String inputJson = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/store.json");
    String outputConfig = "    String outputConfig = \"{\\n\"\n"
        + "        + \"  \\\"cost\\\": \\\"$.expensive\\\",\\n\"\n"
        + "        + \"  \\\"titles\\\": [\\n\"\n"
        + "        + \"    {\\n\"\n"
        + "        + \"      \\\"titles\\\" : \\\"$.store.book[*].title\\\"\\n\"\n"
        + "        + \"    },\\n\"\n"
        + "        + \"    {\\n\"\n"
        + "        + \"      \\\"author\\\" : \\\"$.store.book[*].author\\\"\\n\"\n"
        + "        + \"    }\\n\"\n"
        + "        + \"  ],\\n\"\n"
        + "        + \"  \\\"bicycle\\\": {\\n\"\n"
        + "        + \"    \\\"bicycle\\\": \\\"$.store.bicycle\\\"\\n\"\n"
        + "        + \"  },\\n\"\n"
        + "        + \"  \\\"bookCategory\\\": [\\n\"\n"
        + "        + \"    {\\n\"\n"
        + "        + \"      \\\"bookCategory\\\": \\\"$.store.book[*].category\\\"\\n\"\n"
        + "        + \"    }\\n\"\n"
        + "        + \"  ],\\n\"\n"
        + "        + \"  null\\n\"\n"
        + "        + \"}\";";
    //WHEN
    JsonParser parser = new JsonParser();
    //THEN
    Assertions.assertThatThrownBy(() -> parser.lookUp(outputConfig, inputJson))
        .isInstanceOf(
            JSONProcessingException.class);
    Assertions.assertThatThrownBy(() -> parser.lookUp(outputConfig, inputJson))
        .hasMessageContaining("Invalid Json Configuration");
  }

  @Test
  public void test_13_lookUp_empty_OutputConfiguration_Exception() {
    //GIVEN
    String inputJson = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/store.json");
    //WHEN
    JsonParser parser = new JsonParser();
    //THEN
    Assertions.assertThatThrownBy(() -> parser.lookUp("", inputJson))
        .isInstanceOf(PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> parser.lookUp("", inputJson))
        .hasMessageContaining("Empty configuration");
    Assertions.assertThatThrownBy(() -> parser.lookUp("{}", inputJson))
        .isInstanceOf(PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> parser.lookUp("{}", inputJson))
        .hasMessageContaining("Empty configuration");
  }

  @Test
  public void test_14_lookUp_Valid_OutputConfiguration_invalidJsonInput_Exception() {
    //GIVEN
    String inputJson = "{\n"
        + "  \"store\": {\n"
        + "    \"book\": [\n"
        + "      {\n"
        + "        \"category\": \"reference\",\n"
        + "        \"author\": \"Nigel Rees\",\n"
        + "        \"title\": \"Sayings of the Century\",\n"
        + "        \"price\": 8.95\n"
        + "      },\n"
        + "      {\n"
        + "        \"category\": \"fiction\",\n"
        + "        \"author\": \"Evelyn Waugh\",\n"
        + "        \"title\": \"Sword of Honour\",\n"
        + "        \"price\": 12.99\n"
        + "      },\n"
        + "      {\n"
        + "        \"category\": \"fiction\",\n"
        + "        \"author\": \"Herman Melville\",\n"
        + "        \"title\": \"Moby Dick\",\n"
        + "        \"isbn\": \"0-553-21311-3\",\n"
        + "        \"price\": 8.99\n"
        + "      },\n"
        + "      {\n"
        + "        \"category\": \"fiction\",\n"
        + "        \"author\": \"J. R. R. Tolkien\",\n"
        + "        \"title\": \"The Lord of the Rings\",\n"
        + "        \"isbn\": \"0-395-19395-8\",\n"
        + "        \"price\": 22.99\n"
        + "      }\n"
        + "    ],\n"
        + "    \"bicycle\": {\n"
        + "      \"color\": \"red\",\n"
        + "      \"price\": 19.95\n"
        + "    }\n"
        + "  },\n"
        + "  \"expensive\": 10,\n"
        + "  abcd\n"
        + "}";
    String outputConfig = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/validConfig_store.json");
    //WHEN
    JsonParser parser = new JsonParser();
    //THEN
    Assertions.assertThatThrownBy(() -> parser.lookUp(inputJson))
        .isInstanceOf(JSONProcessingException.class);
    Assertions.assertThatThrownBy(() -> parser.lookUp(inputJson))
        .hasMessageContaining("Invalid input json");
  }

  @Test
  public void test_15_lookUp_emptyOutputConfiguration_EmptyJsonInput_Exception() {
    //GIVEN
    //WHEN
    JsonParser parser = new JsonParser();
    //THEN
    Assertions.assertThatThrownBy(() -> parser.lookUp("", ""))
        .isInstanceOf(PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> parser.lookUp("", ""))
        .hasMessageContaining("Empty configuration");
    Assertions.assertThatThrownBy(() -> parser.lookUp("{}", "{}"))
        .isInstanceOf(PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> parser.lookUp("{}", "{}"))
        .hasMessageContaining("Empty configuration");
  }

  @Test
  public void test_16_both_lookUp_method() {
    //GIVEN
    String storeInputJson = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/store.json");
    String storeOutputConfig = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/validConfig_store.json");

    String twitterInputJson = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/twitter.json");
    String twitterOutputConfig = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/validConfig_twitter.json");
    //WHEN
    JsonParser singleConfig = new JsonParser(storeOutputConfig);
    JsonParser configAtRuntime = new JsonParser();

    String storeSingleOutput = singleConfig.lookUp(storeInputJson);
    String singleConfigTwitter = singleConfig.lookUp(twitterOutputConfig, twitterInputJson);
    String runtimeTwitter = configAtRuntime.lookUp(twitterOutputConfig, twitterInputJson);
    //THEN
    JsonNode storeActualSingleConfigJsonNode = JsonUtils
        .readJsonFromStringToJsonNode(storeSingleOutput);
    JsonNode storeExpectedJsonNode = JsonUtils
        .readJsonFromFileToJsonNode("src/main/resources/result/validConfigResult.json");
    boolean storeResult = storeExpectedJsonNode.equals(storeActualSingleConfigJsonNode);

    Assertions.assertThat(true).isEqualTo(storeResult);

    JsonNode actualSingleConfigTwitter = JsonUtils
        .readJsonFromStringToJsonNode(singleConfigTwitter);
    JsonNode expectedJsonNodeTwitter = JsonUtils
        .readJsonFromFileToJsonNode("src/main/resources/result/validConfigResult_twitter.json");
    boolean twitterResult = expectedJsonNodeTwitter.equals(actualSingleConfigTwitter);

    Assertions.assertThat(true).isEqualTo(twitterResult);

    JsonNode twitterActualRuntimeJsonNode = JsonUtils.readJsonFromStringToJsonNode(runtimeTwitter);
    JsonNode twitterExpectedJsonNode = JsonUtils
        .readJsonFromFileToJsonNode("src/main/resources/result/validConfigResult_twitter.json");
    boolean twitterResult1 = twitterExpectedJsonNode.equals(twitterActualRuntimeJsonNode);

    Assertions.assertThat(true).isEqualTo(twitterResult1);
  }

  @Test
  public void test_17_duplicate_json_key_in_jsonOutputConfiguration_exception() {
    //GIVEN
    //WHEN
    //THEN
    Assertions.assertThatThrownBy(() -> new JsonParser(JsonEx.DUPLICATE_KEY_JSON))
        .isInstanceOf(JSONProcessingException.class);
    Assertions.assertThatThrownBy(() -> new JsonParser(JsonEx.DUPLICATE_KEY_JSON))
        .hasMessageContaining("Invalid Json Configuration");

    Assertions.assertThatThrownBy(
        () -> new JsonParser().lookUp(JsonEx.DUPLICATE_KEY_JSON, JsonEx.STORE_JSON))
        .isInstanceOf(JSONProcessingException.class);
    Assertions.assertThatThrownBy(
        () -> new JsonParser().lookUp(JsonEx.DUPLICATE_KEY_JSON, JsonEx.STORE_JSON))
        .hasMessageContaining("Invalid Json Configuration");

  }
}