package com.maxatta.parser;

/*
  Exception class for path related configuration.
 */
public class PathConfigurationException extends RuntimeException {

  public PathConfigurationException(final String message) {
    super(message);
  }

  public PathConfigurationException(final String message, Throwable cause) {
    super(message, cause);
  }
}
