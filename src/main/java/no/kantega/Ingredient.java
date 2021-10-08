package no.kantega;

public enum Ingredient {
    UNIT_OF_GIN(85),
    UNIT_OF_TONIC_WATER(20),
    UNIT_OF_GREEN_STUFF(10),
    UNIT_OF_LIME_JUICE(10),
    UNIT_OF_GRENADINE(10),
    UNIT_OF_RUM(65);

    int price;

    Ingredient(int price) {
        this.price = price;
    }
}
