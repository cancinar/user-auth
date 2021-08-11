package com.gorillas.users.generator;

import com.github.javafaker.Faker;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BaseGenerator {

  private final Faker faker = new Faker();

  public static long randomId() {
    return faker.number().randomNumber();
  }

  public static String randomFirstName() {
    return faker.name().firstName();
  }

  public static String randomLastName() {
    return faker.name().lastName();
  }

  public static String randomEmail() {
    return faker.internet().emailAddress();
  }

  public static String randomPassword() {
    return faker.internet().password();
  }

  public static String randomUserName() {
    return faker.name().username();
  }

  public static int randomDigitNotZero() {
    return faker.number().randomDigitNotZero();
  }

  public static <T> List<T> randomList(Supplier<T> randomGeneratorFunction, int listSize) {
    return IntStream.rangeClosed(1, listSize)
        .boxed()
        .map(i -> randomGeneratorFunction.get())
        .collect(Collectors.toList());
  }

  public static <T> List<T> randomList(Supplier<T> randomGeneratorFunction) {
    return randomList(randomGeneratorFunction, randomDigitNotZero() + 1);
  }
}
