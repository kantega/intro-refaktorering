package no.kantega;

enum DrinkIngredient {
    RUM(65),
    GRENADINE(10),
    LIME_JUICE(10),
    GREEN_STUFF(10),
    TONIC_WATER(20),
    GIN(85);

    private final int price;

    DrinkIngredient(int price) {
        this.price = price;
    }

    int getPrice(int fraction) {
        return price / fraction;
    }

    int getPrice() {
        return price;
    }
}
