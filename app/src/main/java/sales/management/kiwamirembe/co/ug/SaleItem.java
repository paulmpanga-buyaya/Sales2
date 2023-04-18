package sales.management.kiwamirembe.co.ug;

public class SaleItem {

    public int saleItemID;
    public String saleItemSKU;
    public String saleItemName;
    public float saleItemPrice;
    public int saleItemQuantity;
    public float saleItemTotal;
    public float saleItemDiscount;
    public int saleItemSyncStatus;

    public SaleItem() {
    }

    public SaleItem(int saleItemID, String saleItemSKU, String saleItemName, float saleItemPrice, int saleItemQuantity, float saleItemTotal, float saleItemDiscount, int saleItemSyncStatus) {
        this.saleItemID = saleItemID;
        this.saleItemSKU = saleItemSKU;
        this.saleItemName = saleItemName;
        this.saleItemPrice = saleItemPrice;
        this.saleItemQuantity = saleItemQuantity;
        this.saleItemTotal = saleItemTotal;
        this.saleItemDiscount = saleItemDiscount;
        this.saleItemSyncStatus = saleItemSyncStatus;
    }

    public SaleItem(String saleItemSKU, String saleItemName, float saleItemPrice, int saleItemQuantity, float saleItemTotal, float saleItemDiscount, int saleItemSyncStatus) {
        this.saleItemSKU = saleItemSKU;
        this.saleItemName = saleItemName;
        this.saleItemPrice = saleItemPrice;
        this.saleItemQuantity = saleItemQuantity;
        this.saleItemTotal = saleItemTotal;
        this.saleItemDiscount = saleItemDiscount;
        this.saleItemSyncStatus = saleItemSyncStatus;
    }

    public int getSaleItemID() {
        return saleItemID;
    }

    public void setSaleItemID(int saleItemID) {
        this.saleItemID = saleItemID;
    }

    public String getSaleItemSKU() {
        return saleItemSKU;
    }

    public void setSaleItemSKU(String saleItemSKU) {
        this.saleItemSKU = saleItemSKU;
    }

    public String getSaleItemName() {
        return saleItemName;
    }

    public void setSaleItemName(String saleItemName) {
        this.saleItemName = saleItemName;
    }

    public float getSaleItemPrice() {
        return saleItemPrice;
    }

    public void setSaleItemPrice(float saleItemPrice) {
        this.saleItemPrice = saleItemPrice;
    }

    public int getSaleItemQuantity() {
        return saleItemQuantity;
    }

    public void setSaleItemQuantity(int saleItemQuantity) {
        this.saleItemQuantity = saleItemQuantity;
    }

    public float getSaleItemTotal() {
        return saleItemTotal;
    }

    public void setSaleItemTotal(float saleItemTotal) {
        this.saleItemTotal = saleItemTotal;
    }

    public float getSaleItemDiscount() {
        return saleItemDiscount;
    }

    public void setSaleItemDiscount(float saleItemDiscount) {
        this.saleItemDiscount = saleItemDiscount;
    }

    public int getSaleItemSyncStatus() {
        return saleItemSyncStatus;
    }

    public void setSaleItemSyncStatus(int saleItemSyncStatus) {
        this.saleItemSyncStatus = saleItemSyncStatus;
    }
}
