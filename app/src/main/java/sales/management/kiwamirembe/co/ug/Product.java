package sales.management.kiwamirembe.co.ug;

public class Product {

    public int product_id;
    public String product_sku;
    public String product_name;
    public int product_unit_id;
    public int product_category_id;
    public float product_unit_price;
    public int product_sync_status;

    public Product() {
    }

    public Product(int product_id, String product_sku, String product_name, int product_unit_id, int product_category_id, float product_unit_price, int product_sync_status) {
        this.product_id = product_id;
        this.product_sku = product_sku;
        this.product_name = product_name;
        this.product_unit_id = product_unit_id;
        this.product_category_id = product_category_id;
        this.product_unit_price = product_unit_price;
        this.product_sync_status = product_sync_status;
    }

    public Product(String product_sku, String product_name, int product_unit_id, int product_category_id, float product_unit_price, int product_sync_status) {
        this.product_sku = product_sku;
        this.product_name = product_name;
        this.product_unit_id = product_unit_id;
        this.product_category_id = product_category_id;
        this.product_unit_price = product_unit_price;
        this.product_sync_status = product_sync_status;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_sku() {
        return product_sku;
    }

    public void setProduct_sku(String product_sku) {
        this.product_sku = product_sku;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_unit_id() {
        return product_unit_id;
    }

    public void setProduct_unit_id(int product_unit_id) {
        this.product_unit_id = product_unit_id;
    }

    public int getProduct_category_id() {
        return product_category_id;
    }

    public void setProduct_category_id(int product_category_id) {
        this.product_category_id = product_category_id;
    }

    public float getProduct_unit_price() {
        return product_unit_price;
    }

    public void setProduct_unit_price(float product_unit_price) {
        this.product_unit_price = product_unit_price;
    }

    public int getProduct_sync_status() {
        return product_sync_status;
    }

    public void setProduct_sync_status(int product_sync_status) {
        this.product_sync_status = product_sync_status;
    }
}
