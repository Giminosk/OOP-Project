package stockMarketing.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Asset {
    private DoubleProperty minPrice;
    private DoubleProperty maxPrice;
    private DoubleProperty currentPrice;

    public Asset(Double minPrice, Double maxPrice, Double currentPrice){
        this.minPrice = new SimpleDoubleProperty(minPrice);
        this.maxPrice = new SimpleDoubleProperty(maxPrice);
        this.currentPrice = new SimpleDoubleProperty(currentPrice);
    }

    public Asset(){this(0.0, 0.0, 0.0);}

    public final double getMinPrice() {
        return minPrice.get();
    }

    public final void setMinPrice(double value) {
        minPrice.set(value);
    }

    public DoubleProperty minPriceProperty() {
        return minPrice;
    }

    public final double getMaxPrice() { return maxPrice.get(); }

    public final void setMaxPrice(double value) { maxPrice.set(value); }

    public DoubleProperty maxPriceProperty() { return maxPrice;}

    public final double getCurrentPrice() { return  currentPrice.get(); }

    public final void setCurrentPrice(double value) {currentPrice.set(value); }

    public DoubleProperty currentPriceProperty() { return currentPrice; }
}
