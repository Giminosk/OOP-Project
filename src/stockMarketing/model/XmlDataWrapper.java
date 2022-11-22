package stockMarketing.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Giminosk
 */

@XmlRootElement(name = "Economy")
public class XmlDataWrapper {

    private List<Currency> currencies;
    private List<StockMarket> stockExchanges;
    private List<Company> companies;
    private List<Commodity> commodities;
    private List<Investor> investors;

    @XmlElement(name = "currency")
    public List<Currency> getCurrencies() {
        return currencies;
    }
    
    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    @XmlElement(name = "stockExchange")
    public List<StockMarket> getStockExchanges() {
        return stockExchanges;
    }

    public void setStockExchanges(List<StockMarket> stockMarkets) {
        this.stockExchanges = stockMarkets;
    }
    
    @XmlElement(name = "company")
    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
    
    @XmlElement(name = "commodity")
    public List<Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<Commodity> commodities) {
        this.commodities = commodities;
    }
    
    
    @XmlElement(name = "investor")
    public List<Investor> getInvestors() {
        return investors;
    }

    public void setInvestors(List<Investor> investors) {
        this.investors = investors;
    }

}
