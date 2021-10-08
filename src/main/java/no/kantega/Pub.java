package no.kantega;

import java.util.Optional;

public class Pub {

    public static final String ONE_BEER = "hansa";
    public static final String ONE_CIDER = "grans";
    public static final String A_PROPER_CIDER = "strongbow";
    public static final String GT = "gt";
    public static final String BACARDI_SPECIAL = "bacardi_special";

    public int computeCost(String strDrink, boolean student, int amount) {

        return Optional.ofNullable(Drink.getByName(strDrink))
                .filter(drink -> drink.isAcceptableAmount(amount))
                .map(drink -> drink.computeTotalPrice(student, amount))
                .orElseThrow(() -> new RuntimeException(String.format("Too many drinks, max %d.", Drink.MAX_COCKTAILS_IN_ORDER)));
    }

}
