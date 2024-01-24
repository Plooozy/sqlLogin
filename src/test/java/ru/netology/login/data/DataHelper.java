package ru.netology.login.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;


public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static AuthInfo getRealUser() {
        return new AuthInfo("vasya", "qwerty123");
    }

    private static String generateRandomLogin() {
        return faker.name().username();
    }

    private static String generateRandomPassword() {
        return faker.internet().password();
    }

    public static AuthInfo getRandomUser() {
        return new AuthInfo(generateRandomLogin(), generateRandomPassword());
    }

    public static VerificationCode getRandomCode() {
        return new VerificationCode(String.valueOf(faker.number().randomNumber(4, true)));
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }
}
