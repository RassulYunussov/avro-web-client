package org.example;

import java.util.List;
import java.util.Random;

public class UserFactory {

    private static Random random = new Random();
    private static String[] names = new String[]{"Alyssa", "Rainbow", "John", "Salomon"};
    private static String[] cars = new String[]{"Mercedes", "BMW", "Audi", "Porshe"};
    private static String[] colors = new String[]{"Red", "Green", "Blue", "Black"};
    private static String[] companies = new String[]{"Co & A", "Bo & B", "Do & C", "Eo & D"};

    private static String[] positions = new String[]{"CEO", "CIO", "CTO", "CFO"};
    private static String[] responsibilities = new String[]{"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};

    private UserFactory() {

    }

    public static User getAvroUser() {
        User user = new User();
        user.setName(names[random.nextInt(names.length)]);
        user.setFavoriteNumber(random.nextInt());
        user.setFavoriteColor(colors[random.nextInt(colors.length)]);
        user.setFavoriteCar(new FavoriteCar(cars[random.nextInt(cars.length)], colors[random.nextInt(colors.length)]));
        user.setPortfolioList(List.of(
                new Portfolio(companies[random.nextInt(companies.length)], positions[random.nextInt(positions.length)], random.nextInt(), random.nextDouble(), random.nextFloat(), List.of(responsibilities[random.nextInt(responsibilities.length)], responsibilities[random.nextInt(responsibilities.length)], responsibilities[random.nextInt(responsibilities.length)])),
                new Portfolio(companies[random.nextInt(companies.length)], positions[random.nextInt(positions.length)], random.nextInt(), random.nextDouble(), random.nextFloat(), List.of(responsibilities[random.nextInt(responsibilities.length)], responsibilities[random.nextInt(responsibilities.length)], responsibilities[random.nextInt(responsibilities.length)])),
                new Portfolio(companies[random.nextInt(companies.length)], positions[random.nextInt(positions.length)], random.nextInt(), random.nextDouble(), random.nextFloat(), List.of(responsibilities[random.nextInt(responsibilities.length)], responsibilities[random.nextInt(responsibilities.length)], responsibilities[random.nextInt(responsibilities.length)])),
                new Portfolio(companies[random.nextInt(companies.length)], positions[random.nextInt(positions.length)], random.nextInt(), random.nextDouble(), random.nextFloat(), List.of(responsibilities[random.nextInt(responsibilities.length)], responsibilities[random.nextInt(responsibilities.length)], responsibilities[random.nextInt(responsibilities.length)]))
                ));
        return user;
    }
}
