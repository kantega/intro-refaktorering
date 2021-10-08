package no.kantega;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

public class Pub {

    public static final String ONE_BEER = "hansa";
    public static final String ONE_CIDER = "grans";
    public static final String A_PROPER_CIDER = "strongbow";
    public static final String GT = "gt";
    public static final String BACARDI_SPECIAL = "bacardi_special";

    Map<String, BiFunction<Boolean, Integer, Integer>> drinks = Map.of(
            ONE_BEER, (student, amount) -> computeSimpleBeverage(student, amount, 74),
            ONE_CIDER, (student, amount) -> computeSimpleBeverage(student, amount, 103),
            A_PROPER_CIDER, (student, amount) -> computeSimpleBeverage(student, amount, 110),
            GT, (student, amount) ->
                    computeCocktail(amount, DrinkIngredient.GIN.getPrice(), DrinkIngredient.GREEN_STUFF.getPrice(), DrinkIngredient.TONIC_WATER.getPrice()),
            BACARDI_SPECIAL, (student, amount) ->
                    computeCocktail(amount, DrinkIngredient.GIN.getPrice(2), DrinkIngredient.RUM.getPrice(), DrinkIngredient.GRENADINE.getPrice(), DrinkIngredient.LIME_JUICE.getPrice())
    );

    private int computeSimpleBeverage(boolean student, int amount, int cost) {
        if (student) {
            return (cost - cost/10) * amount;
        }
        return cost * amount;
    }

    private int computeCocktail(int amount, Integer... drinkIngredientsPrice) {
        if (amount > 2) {
            throw new RuntimeException("Too many drinks, max 2.");
        }
        return Arrays.stream(drinkIngredientsPrice).mapToInt(Integer::intValue).sum() * amount;
    }

    public int computeCost(String strDrink, boolean student, int amount) {
        return Optional.ofNullable(drinks.get(strDrink.toLowerCase()))
                .orElseThrow(() -> new RuntimeException("No such drink exists"))
                .apply(student, amount);
    }
}
