package stockMarketing.model;

import javafx.beans.property.IntegerProperty;

import java.util.List;

/**
 *
 * @author Giminosk
 */
public class Index {
    private IntegerProperty indexValue;
    private List<Company> companyList;

    public List<Company> getCompanyList(){return companyList;}

    public void setCompanyList(List<Company> companyList1){this.companyList = companyList1;}

    
}
