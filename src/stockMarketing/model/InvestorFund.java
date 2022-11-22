package stockMarketing.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InvestorFund extends PrivateInvestor {

    private StringProperty name;

    public InvestorFund(String name, String firstName, String lastName) {
        super(firstName, lastName);
        this.name = new SimpleStringProperty(name);
    }

    public final String getName(){return name.get();}
    public final void setName(String value){name.set(value);}
    public StringProperty nameProperty(){return name;}
}
