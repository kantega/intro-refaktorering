package no.kantega;

public class IngredientAmount {
    private Ingredient ingredient;
    private double amount;

    public IngredientAmount(Ingredient ingredient, double amount) {
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public IngredientAmount(Ingredient ingredient) {
        this(ingredient, 1);
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public double getAmount() {
        return amount;
    }
}