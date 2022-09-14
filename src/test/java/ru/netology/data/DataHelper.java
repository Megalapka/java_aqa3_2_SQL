package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    
    private static Faker faker = new Faker(new Locale("en"));
        
    private DataHelper() {
    }
      

    public static  AuthInfo getAuthInfoWithRegisteredUser() {
        return new AuthInfo("vasya", "qwerty123");
    }


    public static AuthInfo generateRandomUser() {
        var randomLogin = faker.name().username();
        var randomPassword = faker.internet().password();
        return new AuthInfo(randomLogin, randomPassword);
    }


    public static VerificationCode generateRandomCode() {
        return new VerificationCode(faker.numerify("######"));
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }


}
