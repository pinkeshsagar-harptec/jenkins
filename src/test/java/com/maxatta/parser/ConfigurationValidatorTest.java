package com.maxatta.parser;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ConfigurationValidatorTest {

  @Test
  public void test_1_valid_JsonConfig_successful() {
    ConfigurationValidator validator = new ConfigurationValidator();
    String json = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/validConfig_store.json");
    validator.validate(json);
  }

  @Test
  public void test_2_Invalid_JsonConfig_with_null_jsonPath_InvalidPathConfiguration() {
    ConfigurationValidator validator = new ConfigurationValidator();
    String json = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/invalidConfigNullJsonPath.json");
    // assertions
    Assertions.assertThatThrownBy(() -> validator
        .validate(json))
        .isInstanceOf(
            PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> validator
        .validate(json))
        .hasMessageContaining("Invalid JsonPath at field:");
  }

  @Test
  public void test_3_Invalid_JsonConfig_with_null_jsonPath_successful() {
    ConfigurationValidator validator = new ConfigurationValidator();
    String json = JsonUtils.readJsonFromFileToString(
        "src/main/resources/outputConfig/invalidConfigNullJsonPath_1.json");
    // assertions
    Assertions.assertThatThrownBy(() -> validator
        .validate(json))
        .isInstanceOf(
            PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> validator
        .validate(json))
        .hasMessageContaining("Invalid JsonPath at field:");
  }

  @Test
  public void test_4_Invalid_JsonConfig_with_null_jsonPath_successful() {
    ConfigurationValidator validator = new ConfigurationValidator();
    String json = JsonUtils.readJsonFromFileToString(
        "src/main/resources/outputConfig/invalidConfigNullJsonPath_2.json");
    // assertions
    Assertions.assertThatThrownBy(() -> validator
        .validate(json))
        .isInstanceOf(
            PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> validator
        .validate(json))
        .hasMessageContaining("Invalid JsonPath at field:");
  }

  @Test
  public void test_5_Invalid_JsonConfig_with_null_jsonPath_successful() {
    ConfigurationValidator validator = new ConfigurationValidator();
    String json = JsonUtils.readJsonFromFileToString(
        "src/main/resources/outputConfig/invalidConfigNullJsonPath_3.json");
//    validator
//        .validate(readJson("src/main/resources/jayway/invalidConfigNullJsonPath_3.json"));
    // assertions
    Assertions.assertThatThrownBy(() -> validator
        .validate(json))
        .isInstanceOf(
            PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> validator
        .validate(json))
        .hasMessageContaining("Array can not contain NULL element but found at field");
    Assertions.assertThatThrownBy(() -> validator
        .validate(json))
        .hasMessageContaining("with index:");
  }

  @Test
  public void test_6_Invalid_JsonConfig_successful() {
    String invalidJson = "{\n"
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
    ConfigurationValidator validator = new ConfigurationValidator();
    // assertions
    Assertions.assertThatThrownBy(() -> validator
        .validate(invalidJson))
        .isInstanceOf(
            JSONProcessingException.class);
    Assertions.assertThatThrownBy(() -> validator
        .validate(invalidJson))
        .hasMessageContaining("Invalid Json Configuration");
  }

  @Test
  public void test_7_empty_json_config() {
    ConfigurationValidator configurationValidator = new ConfigurationValidator();
    //assertions
    Assertions.assertThatThrownBy(() -> configurationValidator.validate(""))
        .isInstanceOf(PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> configurationValidator.validate(""))
        .hasMessageContaining("Empty configuration");
    Assertions.assertThatThrownBy(() -> configurationValidator.validate("{}"))
        .isInstanceOf(PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> configurationValidator.validate("{}"))
        .hasMessageContaining("Empty configuration");
  }

  @Test
  public void test_8_invalid_jsonPath_Exception() {
    ConfigurationValidator configurationValidator = new ConfigurationValidator();
    String json = JsonUtils.readJsonFromFileToString(
        "src/main/resources/outputConfig/invalidConfigInvalidJsonPath_0.json");
    //assertions
    Assertions.assertThatThrownBy(() -> configurationValidator.validate(json))
        .isInstanceOf(PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> configurationValidator.validate(json))
        .hasMessageContaining("Invalid json-path =>");

    String json2 = JsonUtils.readJsonFromFileToString(
        "src/main/resources/outputConfig/invalidConfigInvalidJsonPath_1.json");
    //assertions
    Assertions.assertThatThrownBy(() -> configurationValidator.validate(json2))
        .isInstanceOf(PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> configurationValidator.validate(json2))
        .hasMessageContaining("Invalid json-path =>");
  }

  @Test
  public void test_9_invalid_boolean_as_jsonPath_Exception() {
    ConfigurationValidator configurationValidator = new ConfigurationValidator();
    String json = JsonUtils.readJsonFromFileToString(
        "src/main/resources/outputConfig/invalidConfigInvalidJsonPath_2.json");
    //assertions
    Assertions.assertThatThrownBy(() -> configurationValidator.validate(json))
        .isInstanceOf(PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> configurationValidator.validate(json))
        .hasMessageContaining("Invalid JsonPath at field:");
    Assertions.assertThatThrownBy(() -> configurationValidator.validate(json))
        .hasMessageContaining("with JsonPath:");
  }

  @Test
  public void test_10_invalid_integer_as_jsonPath_Exception() {
    ConfigurationValidator configurationValidator = new ConfigurationValidator();
    String json = JsonUtils.readJsonFromFileToString(
        "src/main/resources/outputConfig/invalidConfigInvalidJsonPath_3.json");
    //assertions
    Assertions.assertThatThrownBy(() -> configurationValidator.validate(json))
        .isInstanceOf(PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> configurationValidator.validate(json))
        .hasMessageContaining("Invalid JsonPath at field:");
    Assertions.assertThatThrownBy(() -> configurationValidator.validate(json))
        .hasMessageContaining("with JsonPath:");
  }

  //  @Test
  public void test_11_invalid_string_as_jsonPath_Exception() {
    ConfigurationValidator configurationValidator = new ConfigurationValidator();
    String json = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/multipleJsonPathInArray.json");
    //assertions
    Assertions.assertThatThrownBy(() -> configurationValidator.validate(json))
        .isInstanceOf(PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> configurationValidator.validate(json))
        .hasMessageContaining("Invalid json-path");
  }

  // TODO: clear this test case(multiple jsonPath inside array)
  //  @Test
  public void test_12_Multiple_jsonPaths_in_array_Exception() {
    ConfigurationValidator configurationValidator = new ConfigurationValidator();
    String json = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/multipleJsonPathInArray.json");
    configurationValidator.validate(json);
    //assertions
//    Assertions.assertThatThrownBy(() -> configurationValidator.validate(json))
//        .isInstanceOf(InvalidPathConfiguration.class);
//    Assertions.assertThatThrownBy(() -> configurationValidator.validate(json))
//        .hasMessageContaining("Invalid json-path");
  }

  @Test
  public void test_13_commaSeparated_jsonPath_Exception() {
    ConfigurationValidator configurationValidator = new ConfigurationValidator();
    String json = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/commaSeparatedJsonPath.json");
    //assertions
    Assertions.assertThatThrownBy(() -> configurationValidator.validate(json))
        .isInstanceOf(PathConfigurationException.class);
    Assertions.assertThatThrownBy(() -> configurationValidator.validate(json))
        .hasMessageContaining("Invalid json-path");
  }

  @Test
  public void test_6_valid_JsonConfig_successful() {
    ConfigurationValidator validator = new ConfigurationValidator();
    String json = JsonUtils
        .readJsonFromFileToString("src/main/resources/outputConfig/validConfig_store.json");
    long start = System.currentTimeMillis();
    for (int i = 0; i <= 100000; i++) {
      validator.validate(json);
    }
    long end = System.currentTimeMillis();
    System.out.println("Total time taken: " + (end - start));
  }
}