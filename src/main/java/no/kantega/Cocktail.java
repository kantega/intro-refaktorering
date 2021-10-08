package no.kantega;

import java.util.List;

public class Cocktail implements Beverage {
    private int price;

    public Cocktail(Ingredient... ingredients) {
        for (Ingredient ingredient : ingredients) {
            price += ingredient.price;
        }
    }

    public Cocktail(IngredientAmount... ingredientAmounts) {
        for (IngredientAmount amount : ingredientAmounts) {
            price += amount.getIngredient().price * amount.getAmount();
        }
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public boolean isAmountLimited() {
        return true;
    }

    @Override
    public boolean isEligibleForStudentDiscount() {
        return false;
    }
}
