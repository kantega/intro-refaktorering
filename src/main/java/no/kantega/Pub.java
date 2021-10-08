package no.kantega;

import java.util.HashMap;
import java.util.Map;

public class Pub {
    public static final String ONE_BEER = "hansa";
    public static final String ONE_CIDER = "grans";
    public static final String A_PROPER_CIDER = "strongbow";
    public static final String GT = "gt";
    public static final String BACARDI_SPECIAL = "bacardi_special";

    public static final int STUDENT_DISCOUNT_IN_PERCENT = 10;
    public static final int MAX_COCKTAILS_FOR_SINGLE_ORDER = 2;

    Map<String, Beverage> menu;

    public Pub() {
        this.menu = new HashMap<String, Beverage>();
        menu.put(ONE_BEER, new SimpleBeverage(74));
        menu.put(ONE_CIDER, new SimpleBeverage(103));
        menu.put(A_PROPER_CIDER, new SimpleBeverage(110));
        menu.put(GT, new Cocktail(
                Ingredient.UNIT_OF_GIN,
                Ingredient.UNIT_OF_TONIC_WATER,
                Ingredient.UNIT_OF_GREEN_STUFF));
        menu.put(BACARDI_SPECIAL, new Cocktail(
                new IngredientAmount(Ingredient.UNIT_OF_GIN, 0.5),
                new IngredientAmount(Ingredient.UNIT_OF_RUM),
                new IngredientAmount(Ingredient.UNIT_OF_GRENADINE),
                new IngredientAmount(Ingredient.UNIT_OF_LIME_JUICE)));
    }

    public int computeCost(String drink, boolean student, int amount) {
        if (!menu.containsKey(drink)) {
            throw new RuntimeException("No such drink exists");
        }

        Beverage beverage = menu.get(drink);
        verifyCocktailOrder(beverage, amount);

        int price = adjustPriceForStudentDiscount(beverage, student);
        return price*amount;
    }

    private int adjustPriceForStudentDiscount(Beverage beverage, boolean student) {
        if (student && beverage.isEligibleForStudentDiscount()) {
            return beverage.getPrice() - beverage.getPrice() / STUDENT_DISCOUNT_IN_PERCENT;
        }
        return beverage.getPrice();
    }

    private void verifyCocktailOrder(Beverage beverage, int amount) {
        if (amount > MAX_COCKTAILS_FOR_SINGLE_ORDER && beverage.isAmountLimited()) {
            throw new RuntimeException("Too many drinks, max " + MAX_COCKTAILS_FOR_SINGLE_ORDER + ".");
        }
    }
}
