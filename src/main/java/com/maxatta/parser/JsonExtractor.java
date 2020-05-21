package com.maxatta.parser;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.PathNotFoundException;

/*
 Class extracts jsonPath values from input json which is converted into DocumentContext
 and throws exception if JsonPath does not exists in the input json string.
 */
final class JsonExtractor {

  private JsonExtractor() {
  }

  /**
   * @param jsonDoc  parsed json input string to Object
   * @param jsonPath json-path jsonPath
   * @param field    for which jsonPath is requested
   * @return value as object found with jsonPath
   */
  public static Object extract(DocumentContext jsonDoc, String jsonPath, String field) {
    Object result;
    String path = jsonPath.subSequence(1, jsonPath.length() - 1).toString();
    try {
      result = jsonDoc.read(path);
    } catch (PathNotFoundException e) {
      throw new JSONProcessingException(
          " Error while looking for jsonPath => '" + jsonPath + "' for field: '" + field + "'.", e);
    }
    return result;
  }
}