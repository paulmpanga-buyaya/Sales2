package sales.management.kiwamirembe.co.ug;

public class CurrentTransactionDetails {

    //this table is a holder for current transaction information
    //Android preferences can be used instead of this table
    public int currentTransactionID;
    public String currentTransactionIdentifier;//receipt number, invoice number etc
    public String currentTransactionCustomerCode;

    public CurrentTransactionDetails() {
    }

    public CurrentTransactionDetails(int currentTransactionID, String currentTransactionIdentifier, String currentTransactionCustomerCode) {
        this.currentTransactionID = currentTransactionID;
        this.currentTransactionIdentifier = currentTransactionIdentifier;
        this.currentTransactionCustomerCode = currentTransactionCustomerCode;
    }

    public CurrentTransactionDetails(String currentTransactionIdentifier, String currentTransactionCustomerCode) {
        this.currentTransactionIdentifier = currentTransactionIdentifier;
        this.currentTransactionCustomerCode = currentTransactionCustomerCode;
    }

    public int getCurrentTransactionID() {
        return currentTransactionID;
    }

    public void setCurrentTransactionID(int currentTransactionID) {
        this.currentTransactionID = currentTransactionID;
    }

    public String getCurrentTransactionIdentifier() {
        return currentTransactionIdentifier;
    }

    public void setCurrentTransactionIdentifier(String currentTransactionIdentifier) {
        this.currentTransactionIdentifier = currentTransactionIdentifier;
    }

    public String getCurrentTransactionCustomerCode() {
        return currentTransactionCustomerCode;
    }

    public void setCurrentTransactionCustomerCode(String currentTransactionCustomerCode) {
        this.currentTransactionCustomerCode = currentTransactionCustomerCode;
    }

}
