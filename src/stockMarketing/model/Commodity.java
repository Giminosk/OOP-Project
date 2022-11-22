package stockMarketing.model;

import static java.lang.Thread.sleep;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 *
 * @author Giminosk
 */
public class Commodity extends Asset implements Runnable{
    
    private StringProperty name;
    private StringProperty unit;
    private IntegerProperty commoditiesCount;
    private IntegerProperty commoditiesForSale;
    private DoubleProperty turnoverValue;
    private IntegerProperty volume;
    
    private DoubleProperty change;
    
    private CommodityMarket memberOfMarket;
       
    private List<Transaction> transactionList;
    private Set<Investor> investorSet;
    
    public Commodity(String name, String unit, Double min, Double max, Double current, Double turnoverValue, Integer volume, Integer commoditiesCount, Integer commoditiesForSale,
                     Double change, CommodityMarket memberOfMarket){
        super(min, max, current);

        this.name = new SimpleStringProperty(name);
        this.unit = new SimpleStringProperty(unit);

        this.turnoverValue = new SimpleDoubleProperty(turnoverValue);
        this.volume = new SimpleIntegerProperty(volume);
        this.commoditiesCount = new SimpleIntegerProperty(commoditiesCount);
        this.commoditiesForSale = new SimpleIntegerProperty(commoditiesForSale);
        this.change = new SimpleDoubleProperty(change);
        this.memberOfMarket = memberOfMarket;
        
        this.transactionList = new LinkedList<>();
        this.investorSet = new HashSet<>();
    }
    
    public Commodity() {
        this(null, null, 0.0, 0.0, 0.0, 0.0, 0, 0, 0, 0.0, null);
    }
    
    private volatile boolean running = true;
    public void terminate() {
        running = false;
    }
    
    public void run(){
        while(running){
            try {
                sellCommodities();

            } catch (InterruptedException ex) {
                Logger.getLogger(Investor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(this.getName() + " thread terminated");
    }
    
    public void sellCommodities() throws InterruptedException {
        int min = 15;
        int max = 20;
        int randomInt = ThreadLocalRandom.current().nextInt(min * 1000, max * 1000);
        
        sleep(randomInt);
        synchronized(this.getCommodityMarketBelonging()){
            int random = ThreadLocalRandom.current().nextInt(5000, 10000);
            
            this.setCommoditiesCount(this.getCommoditiesCount() + random);
            this.setCommoditiesForSale(this.getCommoditiesForSale() + random);
        }
    }
    
    public void buyAllCommodities() {
        synchronized(this.getCommodityMarketBelonging()) {
            this.getInvestorSet().forEach((investor) -> {
                HashMap<Commodity, Integer> investorCommodities = investor.getCommodities();

                investor.setBudget(investor.getBudget() + investorCommodities.get(this) * this.getCurrentPrice());
                investorCommodities.remove(this);                  

            });
        }
    }
        
        
    
    public void calculateCurrentPrice() {
        double random = ThreadLocalRandom.current().nextDouble(-15, 15);
        random = (double) Math.round(random * 100) / 100;
        
        double newPrice = this.getCurrentPrice() * (1 + random/100);
        newPrice = (double) Math.round(newPrice * 100) / 100;
        
        if (newPrice > this.getMaxPrice()) {
            this.setMaxPrice(newPrice);
        } else if (newPrice < this.getMinPrice()) {
            this.setMinPrice(newPrice);
        }
        
        this.setCurrentPrice(newPrice);
        this.setChange(random);
    }
    
    public String toString() {
        return this.getName();
    }
    
    @XmlElementWrapper(name = "transactions")
    @XmlElement(name = "transaction")
    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
    
    public Set<Investor> getInvestorSet() {
        return investorSet;
    }

    public final String getName() {
        return name.get();
    }

    public final void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }
    
    public final String getUnit() {
        return unit.get();
    }

    public final void setUnit(String value) {
        unit.set(value);
    }

    public StringProperty unitProperty() {
        return unit;
    }

    public final int getCommoditiesCount() {
        return commoditiesCount.get();
    }

    public final void setCommoditiesCount(int value) {
        commoditiesCount.set(value);
    }

    public IntegerProperty commoditiesCountProperty() {
        return commoditiesCount;
    }

    public final int getCommoditiesForSale() {
        return commoditiesForSale.get();
    }

    public final void setCommoditiesForSale(int value) {
        commoditiesForSale.set(value);
    }

    public IntegerProperty commoditiesForSaleProperty() {
        return commoditiesForSale;
    }

    public final double getTurnoverValue() {
        return turnoverValue.get();
    }

    public final void setTurnoverValue(double value) {
        turnoverValue.set(value);
    }

    public DoubleProperty turnoverValueProperty() {
        return turnoverValue;
    }

    public final int getVolume() {
        return volume.get();
    }

    public final void setVolume(int value) {
        volume.set(value);
    }

    public IntegerProperty volumeProperty() {
        return volume;
    }

    public final double getChange() {
        return change.get();
    }

    public final void setChange(double value) {
        change.set(value);
    }

    public DoubleProperty changeProperty() {
        return change;
    }
    
    public final CommodityMarket getCommodityMarketBelonging () {
        return memberOfMarket;
    }
    
    public final void setCommodityMarketBelonging(CommodityMarket commodityMarket) {
        memberOfMarket = commodityMarket;
    }
     
    
    
        
}
