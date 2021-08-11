package com.gorillas.users.component.exception;

public class ValidationException extends RuntimeException{

  public ValidationException(String message){
    super(message);
  }
}
