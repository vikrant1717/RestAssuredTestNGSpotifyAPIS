package utilities;

import com.github.javafaker.Faker;

public class Fakerutils {

    public static String generateName() {
        Faker faker = new Faker();
        return "Playlist" + faker.animal();
    }

    public static String generateDescription() {
        Faker faker = new Faker();
        return "Description" + faker.app();
    }
}
