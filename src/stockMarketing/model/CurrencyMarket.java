package stockMarketing.model;

import java.util.ArrayList;
import java.util.List;

public class CurrencyMarket extends Market{

    List<Currency> currencies;

    public CurrencyMarket(String name, Double fee) {
        super(name, fee);
        this.currencies = new ArrayList<>();
    }

    public CurrencyMarket(){this(null, 0.0); }

    public List<Currency> getCurrencies(){return currencies;}
}
