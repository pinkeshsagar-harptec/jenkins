package com.maxatta.parser;

import com.jayway.jsonpath.InvalidPathException;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.JsonPathException;

/*
  Class validates jsonPath and throw exception if invalid.
 */
class JsonPathValidator {

  /**
   * method validates JsonPath
   *
   * @param path JsonPath as string
   */
  public void validate(String path) {
    try {
      JsonPath.compile(path);
    } catch (InvalidPathException e) {
      throw new PathConfigurationException("Invalid json-path => " + path, e);
    } catch (JsonPathException e) {
      throw new PathConfigurationException("Error with json-path " + path, e);
    }
  }
}