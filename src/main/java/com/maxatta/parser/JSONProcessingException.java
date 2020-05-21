package com.maxatta.parser;

public class JSONProcessingException extends RuntimeException {

  public JSONProcessingException(final String message) {
    super(message);
  }

  public JSONProcessingException(final String message, Throwable cause) {
    super(message, cause);
  }
}
