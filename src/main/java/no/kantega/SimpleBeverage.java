package no.kantega;

public class SimpleBeverage implements Beverage {
    private int price;

    public SimpleBeverage(int price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public boolean isAmountLimited() {
        return false;
    }

    @Override
    public boolean isEligibleForStudentDiscount() {
        return true;
    }
}
