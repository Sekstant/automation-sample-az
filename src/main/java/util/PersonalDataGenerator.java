package util;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.util.Locale;

public class PersonalDataGenerator {
    public static String generateName() {
        Faker faker = new Faker();
        return faker.name().fullName();
    }

    public static String generateEmail() {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        return fakeValuesService.bothify("????##@gmail.com");
    }

}
