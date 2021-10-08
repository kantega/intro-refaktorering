package no.kantega;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

enum Drink {
    ONE_BEER("hansa", 74),
    ONE_CIDER("grans", 103),
    A_PROPER_CIDER("strongbow", 110),
    GT("gt",
            Stream.of(DrinkIngredient.GIN.getPrice(), DrinkIngredient.GREEN_STUFF.getPrice(), DrinkIngredient.TONIC_WATER.getPrice())
                    .mapToInt(Integer::intValue)
                    .sum(),
            true
    ),
    BACARDI_SPECIAL("bacardi_special",
            Stream.of(DrinkIngredient.GIN.getPrice(2), DrinkIngredient.RUM.getPrice(), DrinkIngredient.GRENADINE.getPrice(), DrinkIngredient.LIME_JUICE.getPrice())
                    .mapToInt(Integer::intValue)
                    .sum(),
            true
    );

    static final int STUDENT_DISCOUNT = 10;
    static final int MAX_COCKTAILS_IN_ORDER = 2;

    private static final Map<String, Drink> lookupByName =
            new HashMap<>();

    static {
        for (Drink drink : Drink.values()) {
            lookupByName.put(drink.name.toLowerCase(), drink);
        }
    }

    private final String name;
    private final int price;
    private final boolean isCocktail;

    Drink(String name, int price) {
        this.name = name;
        this.price = price;
        this.isCocktail = false;
    }

    Drink(String name, int price, boolean isCocktail) {
        this.name = name;
        this.price = price;
        this.isCocktail = isCocktail;
    }

    static Drink getByName(String name) {
        return Optional.ofNullable(lookupByName.get(name.toLowerCase()))
                .orElseThrow(() -> new RuntimeException("No such drink exists"));
    }

    boolean isAcceptableAmount(int amount) {
        if (isCocktail && amount > MAX_COCKTAILS_IN_ORDER) {
            return false;
        }
        return true;
    }

    int computeTotalPrice(boolean isStudent, int amount) {
        if (!isCocktail && isStudent) {
            return (price - price / STUDENT_DISCOUNT) * amount;
        }
        return price * amount;
    }
}
