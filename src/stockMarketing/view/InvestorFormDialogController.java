package stockMarketing.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import stockMarketing.MainApp;
import stockMarketing.model.Investor;
import stockMarketing.model.StockMarket;

/**
 * FXML Controller class
 *
 * @author Giminosk
 */
public class InvestorFormDialogController implements Initializable {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField idField;
    @FXML
    private TextField budgetField;
    @FXML
    private ComboBox<StockMarket> stockComboBox;
                 
    
    private Stage dialogStage;
    private Investor investor;
    private boolean okClicked = false;
    
    private MainApp app;
    
    public void setApp(MainApp app){
        this.app = app;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        
        // Set the dialog icon.
       // this.dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
    }
    
    public void setInvestorFields(Investor investor) {
        this.investor = investor;
            
        firstNameField.setText(investor.getFirstName());
        lastNameField.setText(investor.getLastName());
        budgetField.setText(String.valueOf(investor.getBudget()));
        idField.setText(investor.getId());
    
        stockComboBox.setItems(app.getStockExchangeData());
        stockComboBox.getSelectionModel().selectFirst();
     }
        
    public boolean isOkClicked() {
        return okClicked;
    }
    
    @FXML
    private void handleOk() {
        if(isInputValid()){
            investor.setFirstName(firstNameField.getText());
            investor.setLastName(lastNameField.getText());
            investor.setBudget(Double.parseDouble(budgetField.getText()));
            investor.setId(idField.getText());
            investor.setStockExchangeBelonging(stockComboBox.getSelectionModel().getSelectedItem());
            stockComboBox.getSelectionModel().getSelectedItem().getInvestors().add(investor);            
             
            okClicked = true;
            dialogStage.close();  
        }
    }


    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "First name can't be empty\n"; 
        }
        
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0  ){
            errorMessage += "Last name can't be empty\n";
        }
        
        if (budgetField.getText() == null || Double.parseDouble(budgetField.getText()) == 0.0  ){
            errorMessage += "Budget can't be empty\n";
        }
        
        if (idField.getText() == null || idField.getText().length() == 0){
            errorMessage += "Id can't be empty\n";
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }    
}
