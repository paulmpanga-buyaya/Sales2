package sales.management.kiwamirembe.co.ug;

public class SaleTransactionItem {

    public int saleTransactionItemID;
    public String saleTransactionIdentifier;
    public String saleTransactionItemName;
    public String saleTransactionItemSKU;
    public float saleTransactionItemPrice;
    public int saleTransactionItemQuantity;
    public float saleTransactionItemTotal;
    public int saleTransactionItemSyncStatus;

    public SaleTransactionItem() {
    }

    public SaleTransactionItem(int saleTransactionItemID, String saleTransactionIdentifier, String saleTransactionItemName, String saleTransactionItemSKU, float saleTransactionItemPrice, int saleTransactionItemQuantity, float saleTransactionItemTotal, int saleTransactionItemSyncStatus) {
        this.saleTransactionItemID = saleTransactionItemID;
        this.saleTransactionIdentifier = saleTransactionIdentifier;
        this.saleTransactionItemName = saleTransactionItemName;
        this.saleTransactionItemSKU = saleTransactionItemSKU;
        this.saleTransactionItemPrice = saleTransactionItemPrice;
        this.saleTransactionItemQuantity = saleTransactionItemQuantity;
        this.saleTransactionItemTotal = saleTransactionItemTotal;
        this.saleTransactionItemSyncStatus = saleTransactionItemSyncStatus;
    }

    public SaleTransactionItem(String saleTransactionIdentifier, String saleTransactionItemName, String saleTransactionItemSKU, float saleTransactionItemPrice, int saleTransactionItemQuantity, float saleTransactionItemTotal, int saleTransactionItemSyncStatus) {
        this.saleTransactionIdentifier = saleTransactionIdentifier;
        this.saleTransactionItemName = saleTransactionItemName;
        this.saleTransactionItemSKU = saleTransactionItemSKU;
        this.saleTransactionItemPrice = saleTransactionItemPrice;
        this.saleTransactionItemQuantity = saleTransactionItemQuantity;
        this.saleTransactionItemTotal = saleTransactionItemTotal;
        this.saleTransactionItemSyncStatus = saleTransactionItemSyncStatus;
    }

    public int getSaleTransactionItemID() {
        return saleTransactionItemID;
    }

    public void setSaleTransactionItemID(int saleTransactionItemID) {
        this.saleTransactionItemID = saleTransactionItemID;
    }

    public String getSaleTransactionIdentifier() {
        return saleTransactionIdentifier;
    }

    public void setSaleTransactionIdentifier(String saleTransactionIdentifier) {
        this.saleTransactionIdentifier = saleTransactionIdentifier;
    }

    public String getSaleTransactionItemName() {
        return saleTransactionItemName;
    }

    public void setSaleTransactionItemName(String saleTransactionItemName) {
        this.saleTransactionItemName = saleTransactionItemName;
    }

    public String getSaleTransactionItemSKU() {
        return saleTransactionItemSKU;
    }

    public void setSaleTransactionItemSKU(String saleTransactionItemSKU) {
        this.saleTransactionItemSKU = saleTransactionItemSKU;
    }

    public float getSaleTransactionItemPrice() {
        return saleTransactionItemPrice;
    }

    public void setSaleTransactionItemPrice(float saleTransactionItemPrice) {
        this.saleTransactionItemPrice = saleTransactionItemPrice;
    }

    public int getSaleTransactionItemQuantity() {
        return saleTransactionItemQuantity;
    }

    public void setSaleTransactionItemQuantity(int saleTransactionItemQuantity) {
        this.saleTransactionItemQuantity = saleTransactionItemQuantity;
    }

    public float getSaleTransactionItemTotal() {
        return saleTransactionItemTotal;
    }

    public void setSaleTransactionItemTotal(float saleTransactionItemTotal) {
        this.saleTransactionItemTotal = saleTransactionItemTotal;
    }

    public int getSaleTransactionItemSyncStatus() {
        return saleTransactionItemSyncStatus;
    }

    public void setSaleTransactionItemSyncStatus(int saleTransactionItemSyncStatus) {
        this.saleTransactionItemSyncStatus = saleTransactionItemSyncStatus;
    }
}
