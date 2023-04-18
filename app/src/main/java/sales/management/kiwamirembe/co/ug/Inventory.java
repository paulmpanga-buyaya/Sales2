package sales.management.kiwamirembe.co.ug;

public class Inventory {

    public int inventoryID;
    public String productSKU;
    public int stock;
    public int isSynced;

    public Inventory() {
    }

    public Inventory(int inventoryID, String productSKU, int stock, int isSynced) {
        this.inventoryID = inventoryID;
        this.productSKU = productSKU;
        this.stock = stock;
        this.isSynced = isSynced;
    }

    public Inventory(String productSKU, int stock, int isSynced) {
        this.productSKU = productSKU;
        this.stock = stock;
        this.isSynced = isSynced;
    }

    public int getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(int inventoryID) {
        this.inventoryID = inventoryID;
    }

    public String getProductSKU() {
        return productSKU;
    }

    public void setProductSKU(String productSKU) {
        this.productSKU = productSKU;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIsSynced() {
        return isSynced;
    }

    public void setIsSynced(int isSynced) {
        this.isSynced = isSynced;
    }
}
