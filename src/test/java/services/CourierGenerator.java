package services;

import org.apache.commons.lang3.RandomStringUtils;
import pojo.Courier;

public class CourierGenerator {
    static int targetStringLength = 6;

    public static String randomLogin() {
        return RandomStringUtils.randomAlphabetic(targetStringLength);
    }
    public static String randomPassword() {
        return RandomStringUtils.randomAlphanumeric(targetStringLength);
    }
    public static String randomFirstName() {
        return RandomStringUtils.randomAlphabetic(targetStringLength);
    }
    public static Courier randomCourier() {
        return new Courier(randomLogin(), randomPassword(), randomFirstName());
    }
}
