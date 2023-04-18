package sales.management.kiwamirembe.co.ug;

public class Payment {

    public int paymentID;
    public String paymentCode;
    public String saleTransactionIdentifier;//also could be the order number
    public String paymentType;
    public float paymentAmount;
    public long paymentDate;

    public Payment() {
    }

    public Payment(int paymentID, String paymentCode, String saleTransactionIdentifier, String paymentType, float paymentAmount, long paymentDate) {
        this.paymentID = paymentID;
        this.paymentCode = paymentCode;
        this.saleTransactionIdentifier = saleTransactionIdentifier;
        this.paymentType = paymentType;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }

    public Payment(String paymentCode, String saleTransactionIdentifier, String paymentType, float paymentAmount, long paymentDate) {
        this.paymentCode = paymentCode;
        this.saleTransactionIdentifier = saleTransactionIdentifier;
        this.paymentType = paymentType;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getSaleTransactionIdentifier() {
        return saleTransactionIdentifier;
    }

    public void setSaleTransactionIdentifier(String saleTransactionIdentifier) {
        this.saleTransactionIdentifier = saleTransactionIdentifier;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public long getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(long paymentDate) {
        this.paymentDate = paymentDate;
    }
}
