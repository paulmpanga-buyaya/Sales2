package sales.management.kiwamirembe.co.ug;

public class SaleTransaction {

    public int saleTransactionID;
    public String customerCode;
    public String saleTransactionIdentifier;
    public float saleTransactionTotal;
    public long saleTransactionDate;
    public int paymentStatus;
    public int saleTransactionSyncStatus;

    public SaleTransaction(){
    }

    public SaleTransaction(int saleTransactionID, String customerCode, String saleTransactionIdentifier, float saleTransactionTotal, long saleTransactionDate, int paymentStatus,int saleTransactionSyncStatus) {
        this.saleTransactionID = saleTransactionID;
        this.customerCode = customerCode;
        this.saleTransactionIdentifier = saleTransactionIdentifier;
        this.saleTransactionTotal = saleTransactionTotal;
        this.saleTransactionDate = saleTransactionDate;
        this.paymentStatus = paymentStatus;
        this.saleTransactionSyncStatus = saleTransactionSyncStatus;
    }

    public SaleTransaction(String customerCode, String saleTransactionIdentifier, float saleTransactionTotal, long saleTransactionDate, int paymentStatus, int saleTransactionSyncStatus) {
        this.customerCode = customerCode;
        this.saleTransactionIdentifier = saleTransactionIdentifier;
        this.saleTransactionTotal = saleTransactionTotal;
        this.saleTransactionDate = saleTransactionDate;
        this.paymentStatus = paymentStatus;
        this.saleTransactionSyncStatus = saleTransactionSyncStatus;
    }

    public int getSaleTransactionID() {
        return saleTransactionID;
    }

    public void setSaleTransactionID(int saleTransactionID) {
        this.saleTransactionID = saleTransactionID;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getSaleTransactionIdentifier() {
        return saleTransactionIdentifier;
    }

    public void setSaleTransactionIdentifier(String saleTransactionIdentifier) {
        this.saleTransactionIdentifier = saleTransactionIdentifier;
    }

    public float getSaleTransactionTotal() {
        return saleTransactionTotal;
    }

    public void setSaleTransactionTotal(float saleTransactionTotal) {
        this.saleTransactionTotal = saleTransactionTotal;
    }

    public long getSaleTransactionDate() {
        return saleTransactionDate;
    }

    public void setSaleTransactionDate(long saleTransactionDate) {
        this.saleTransactionDate = saleTransactionDate;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getSaleTransactionSyncStatus() {
        return saleTransactionSyncStatus;
    }

    public void setSaleTransactionSyncStatus(int saleTransactionSyncStatus) {
        this.saleTransactionSyncStatus = saleTransactionSyncStatus;
    }

}
