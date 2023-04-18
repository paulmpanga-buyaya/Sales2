package sales.management.kiwamirembe.co.ug;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "commerce_v1.db";
    public static final int DATABASE_VERSION = 1;

    public static final String payments_table = "payments";
    public static final String payment_id = "paymentID";
    public static final String payment_code = "paymentCode";
    public static final String payment_saleTransactionIdentifier = "saleTransactionIdentifier";
    public static final String payment_type = "paymentType";
    public static final String payment_amount = "paymentAmount";
    public static final String payment_date = "paymentDate";

    public static final String products_table = "products";
    public static final String product_id = "productID";
    public static final String product_sku = "productSKU";
    public static final String product_name = "productName";
    public static final String product_unit_id = "productUnitID";
    public static final String product_category_id = "productCategoryID";
    public static final String product_unit_price = "productUnitPrice";
    public static final String product_sync_status = "productSyncStatus";

    public static final String categories_table = "categories";
    public static final String category_id = "categoryID";
    public static final String category_code = "categoryCode";
    public static final String category_name = "categoryName";
    public static final String category_type = "categoryType";
    public static final String category_sync_status = "categorySyncStatus";

    public static final String units_table = "units";
    public static final String unit_id = "unitID";
    public static final String unit_code = "unitCode";
    public static final String unit_name = "unitName";
    public static final String unit_sync_status = "unitSyncStatus";

    public static final String customers_table = "customers";
    public static final String customer_id = "customerID";
    public static final String customer_code = "customerCode";
    public static final String customer_name = "customerName";
    public static final String customer_email = "customerEmail";
    public static final String customer_phone = "customerPhone";
    public static final String customer_sync_status = "customerSyncStatus";

    public static final String inventory_table = "inventories";
    public static final String inventory_id = "inventoryID";
    public static final String inventory_product_sku = "inventoryProductSKU";
    public static final String inventory_stock = "inventoryStock";
    public static final String inventory_is_synced = "isSynced";

    public static final String sale_items_table = "saleItems";
    public static final String sale_item_id = "saleItemID";
    public static final String sale_item_sku = "saleItemSKU";
    public static final String sale_item_name = "saleItemName";
    public static final String sale_item_price = "saleItemPrice";
    public static final String sale_item_quantity = "saleItemQuantity";
    public static final String sale_item_total = "saleItemTotal";
    public static final String sale_item_discount = "saleItemDiscount";
    public static final String sale_item_sync_status = "saleItemSyncStatus";

    public static final String current_transaction_details_table = "currentTransactionDetails";
    public static final String current_transaction_id = "currentTransactionID";
    public static final String current_transaction_identifier = "currentTransactionIdentifier";
    public static final String current_transaction_customer_code = "currentTransactionCustomerCode";

    public static final String sale_transactions_table = "saleTransactions";
    public static final String sale_transactions_id = "saleTransactionID";
    public static final String sale_transactions_customer_code = "customerCode";
    public static final String sale_transactions_identifier = "saleTransactionIdentifier";
    public static final String sale_transactions_total = "saleTransactionTotal";
    public static final String sale_transactions_date = "saleTransactionDate";
    public static final String sale_transactions_payment_status = "paymentStatus";
    public static final String sale_transactions_sync_status = "saleTransactionSyncStatus";

    public static final String sale_transactions_items_table = "salesTransactionItems";
    public static final String sale_transactions_item_id = "saleTransactionItemID";
    public static final String sale_item_transactions_identifier = "saleTransactionIdentifier";
    public static final String sale_transactions_item_name = "saleTransactionItemName";
    public static final String sale_transactions_item_sku = "saleTransactionItemSKU";
    public static final String sale_transactions_item_price = "saleTransactionItemPrice";
    public static final String sale_transactions_item_quantity = "saleTransactionItemQuantity";
    public static final String sale_transactions_item_total = "saleTransactionItemTotal";
    public static final String sale_transactions_item_sync_status = "saleTransactionItemSyncStatus";

    public static final String routes_table = "routes";
    public static final String route_id = "routeID";
    public static final String route_truck_id = "routeTruckID";
    public static final String route_name = "routeName";
    public static final String route_sync_status = "routeSyncStatus";

    public static final String user_table = "user";
    public static final String user_id = "userID";
    public static final String user_identification = "userIdentification";
    public static final String user_name = "userName";
    public static final String user_truck_id = "userTruckID";

    public static final String create_products_table = "create table products (productID INTEGER PRIMARY KEY, productSKU text, productName text, productUnitID int, productCategoryID int, productUnitPrice real, productSyncStatus int)";
    private static final String drop_products_table = "DROP TABLE IF EXISTS products";

    public static final String create_categories_table = "create table categories (categoryID INTEGER PRIMARY KEY, categoryCode text, categoryName text, categoryType text, categorySyncStatus int)";
    private static final String drop_categories_table = "DROP TABLE IF EXISTS categories";

    public static final String create_units_table = "create table units (unitID INTEGER PRIMARY KEY, unitCode text, unitName text, unitSyncStatus int)";
    private static final String drop_units_table = "DROP TABLE IF EXISTS units";

    public static final String create_customers_table = "create table customers (customerID INTEGER PRIMARY KEY, customerCode text, customerName text, customerEmail text, customerPhone text, customerSyncStatus int)";
    private static final String drop_customers_table = "DROP TABLE IF EXISTS customers";

    public static final String create_inventories_table = "create table inventories (inventoryID INTEGER PRIMARY KEY, inventoryProductSKU text, inventoryStock int, isSynced int)";
    private static String drop_inventories_table = "DROP TABLE IF EXISTS inventories";

    public static final String create_sale_items_table = "create table saleItems (saleItemID INTEGER PRIMARY KEY, saleItemSKU text, saleItemName text, saleItemPrice int, saleItemQuantity int, saleItemTotal real, saleItemDiscount real, saleItemSyncStatus int)";
    private static final String drop_sale_items_table = "DROP TABLE IF EXISTS saleItems";

    public static final String create_current_transactions_details_table = "create table currentTransactionDetails (currentTransactionID INTEGER PRIMARY KEY, currentTransactionIdentifier text, currentTransactionCustomerCode text)";
    private static final String drop_current_transactions_details_table = "DROP TABLE IF EXISTS currentTransactionDetails";

    public static final String create_sales_transactions_table = "create table saleTransactions (saleTransactionID INTEGER PRIMARY KEY, customerCode text, saleTransactionIdentifier text, saleTransactionTotal real, saleTransactionDate real, paymentStatus int, saleTransactionSyncStatus int)";
    private static final String drop_sales_transactions_table = "DROP TABLE IF EXISTS saleTransactions";

    public static final String create_sales_transactions_items_table = "create table salesTransactionItems (saleTransactionItemID INTEGER PRIMARY KEY, saleTransactionIdentifier text, saleTransactionItemName text, saleTransactionItemSKU text, saleTransactionItemPrice real, saleTransactionItemQuantity int, saleTransactionItemTotal real, paymentStatus int, saleTransactionItemSyncStatus int)";
    private static final String drop_sales_transactions_items_table = "DROP TABLE IF EXISTS salesTransactionItems";

    public static final String create_routes_table = "create table routes (routeID INTEGER PRIMARY KEY, routeTruckID text, routeName text, routeSyncStatus int)";
    private static final String drop_routes_table = "DROP TABLE IF EXISTS routes";

    public static final String create_user_table = "create table user (userID INTEGER PRIMARY KEY, user_identification text, userName text, userTruckID text)";
    private static final String drop_user_table = "DROP TABLE IF EXISTS user";

    public static final String create_payments_table = "create table payments (paymentID INTEGER PRIMARY KEY, paymentCode text, saleTransactionIdentifier text, paymentType text, paymentAmount real, paymentDate real)";
    private static final String drop_payments_table = "DROP TABLE IF EXISTS payments";

    public Context context;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_products_table);
        db.execSQL(create_categories_table);
        db.execSQL(create_units_table);
        db.execSQL(create_customers_table);
        db.execSQL(create_inventories_table);
        db.execSQL(create_sale_items_table);
        db.execSQL(create_current_transactions_details_table);
        db.execSQL(create_sales_transactions_table);
        db.execSQL(create_sales_transactions_items_table);
        db.execSQL(create_routes_table);
        db.execSQL(create_user_table);
        db.execSQL(create_payments_table);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(drop_products_table);
        db.execSQL(drop_categories_table);
        db.execSQL(drop_units_table);
        db.execSQL(drop_customers_table);
        db.execSQL(drop_inventories_table);
        db.execSQL(drop_sale_items_table);
        db.execSQL(drop_current_transactions_details_table);
        db.execSQL(drop_sales_transactions_table);
        db.execSQL(drop_sales_transactions_items_table);
        db.execSQL(drop_routes_table);
        db.execSQL(drop_user_table);
        db.execSQL(drop_payments_table);
        onCreate(db);
    }

    public boolean addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(product_sku, product.getProduct_sku());
        contentValues.put(product_name, product.getProduct_name());
        contentValues.put(product_unit_id, product.getProduct_unit_id());
        contentValues.put(product_category_id, product.getProduct_category_id());
        contentValues.put(product_unit_price, product.getProduct_unit_price());
        contentValues.put(product_sync_status, product.getProduct_sync_status());
        db.insert(products_table, null, contentValues);
        return true;
    }

    public boolean productExists(String searchItem) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = { product_sku };
        String selection = product_sku + " =?";
        String[] selectionArgs = { searchItem };
        String limit = "1";
        Cursor cursor = db.query(products_table, columns, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public void delete(String table_name) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //deleting rows
        sqLiteDatabase.delete(table_name, null, null);
        sqLiteDatabase.close();
    }

    public int getMaxId(String table_name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT MAX(id) FROM " + table_name, null);
        res.moveToFirst();
        return res.getInt(0);
    }

    public int numberOfRows(String table_name){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, table_name);
        return numRows;
    }

    public List<Product> getProducts(){
        List<Product> productList = new ArrayList<Product>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + DBHelper.products_table, null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            int id = res.getInt(res.getColumnIndexOrThrow(DBHelper.product_id));
            String i_sku = res.getString(res.getColumnIndexOrThrow(DBHelper.product_sku));
            String i_name = res.getString(res.getColumnIndexOrThrow(DBHelper.product_name));
            int i_unit_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.product_unit_id));
            int i_cat_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.product_category_id));
            float i_price = res.getFloat(res.getColumnIndexOrThrow(DBHelper.product_unit_price));
            int i_sync_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.product_sync_status));
            productList.add(new Product(id,i_sku,i_name,i_unit_id,i_cat_id,i_price,i_sync_status));
            res.moveToNext();
        }
        return productList;
    }

    public Product getProductWithSKU(String sku){
        SQLiteDatabase db = getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + DBHelper.products_table +
                " WHERE " + DBHelper.product_sku + "=?", new String[]{String.valueOf(sku)});
        Product item = new Product();
        if(res.getCount() == 1) {
            res.moveToFirst();

            int id = res.getInt(res.getColumnIndexOrThrow(DBHelper.product_id));
            item.setProduct_id(id);
            String i_sku = res.getString(res.getColumnIndexOrThrow(DBHelper.product_sku));
            item.setProduct_sku(i_sku);
            String i_name = res.getString(res.getColumnIndexOrThrow(DBHelper.product_name));
            item.setProduct_name(i_name);
            int i_unit_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.product_unit_id));
            item.setProduct_unit_id(i_unit_id);
            int i_cat_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.product_category_id));
            item.setProduct_category_id(i_cat_id);
            float i_price = res.getFloat(res.getColumnIndexOrThrow(DBHelper.product_unit_price));
            item.setProduct_unit_price(i_price);
            int i_sync_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.product_sync_status));
            item.setProduct_sync_status(i_sync_status);
        }
        return item;
    }

    public boolean addCategory(Category category){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(category_code, category.getCategory_code());
        contentValues.put(category_name, category.getCategory_name());
        contentValues.put(category_type, category.getCategory_type());
        contentValues.put(category_sync_status, category.getCategory_syc_status());
        db.insert(categories_table, null, contentValues);
        return true;
    }

    public boolean categoryNameExists(String searchItem) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = { category_name };
        String selection = category_name + " =?";
        String[] selectionArgs = { searchItem };
        String limit = "1";
        Cursor cursor = db.query(categories_table, columns, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public boolean updateCategory(int id, String code, String name, String type, int sync_status) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues updateItem = new ContentValues();
            updateItem.put(category_code, code);
            updateItem.put(category_name, name);
            updateItem.put(category_type, type);
            updateItem.put(category_sync_status, sync_status);
            db.update(categories_table, updateItem, category_id + " = '" + id + "'", null);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getCategoryID(String name) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor data =  db.rawQuery("SELECT " + DBHelper.category_id + " FROM " + DBHelper.categories_table +
                " WHERE " + DBHelper.category_name + "=?", new String[]{name});
        int id = 0;
        if(data.getCount() == 1) {
            data.moveToFirst();
            id = data.getInt(data.getColumnIndexOrThrow(DBHelper.category_id));
        }
        return id;
    }

    public String getCategoryName(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor data =  db.rawQuery("SELECT " + DBHelper.category_name + " FROM " + DBHelper.categories_table +
                " WHERE " + DBHelper.category_id + "=?", new String[]{String.valueOf(id)});
        String name = "";
        if(data.getCount() == 1) {
            data.moveToFirst();
            name = data.getString(data.getColumnIndexOrThrow(DBHelper.category_name));
        }
        return name;
    }

    public List<Category> getTypeOfCategories(String type){
        List<Category> categoryList = new ArrayList<Category>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res =  db.rawQuery("SELECT * FROM " + DBHelper.categories_table +
                " WHERE " + DBHelper.category_type + "=?", new String[]{String.valueOf(type)});

        res.moveToFirst();
        while (res.isAfterLast() == false) {
            int cat_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.category_id));
            String cat_code = res.getString(res.getColumnIndexOrThrow(DBHelper.category_code));
            String cat_name = res.getString(res.getColumnIndexOrThrow(DBHelper.category_name));
            String cat_type = res.getString(res.getColumnIndexOrThrow(DBHelper.category_type));
            int cat_sync_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.category_sync_status));
            categoryList.add(new Category(cat_id,cat_code,cat_name,cat_type,cat_sync_status));
            res.moveToNext();
        }
        return categoryList;
    }

    public List<Category> getCategories(){
        List<Category> categoryList = new ArrayList<Category>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + categories_table, null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            int cat_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.category_id));
            String cat_code = res.getString(res.getColumnIndexOrThrow(DBHelper.category_code));
            String cat_name = res.getString(res.getColumnIndexOrThrow(DBHelper.category_name));
            String cat_type = res.getString(res.getColumnIndexOrThrow(DBHelper.category_type));
            int cat_sync_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.category_sync_status));
            categoryList.add(new Category(cat_id,cat_code,cat_name,cat_type,cat_sync_status));
            res.moveToNext();
        }
        return categoryList;
    }

    public Category getCategory(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + DBHelper.categories_table +
                " WHERE " + DBHelper.category_id + "=?", new String[]{String.valueOf(id)});//product_category_table_name
        Category category = new Category();
        if(res.getCount() == 1) {
            res.moveToFirst();
            int cat_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.category_id));
            category.setCategory_id(cat_id);
            String cat_code = res.getString(res.getColumnIndexOrThrow(DBHelper.category_code));
            category.setCategory_code(cat_code);
            String cat_name = res.getString(res.getColumnIndexOrThrow(DBHelper.category_name));
            category.setCategory_name(cat_name);
            String cat_type = res.getString(res.getColumnIndexOrThrow(DBHelper.category_type));
            category.setCategory_type(cat_type);
            int cat_sync_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.category_id));
            category.setCategory_syc_status(cat_sync_status);
        }
        return category;
    }

    public Category getCategoryWithName(String name){
        SQLiteDatabase db = getReadableDatabase();
        Cursor data =  db.rawQuery("SELECT * FROM " + DBHelper.categories_table +
                " WHERE " + DBHelper.category_name + "=?", new String[]{String.valueOf(name)});
        Category category = new Category();
        if(data.getCount() == 1) {
            data.moveToFirst();
            int cat_id = data.getInt(data.getColumnIndexOrThrow(DBHelper.category_id));
            category.setCategory_id(cat_id);
            String cat_code = data.getString(data.getColumnIndexOrThrow(DBHelper.category_code));
            category.setCategory_code(cat_code);
            String cat_name = data.getString(data.getColumnIndexOrThrow(DBHelper.category_name));
            category.setCategory_name(cat_name);
            String cat_type = data.getString(data.getColumnIndexOrThrow(DBHelper.category_type));
            category.setCategory_type(cat_type);
            int cat_sync_status = data.getInt(data.getColumnIndexOrThrow(DBHelper.category_id));
            category.setCategory_syc_status(cat_sync_status);
        }
        return category;
    }

    public boolean addUnit(Unit unit){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(unit_code, unit.getUnit_code());
        contentValues.put(unit_name, unit.getUnit_name());
        contentValues.put(unit_sync_status, unit.getUnit_sync_status());
        db.insert(units_table, null, contentValues);
        return true;
    }

    public boolean unitExists(String searchItem) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = { unit_name };
        String selection = unit_name + " =?";
        String[] selectionArgs = { searchItem };
        String limit = "1";
        Cursor cursor = db.query(units_table, columns, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public int getUnitID(String name) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor data =  db.rawQuery("SELECT " + DBHelper.unit_id + " FROM " + DBHelper.units_table +
                " WHERE " + DBHelper.unit_name + "=?", new String[]{name});
        int id = 0;
        if(data.getCount() == 1) {
            data.moveToFirst();
            id = data.getInt(data.getColumnIndexOrThrow(DBHelper.category_id));
        }
        return id;
    }

    public String getUnitName(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor data =  db.rawQuery("SELECT " + DBHelper.unit_name + " FROM " + DBHelper.units_table +
                " WHERE " + DBHelper.unit_id + "=?", new String[]{String.valueOf(id)});
        String name = "";
        if(data.getCount() == 1) {
            data.moveToFirst();
            name = data.getString(data.getColumnIndexOrThrow(DBHelper.unit_name));
        }
        return name;
    }

    public List<Unit> getUnits(){
        List<Unit> unitList = new ArrayList<Unit>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + units_table, null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            int cat_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.unit_code));
            String cat_code = res.getString(res.getColumnIndexOrThrow(DBHelper.unit_code));
            String cat_name = res.getString(res.getColumnIndexOrThrow(DBHelper.unit_name));
            int cat_sync_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.unit_sync_status));
            unitList.add(new Unit(cat_id,cat_code,cat_name,cat_sync_status));
            res.moveToNext();
        }
        return unitList;
    }

    public Unit getUnitWithID(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor data =  db.rawQuery("SELECT * FROM " + DBHelper.units_table +
                " WHERE " + DBHelper.unit_id + "=?", new String[]{String.valueOf(id)});
        Unit category = new Unit();
        if(data.getCount() == 1) {
            data.moveToFirst();
            int cat_id = data.getInt(data.getColumnIndexOrThrow(DBHelper.unit_id));
            category.setUnit_id(cat_id);
            String cat_code = data.getString(data.getColumnIndexOrThrow(DBHelper.unit_code));
            category.setUnit_code(cat_code);
            String cat_name = data.getString(data.getColumnIndexOrThrow(DBHelper.unit_name));
            category.setUnit_name(cat_name);
            int cat_sync_status = data.getInt(data.getColumnIndexOrThrow(DBHelper.unit_sync_status));
            category.setUnit_sync_status(cat_sync_status);
        }
        return category;
    }

    public boolean addCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(customer_code, customer.getCustomerCode());
        contentValues.put(customer_name, customer.getCustomerName());
        contentValues.put(customer_email, customer.getCustomerEmail());
        contentValues.put(customer_phone, customer.getCustomerPhone());
        contentValues.put(customer_sync_status, customer.getIsSynced());
        db.insert(customers_table, null, contentValues);
        return true;
    }

    public boolean customerExists(String searchItem) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = { customer_code };
        String selection = customer_code + " =?";
        String[] selectionArgs = { searchItem };
        String limit = "1";
        Cursor cursor = db.query(customers_table, columns, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public List<Customer> getCustomers(){
        List<Customer> customerList = new ArrayList<Customer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + customers_table, null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            int cust_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.customer_id));
            String cust_code = res.getString(res.getColumnIndexOrThrow(DBHelper.customer_code));
            String cust_name = res.getString(res.getColumnIndexOrThrow(DBHelper.customer_name));
            String cust_email = res.getString(res.getColumnIndexOrThrow(DBHelper.customer_email));
            String cust_phone = res.getString(res.getColumnIndexOrThrow(DBHelper.customer_phone));
            int cust_sync_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.customer_sync_status));
            customerList.add(new Customer(cust_id,cust_code,cust_name,cust_email,cust_phone,cust_sync_status));
            res.moveToNext();
        }
        return customerList;
    }

    public Customer getCustomerWithCode(String customer_code){
        SQLiteDatabase db = getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + DBHelper.customers_table +
                " WHERE " + DBHelper.customer_code + "=?", new String[]{customer_code});//product_category_table_name
        Customer customer = new Customer();
        if(res.getCount() == 1) {
            res.moveToFirst();
            int cust_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.customer_id));
            customer.setCustomerID(cust_id);
            String cust_code = res.getString(res.getColumnIndexOrThrow(DBHelper.customer_code));
            customer.setCustomerCode(cust_code);
            String cust_name = res.getString(res.getColumnIndexOrThrow(DBHelper.customer_name));
            customer.setCustomerName(cust_name);
            String cust_email = res.getString(res.getColumnIndexOrThrow(DBHelper.customer_email));
            customer.setCustomerEmail(cust_email);
            String cust_phone = res.getString(res.getColumnIndexOrThrow(DBHelper.customer_phone));
            customer.setCustomerPhone(cust_phone);
            int cust_sync_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.customer_sync_status));
            customer.setIsSynced(cust_sync_status);
        }
        return customer;
    }

    /*public String getCurrentCustomerCode(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor data =  db.rawQuery("SELECT " + DBHelper.current_transaction_customer_code + " FROM " + DBHelper.current_transaction_details_table +
                " WHERE " + DBHelper.current_transaction_id + "=?", new String[]{String.valueOf(id)});
        String code = "";
        if(data.getCount() == 1) {
            data.moveToFirst();
            code = data.getString(data.getColumnIndexOrThrow(DBHelper.current_transaction_customer_code));
        }
        return code;
    }
*/
    public boolean updateCustomerName(String code,String name) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues updateItem = new ContentValues();
            updateItem.put(customer_name, name);
            db.update(customers_table, updateItem, customer_code + " = '" + code + "'", null);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCustomerEmail(String code,String email) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues updateItem = new ContentValues();
            updateItem.put(customer_email, email);
            db.update(customers_table, updateItem, customer_code + " = '" + code + "'", null);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCustomerPhone(String code,String phone) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues updateItem = new ContentValues();
            updateItem.put(customer_phone, phone);
            db.update(customers_table, updateItem, customer_code + " = '" + code + "'", null);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addInventory(Inventory inventory){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(inventory_product_sku, inventory.getProductSKU());
        contentValues.put(inventory_stock, inventory.getStock());
        contentValues.put(inventory_is_synced, inventory.getIsSynced());
        db.insert(inventory_table, null, contentValues);
        return true;
    }

    public int getProductStock(String sku) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor data =  db.rawQuery("SELECT " + DBHelper.inventory_stock + " FROM " + DBHelper.inventory_table +
                " WHERE " + DBHelper.inventory_product_sku + "=?", new String[]{sku});//product_category_table_name
        int id = 0;
        if(data.getCount() == 1) {
            data.moveToFirst();
            id = data.getInt(data.getColumnIndexOrThrow(DBHelper.inventory_stock));
        }
        return id;
    }

    public int getInventoryID(String sku, int locationID) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor data =  db.rawQuery("SELECT " + DBHelper.inventory_id + " FROM " + DBHelper.inventory_table +
                " WHERE " + DBHelper.inventory_product_sku + "=?", new String[]{sku,String.valueOf(locationID)});//product_category_table_name
        int id = 0;
        if(data.getCount() == 1) {
            data.moveToFirst();
            id = data.getInt(data.getColumnIndexOrThrow(DBHelper.inventory_id));
        }
        return id;
    }

    public int checkInventory(String sku, int locationID) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor data =  db.rawQuery("SELECT " + DBHelper.inventory_id + " FROM " + DBHelper.inventory_table +
                " WHERE " + DBHelper.inventory_product_sku + "=?", new String[]{sku,String.valueOf(locationID)});//product_category_table_name
        int id = 0;
        if(data.getCount() == 1) {
            data.moveToFirst();
            id = data.getInt(data.getColumnIndexOrThrow(DBHelper.inventory_id));
        }
        return id;
    }

    public int getInventory(String sku) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor data =  db.rawQuery("SELECT " + DBHelper.inventory_stock + " FROM " + DBHelper.inventory_table +
                " WHERE " + DBHelper.inventory_product_sku + "=?", new String[]{sku});//product_category_table_name
        int id = 0;
        if(data.getCount() == 1) {
            data.moveToFirst();
            id = data.getInt(data.getColumnIndexOrThrow(DBHelper.inventory_stock));
        }
        return id;
    }

    public boolean updateInventoryStock(String sku,int stock) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues updateItem = new ContentValues();
            updateItem.put(inventory_stock, stock);
            db.update(inventory_table, updateItem, inventory_product_sku + " = '" + sku + "'", null);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Inventory> getInventory(){
        List<Inventory> inventoryList = new ArrayList<Inventory>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + inventory_table, null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            int id = res.getInt(res.getColumnIndexOrThrow(DBHelper.inventory_id));
            String sku = res.getString(res.getColumnIndexOrThrow(DBHelper.inventory_product_sku));
            int stock = res.getInt(res.getColumnIndexOrThrow(DBHelper.inventory_stock));
            int sync = res.getInt(res.getColumnIndexOrThrow(DBHelper.inventory_is_synced));
            inventoryList.add(new Inventory(id,sku,stock,sync));
            res.moveToNext();
        }
        return inventoryList;
    }

    public List<Inventory> getItemsWithInventory(int amount){
        List<Inventory> inventoryList = new ArrayList<Inventory>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + DBHelper.inventory_table +
                " WHERE " + DBHelper.inventory_stock + ">?", new String[]{String.valueOf(amount)});//product_category_table_name
       // Cursor res2 = db.rawQuery("SELECT * FROM " + inventory_table, null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            int id = res.getInt(res.getColumnIndexOrThrow(DBHelper.inventory_id));
            String sku = res.getString(res.getColumnIndexOrThrow(DBHelper.inventory_product_sku));
            int stock = res.getInt(res.getColumnIndexOrThrow(DBHelper.inventory_stock));
            int sync = res.getInt(res.getColumnIndexOrThrow(DBHelper.inventory_is_synced));
            inventoryList.add(new Inventory(id,sku,stock,sync));
            res.moveToNext();
        }
        return inventoryList;
    }

    public boolean addSaleItem(SaleItem saleItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(sale_item_sku, saleItem.getSaleItemSKU());
        contentValues.put(sale_item_name, saleItem.getSaleItemName());
        contentValues.put(sale_item_price, saleItem.getSaleItemPrice());
        contentValues.put(sale_item_quantity, saleItem.getSaleItemQuantity());
        contentValues.put(sale_item_total, saleItem.getSaleItemTotal());
        contentValues.put(sale_item_discount, saleItem.getSaleItemDiscount());
        contentValues.put(sale_item_sync_status, saleItem.getSaleItemSyncStatus());
        db.insert(sale_items_table, null, contentValues);
        return true;
    }

    public boolean saleItemExists(String searchItem) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = { sale_item_sku };
        String selection = sale_item_sku + " =?";
        String[] selectionArgs = { searchItem };
        String limit = "1";
        Cursor cursor = db.query(sale_items_table, columns, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public List<SaleItem> getSalesItems(){
        List<SaleItem> saleItemList = new ArrayList<SaleItem>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + sale_items_table, null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            int id = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_item_id));
            String i_sku = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_item_sku));
            String i_name = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_item_name));
            int i_price = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_item_price));
            int i_quantity = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_item_quantity));
            float i_total = res.getFloat(res.getColumnIndexOrThrow(DBHelper.sale_item_total));
            float i_discount = res.getFloat(res.getColumnIndexOrThrow(DBHelper.sale_item_discount));
            int i_sync_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_item_sync_status));
            saleItemList.add(new SaleItem(id,i_sku,i_name,i_price,i_quantity,i_total,i_discount,i_sync_status));
            res.moveToNext();
        }
        return saleItemList;
    }

    public SaleItem getSaleItemWithSKU(String sku){
        SQLiteDatabase db = getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + DBHelper.sale_items_table +
                " WHERE " + DBHelper.sale_item_sku + "=?", new String[]{String.valueOf(sku)});//product_category_table_name
        SaleItem saleItem = new SaleItem();
        if(res.getCount() == 1) {
            res.moveToFirst();
            int id = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_item_id));
            saleItem.setSaleItemID(id);
            String i_sku = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_item_sku));
            saleItem.setSaleItemSKU(i_sku);
            String i_name = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_item_name));
            saleItem.setSaleItemName(i_name);
            int i_price = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_item_price));
            saleItem.setSaleItemPrice(i_price);
            int i_quantity = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_item_quantity));
            saleItem.setSaleItemQuantity(i_quantity);
            float i_total = res.getFloat(res.getColumnIndexOrThrow(DBHelper.sale_item_total));
            saleItem.setSaleItemTotal(i_total);
            float i_discount = res.getFloat(res.getColumnIndexOrThrow(DBHelper.sale_item_discount));
            saleItem.setSaleItemDiscount(i_discount);
            int i_sync_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_item_sync_status));
            saleItem.setSaleItemSyncStatus(i_sync_status);
        }
        return saleItem;
    }

    public int getSaleItemQuantity(String sku) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor data =  db.rawQuery("SELECT " + DBHelper.sale_item_quantity + " FROM " + DBHelper.sale_items_table +
                " WHERE " + DBHelper.sale_item_sku + "=?", new String[]{sku});
        int id = 0;
        if(data.getCount() == 1) {
            data.moveToFirst();
            id = data.getInt(data.getColumnIndexOrThrow(DBHelper.sale_item_quantity));
        }
        return id;
    }

    public float getSaleItemPrice(String sku) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor data =  db.rawQuery("SELECT " + DBHelper.sale_item_price + " FROM " + DBHelper.sale_items_table +
                " WHERE " + DBHelper.sale_item_sku + "=?", new String[]{sku});
        float id = 0;
        if(data.getCount() == 1) {
            data.moveToFirst();
            id = data.getFloat(data.getColumnIndexOrThrow(DBHelper.sale_item_price));
        }
        return id;
    }

    @SuppressLint("Range")
    public float getTotal(){
        float sum = 0435;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT SUM(" + DBHelper.sale_item_total + ") as saleItemTotal FROM " + DBHelper.sale_items_table, null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            sum = res.getFloat(res.getColumnIndex("saleItemTotal"));
            res.moveToNext();
        }
        return sum;
    }

    public boolean updateSaleItemQuantity(String sku,int stock) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues updateItem = new ContentValues();
            updateItem.put(sale_item_quantity, stock);
            db.update(sale_items_table, updateItem, sale_item_sku + " = '" + sku + "'", null);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateSaleItemPrice(String sku,double price) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues updateItem = new ContentValues();
            updateItem.put(sale_item_price, price);
            db.update(sale_items_table, updateItem, sale_item_sku + " = '" + sku + "'", null);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateSaleItemTotal(String sku, double total) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues updateItem = new ContentValues();
            updateItem.put(sale_item_total, total);
            db.update(sale_items_table, updateItem, sale_item_sku + " = '" + sku + "'", null);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateSaleItemDiscount(String sku, double discount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(sale_item_discount, discount);
        db.update(sale_items_table, contentValues, sale_item_sku + "=" + sku, null);
        db.close();
        return true;
    }

    public boolean addCurrentTransactionDetails(CurrentTransactionDetails currentTransactionDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(current_transaction_identifier, currentTransactionDetails.getCurrentTransactionIdentifier());
        contentValues.put(current_transaction_customer_code, currentTransactionDetails.getCurrentTransactionCustomerCode());
        db.insert(current_transaction_details_table, null, contentValues);
        return true;
    }

    public CurrentTransactionDetails getCurrentTransactionDetails(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + DBHelper.current_transaction_details_table +
                " WHERE " + DBHelper.current_transaction_id + "=?", new String[]{String.valueOf(id)});//product_category_table_name
        CurrentTransactionDetails currentTransactionDetails = new CurrentTransactionDetails();
        if(res.getCount() == 1) {
            res.moveToFirst();
            int current_trans_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.current_transaction_id));
            currentTransactionDetails.setCurrentTransactionID(current_trans_id);
            String current_trans_identifier = res.getString(res.getColumnIndexOrThrow(DBHelper.current_transaction_identifier));
            currentTransactionDetails.setCurrentTransactionIdentifier(current_trans_identifier);
            String current_trans_customer_code = res.getString(res.getColumnIndexOrThrow(DBHelper.current_transaction_customer_code));
            currentTransactionDetails.setCurrentTransactionCustomerCode(current_trans_customer_code);
        }
        return currentTransactionDetails;
    }

    public boolean updateCurrentTransactionCustomerCode(int id,String code) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues updateItem = new ContentValues();
            updateItem.put(current_transaction_customer_code, code);
            db.update(current_transaction_details_table, updateItem, current_transaction_id + " = '" + id + "'", null);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCurrentTransactionIdentifier(int id,String identifier) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues updateItem = new ContentValues();
            updateItem.put(current_transaction_identifier, identifier);
            db.update(current_transaction_details_table, updateItem, current_transaction_id + " = '" + id + "'", null);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getCurrentCustomerCode(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor data =  db.rawQuery("SELECT " + DBHelper.current_transaction_customer_code + " FROM " + DBHelper.current_transaction_details_table +
                " WHERE " + DBHelper.current_transaction_id + "=?", new String[]{String.valueOf(id)});
        String code = "";
        if(data.getCount() == 1) {
            data.moveToFirst();
            code = data.getString(data.getColumnIndexOrThrow(DBHelper.current_transaction_customer_code));
        }
        return code;
    }

    public boolean addSaleTransaction(SaleTransaction saleTransaction){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(sale_transactions_customer_code, saleTransaction.getCustomerCode());
        contentValues.put(sale_transactions_identifier, saleTransaction.getSaleTransactionIdentifier());
        contentValues.put(sale_transactions_total, saleTransaction.getSaleTransactionTotal());
        contentValues.put(sale_transactions_date, saleTransaction.getSaleTransactionDate());
        contentValues.put(sale_transactions_payment_status, saleTransaction.getPaymentStatus());
        contentValues.put(sale_transactions_sync_status, saleTransaction.getSaleTransactionSyncStatus());
        db.insert(sale_transactions_table, null, contentValues);
        return true;
    }

    public SaleTransaction getSaleTransaction(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + DBHelper.sale_transactions_table +
                " WHERE " + DBHelper.sale_transactions_id + "=?", new String[]{String.valueOf(id)});//product_category_table_name
        SaleTransaction saleTransaction = new SaleTransaction();
        if(res.getCount() == 1) {
            res.moveToFirst();
            int sale_trans_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_id));
            saleTransaction.setSaleTransactionID(sale_trans_id);
            String sale_trans_customer_code = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_transactions_customer_code));
            saleTransaction.setCustomerCode(sale_trans_customer_code);
            String sales_trans_identifier = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_transactions_identifier));
            saleTransaction.setSaleTransactionIdentifier(sales_trans_identifier);
            float sale_trans_total = res.getFloat(res.getColumnIndexOrThrow(DBHelper.sale_transactions_total));
            saleTransaction.setSaleTransactionTotal(sale_trans_total);
            long sale_trans_date = res.getLong(res.getColumnIndexOrThrow(DBHelper.sale_transactions_date));
            saleTransaction.setSaleTransactionDate(sale_trans_date);
            int sale_trans_pay_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_payment_status));
            saleTransaction.setPaymentStatus(sale_trans_pay_status);
            int sale_trans_sync_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_sync_status));
            saleTransaction.setSaleTransactionID(sale_trans_sync_status);
        }
        return saleTransaction;
    }

    public SaleTransaction getSaleTransactionWithIdentifier(String identifier){
        SQLiteDatabase db = getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + DBHelper.sale_transactions_table +
                " WHERE " + DBHelper.sale_transactions_id + "=?", new String[]{String.valueOf(identifier)});//product_category_table_name
        SaleTransaction saleTransaction = new SaleTransaction();
        if(res.getCount() == 1) {
            res.moveToFirst();
            int sale_trans_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_id));
            saleTransaction.setSaleTransactionID(sale_trans_id);
            String sale_trans_customer_code = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_transactions_customer_code));
            saleTransaction.setCustomerCode(sale_trans_customer_code);
            String sales_trans_identifier = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_transactions_identifier));
            saleTransaction.setSaleTransactionIdentifier(sales_trans_identifier);
            float sale_trans_total = res.getFloat(res.getColumnIndexOrThrow(DBHelper.sale_transactions_total));
            saleTransaction.setSaleTransactionTotal(sale_trans_total);
            long sale_trans_date = res.getLong(res.getColumnIndexOrThrow(DBHelper.sale_transactions_date));
            saleTransaction.setSaleTransactionDate(sale_trans_date);
            int sale_trans_pay_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_payment_status));
            saleTransaction.setPaymentStatus(sale_trans_pay_status);
            int sale_trans_sync_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_sync_status));
            saleTransaction.setSaleTransactionID(sale_trans_sync_status);
        }
        return saleTransaction;
    }

    public List<SaleTransaction> getSaleTransactions(){
        List<SaleTransaction> saleTransactionList = new ArrayList<SaleTransaction>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + sale_transactions_table, null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            int sale_trans_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_id));
            //saleTransaction.setSaleTransactionID(sale_trans_id);
            String sale_trans_customer_code = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_transactions_customer_code));
            //saleTransaction.setCustomerCode(sale_trans_customer_code);
            String sales_trans_identifier = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_transactions_identifier));
            //saleTransaction.setSaleTransactionIdentifier(sales_trans_identifier);
            float sale_trans_total = res.getFloat(res.getColumnIndexOrThrow(DBHelper.sale_transactions_total));
            //saleTransaction.setSaleTransactionTotal(sale_trans_total);
            long sale_trans_date = res.getLong(res.getColumnIndexOrThrow(DBHelper.sale_transactions_date));
            //saleTransaction.setSaleTransactionDate(sale_trans_date);
            int sale_trans_pay_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_payment_status));
            //saleTransaction.setPaymentStatus(sale_trans_pay_status);
            int sale_trans_sync_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_sync_status));
           // saleTransaction.setSaleTransactionID(sale_trans_sync_status);

            //SaleTransaction(int saleTransactionID, String customerCode, String saleTransactionIdentifier, float saleTransactionTotal, long saleTransactionDate, int saleTransactionSyncStatus)
            saleTransactionList.add(new SaleTransaction(sale_trans_id,sale_trans_customer_code,sales_trans_identifier,sale_trans_total,sale_trans_date,sale_trans_pay_status,sale_trans_sync_status));
            res.moveToNext();
        }
        return saleTransactionList;
    }

    public List<SaleTransaction> getSaleTransactionsWithDate(long transDate){
        List<SaleTransaction> saleTransactionList = new ArrayList<SaleTransaction>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + sale_transactions_table +
                " WHERE " + DBHelper.sale_transactions_date + "=?", new String[]{String.valueOf(transDate)});
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            int sale_trans_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_id));
            //saleTransaction.setSaleTransactionID(sale_trans_id);
            String sale_trans_customer_code = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_transactions_customer_code));
            //saleTransaction.setCustomerCode(sale_trans_customer_code);
            String sales_trans_identifier = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_transactions_identifier));
            //saleTransaction.setSaleTransactionIdentifier(sales_trans_identifier);
            float sale_trans_total = res.getFloat(res.getColumnIndexOrThrow(DBHelper.sale_transactions_total));
            //saleTransaction.setSaleTransactionTotal(sale_trans_total);
            long sale_trans_date = res.getLong(res.getColumnIndexOrThrow(DBHelper.sale_transactions_date));
            //saleTransaction.setSaleTransactionDate(sale_trans_date);
            int sale_trans_pay_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_payment_status));
            //saleTransaction.setPaymentStatus(sale_trans_pay_status);
            int sale_trans_sync_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_sync_status));
            // saleTransaction.setSaleTransactionID(sale_trans_sync_status);

            //SaleTransaction(int saleTransactionID, String customerCode, String saleTransactionIdentifier, float saleTransactionTotal, long saleTransactionDate, int saleTransactionSyncStatus)
            saleTransactionList.add(new SaleTransaction(sale_trans_id,sale_trans_customer_code,sales_trans_identifier,sale_trans_total,sale_trans_date,sale_trans_pay_status,sale_trans_sync_status));
            res.moveToNext();
        }
        return saleTransactionList;
    }

    public List<SaleTransaction> getCreditSaleTransactions(){
        List<SaleTransaction> saleTransactionList = new ArrayList<SaleTransaction>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM " + sale_transactions_table +
                " WHERE " + DBHelper.sale_transactions_payment_status + "=?", new String[]{String.valueOf(0)});

        res.moveToFirst();
        while (res.isAfterLast() == false) {
            int sale_trans_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_id));
            //saleTransaction.setSaleTransactionID(sale_trans_id);
            String sale_trans_customer_code = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_transactions_customer_code));
            //saleTransaction.setCustomerCode(sale_trans_customer_code);
            String sales_trans_identifier = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_transactions_identifier));
            //saleTransaction.setSaleTransactionIdentifier(sales_trans_identifier);
            float sale_trans_total = res.getFloat(res.getColumnIndexOrThrow(DBHelper.sale_transactions_total));
            //saleTransaction.setSaleTransactionTotal(sale_trans_total);
            long sale_trans_date = res.getLong(res.getColumnIndexOrThrow(DBHelper.sale_transactions_date));
            //saleTransaction.setSaleTransactionDate(sale_trans_date);
            int sale_trans_pay_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_payment_status));
            //saleTransaction.setPaymentStatus(sale_trans_pay_status);
            int sale_trans_sync_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_sync_status));
            // saleTransaction.setSaleTransactionID(sale_trans_sync_status);

            //SaleTransaction(int saleTransactionID, String customerCode, String saleTransactionIdentifier, float saleTransactionTotal, long saleTransactionDate, int saleTransactionSyncStatus)
            saleTransactionList.add(new SaleTransaction(sale_trans_id,sale_trans_customer_code,sales_trans_identifier,sale_trans_total,sale_trans_date,sale_trans_pay_status,sale_trans_sync_status));
            res.moveToNext();
        }
        return saleTransactionList;
    }

    public List<SaleTransaction> getCustomerCreditSaleTransactions(String customer_code){
        List<SaleTransaction> saleTransactionList = new ArrayList<SaleTransaction>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM " + sale_transactions_table +
                " WHERE " + DBHelper.sale_transactions_payment_status + "=?" + " AND " + DBHelper.sale_transactions_customer_code + "=?", new String[]{String.valueOf(0),customer_code});

        res.moveToFirst();
        while (res.isAfterLast() == false) {
            int sale_trans_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_id));
            //saleTransaction.setSaleTransactionID(sale_trans_id);
            String sale_trans_customer_code = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_transactions_customer_code));
            //saleTransaction.setCustomerCode(sale_trans_customer_code);
            String sales_trans_identifier = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_transactions_identifier));
            //saleTransaction.setSaleTransactionIdentifier(sales_trans_identifier);
            float sale_trans_total = res.getFloat(res.getColumnIndexOrThrow(DBHelper.sale_transactions_total));
            //saleTransaction.setSaleTransactionTotal(sale_trans_total);
            long sale_trans_date = res.getLong(res.getColumnIndexOrThrow(DBHelper.sale_transactions_date));
            //saleTransaction.setSaleTransactionDate(sale_trans_date);
            int sale_trans_pay_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_payment_status));
            //saleTransaction.setPaymentStatus(sale_trans_pay_status);
            int sale_trans_sync_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_sync_status));
            // saleTransaction.setSaleTransactionID(sale_trans_sync_status);
            //SaleTransaction(int saleTransactionID, String customerCode, String saleTransactionIdentifier, float saleTransactionTotal, long saleTransactionDate, int saleTransactionSyncStatus)
            saleTransactionList.add(new SaleTransaction(sale_trans_id,sale_trans_customer_code,sales_trans_identifier,sale_trans_total,sale_trans_date,sale_trans_pay_status,sale_trans_sync_status));
            res.moveToNext();
        }
        return saleTransactionList;
    }

    public boolean addSaleTransactionItem(SaleTransactionItem saleTransactionItem){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(sale_item_transactions_identifier, saleTransactionItem.getSaleTransactionIdentifier());
        contentValues.put(sale_transactions_item_name, saleTransactionItem.getSaleTransactionItemName());
        contentValues.put(sale_transactions_item_sku, saleTransactionItem.getSaleTransactionItemSKU());
        contentValues.put(sale_transactions_item_price, saleTransactionItem.getSaleTransactionItemPrice());
        contentValues.put(sale_transactions_item_quantity, saleTransactionItem.getSaleTransactionItemQuantity());
        contentValues.put(sale_transactions_item_total, saleTransactionItem.getSaleTransactionItemTotal());
        contentValues.put(sale_transactions_item_sync_status, saleTransactionItem.getSaleTransactionItemSyncStatus());
        db.insert(sale_transactions_items_table, null, contentValues);
        return true;
    }

    public SaleTransactionItem getSaleTransactionItems(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + DBHelper.sale_transactions_items_table +
                " WHERE " + DBHelper.sale_transactions_item_id + "=?", new String[]{String.valueOf(id)});//product_category_table_name
        SaleTransactionItem saleTransactionItem = new SaleTransactionItem();
        if(res.getCount() == 1) {
            res.moveToFirst();
            int sale_trans_item_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_id));
            saleTransactionItem.setSaleTransactionItemID(sale_trans_item_id);
            String sale_trans_item_identifier = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_item_transactions_identifier));
            saleTransactionItem.setSaleTransactionIdentifier(sale_trans_item_identifier);
            String sale_trans_item_name = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_name));
            saleTransactionItem.setSaleTransactionItemName(sale_trans_item_name);
            String sale_trans_item_sku = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_sku));
            saleTransactionItem.setSaleTransactionItemSKU(sale_trans_item_sku);
            float sale_trans_item_price = res.getFloat(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_price));
            saleTransactionItem.setSaleTransactionItemPrice(sale_trans_item_price);
            int sale_trans_item_quantity = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_quantity));
            saleTransactionItem.setSaleTransactionItemQuantity(sale_trans_item_quantity);
            float sale_trans_item_total = res.getFloat(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_total));
            saleTransactionItem.setSaleTransactionItemTotal(sale_trans_item_total);
            int sale_trans_item_sync_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_sync_status));
            saleTransactionItem.setSaleTransactionItemSyncStatus(sale_trans_item_sync_status);
        }
        return saleTransactionItem;
    }

    public List<SaleTransactionItem> getSaleTransactionItems(){
        List<SaleTransactionItem> saleTransactionItemList = new ArrayList<SaleTransactionItem>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + sale_transactions_items_table, null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {

            int sale_trans_item_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_id));
            //saleTransactionItem.setSaleTransactionItemID(sale_trans_item_id);
            String sale_trans_item_identifier = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_item_transactions_identifier));
            //saleTransactionItem.setSaleTransactionIdentifier(sale_trans_item_identifier);
            String sale_trans_item_name = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_name));
            //saleTransactionItem.setSaleTransactionItemName(sale_trans_item_name);
            String sale_trans_item_sku = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_sku));
            //saleTransactionItem.setSaleTransactionItemSKU(sale_trans_item_sku);
            float sale_trans_item_price = res.getFloat(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_price));
            //saleTransactionItem.setSaleTransactionItemPrice(sale_trans_item_price);
            int sale_trans_item_quantity = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_quantity));
            //saleTransactionItem.setSaleTransactionItemQuantity(sale_trans_item_quantity);
            float sale_trans_item_total = res.getFloat(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_total));
            //saleTransactionItem.setSaleTransactionItemTotal(sale_trans_item_total);
            int sale_trans_item_sync_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_sync_status));
            //saleTransactionItem.setSaleTransactionItemSyncStatus(sale_trans_item_sync_status);
            //SaleTransactionItem(int saleTransactionItemID, String saleTransactionIdentifier, String saleTransactionItemName, String saleTransactionItemSKU, float saleTransactionItemPrice, int saleTransactionItemQuantity, float saleTransactionItemTotal, int saleTransactionItemSyncStatus)
            saleTransactionItemList.add(new SaleTransactionItem(sale_trans_item_id,sale_trans_item_identifier,sale_trans_item_name,sale_trans_item_sku,sale_trans_item_price,sale_trans_item_quantity,sale_trans_item_total,sale_trans_item_sync_status));
            res.moveToNext();
        }
        return saleTransactionItemList;
    }

    public List<SaleTransactionItem> getSaleTransactionItemsWithIdentifier(String identifier){
        List<SaleTransactionItem> saleTransactionItemList = new ArrayList<SaleTransactionItem>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + DBHelper.sale_transactions_items_table +
                " WHERE " + DBHelper.sale_item_transactions_identifier + "=?", new String[]{String.valueOf(identifier)});//product_category_table_name
        res.moveToFirst();
        while (res.isAfterLast() == false) {

            int sale_trans_item_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_id));
            //saleTransactionItem.setSaleTransactionItemID(sale_trans_item_id);
            String sale_trans_item_identifier = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_item_transactions_identifier));
            //saleTransactionItem.setSaleTransactionIdentifier(sale_trans_item_identifier);
            String sale_trans_item_name = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_name));
            //saleTransactionItem.setSaleTransactionItemName(sale_trans_item_name);
            String sale_trans_item_sku = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_sku));
            //saleTransactionItem.setSaleTransactionItemSKU(sale_trans_item_sku);
            float sale_trans_item_price = res.getFloat(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_price));
            //saleTransactionItem.setSaleTransactionItemPrice(sale_trans_item_price);
            int sale_trans_item_quantity = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_quantity));
            //saleTransactionItem.setSaleTransactionItemQuantity(sale_trans_item_quantity);
            float sale_trans_item_total = res.getFloat(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_total));
            //saleTransactionItem.setSaleTransactionItemTotal(sale_trans_item_total);
            int sale_trans_item_sync_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_sync_status));
            //saleTransactionItem.setSaleTransactionItemSyncStatus(sale_trans_item_sync_status);
            //SaleTransactionItem(int saleTransactionItemID, String saleTransactionIdentifier, String saleTransactionItemName, String saleTransactionItemSKU, float saleTransactionItemPrice, int saleTransactionItemQuantity, float saleTransactionItemTotal, int saleTransactionItemSyncStatus)
            saleTransactionItemList.add(new SaleTransactionItem(sale_trans_item_id,sale_trans_item_identifier,sale_trans_item_name,sale_trans_item_sku,sale_trans_item_price,sale_trans_item_quantity,sale_trans_item_total,sale_trans_item_sync_status));
            res.moveToNext();
        }
        return saleTransactionItemList;
    }

    public List<SaleItem> getSalesItemsWithIdentifier(String identifier){
        List<SaleItem> saleItemList = new ArrayList<SaleItem>();
        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor res = db.rawQuery("SELECT * FROM " + sale_items_table, null);

        Cursor res =  db.rawQuery("SELECT * FROM " + DBHelper.sale_transactions_items_table +
                " WHERE " + DBHelper.sale_item_transactions_identifier + "=?", new String[]{String.valueOf(identifier)});

        res.moveToFirst();
        while (res.isAfterLast() == false) {
            int sale_trans_item_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_id));
            //saleTransactionItem.setSaleTransactionItemID(sale_trans_item_id);
            String sale_trans_item_identifier = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_item_transactions_identifier));
            //saleTransactionItem.setSaleTransactionIdentifier(sale_trans_item_identifier);
            String sale_trans_item_name = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_name));
            //saleTransactionItem.setSaleTransactionItemName(sale_trans_item_name);
            String sale_trans_item_sku = res.getString(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_sku));
            //saleTransactionItem.setSaleTransactionItemSKU(sale_trans_item_sku);
            float sale_trans_item_price = res.getFloat(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_price));
            //saleTransactionItem.setSaleTransactionItemPrice(sale_trans_item_price);
            int sale_trans_item_quantity = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_quantity));
            //saleTransactionItem.setSaleTransactionItemQuantity(sale_trans_item_quantity);
            float sale_trans_item_total = res.getFloat(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_total));
            //saleTransactionItem.setSaleTransactionItemTotal(sale_trans_item_total);
            int sale_trans_item_sync_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.sale_transactions_item_sync_status));
            //saleTransactionItem.setSaleTransactionItemSyncStatus(sale_trans_item_sync_status);
            //SaleTransactionItem(int saleTransactionItemID, String saleTransactionIdentifier, String saleTransactionItemName, String saleTransactionItemSKU, float saleTransactionItemPrice, int saleTransactionItemQuantity, float saleTransactionItemTotal, int saleTransactionItemSyncStatus)
            //SaleItem(int saleItemID, String saleItemSKU, String saleItemName, float saleItemPrice, int saleItemQuantity, float saleItemTotal, float saleItemDiscount, int saleItemSyncStatus)
            saleItemList.add(new SaleItem(sale_trans_item_id,sale_trans_item_sku,sale_trans_item_name,sale_trans_item_price,sale_trans_item_quantity,sale_trans_item_total,0,sale_trans_item_sync_status));
            res.moveToNext();
        }
        return saleItemList;
    }

    public boolean addRoute(Route route){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(route_truck_id, route.getTruckID());
        contentValues.put(route_name, route.getRouteName());
        contentValues.put(route_sync_status, route.getIsSynced());
        db.insert(routes_table, null, contentValues);
        return true;
    }

    public boolean routeExists(String searchItem) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = { route_name };
        String selection = route_name + " =?";
        String[] selectionArgs = { searchItem };
        String limit = "1";
        Cursor cursor = db.query(routes_table, columns, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public int getRouteID(String name) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor data =  db.rawQuery("SELECT " + DBHelper.route_id + " FROM " + DBHelper.routes_table +
                " WHERE " + DBHelper.route_name + "=?", new String[]{name});
        int id = 0;
        if(data.getCount() == 1) {
            data.moveToFirst();
            id = data.getInt(data.getColumnIndexOrThrow(DBHelper.route_id));
        }
        return id;
    }

    public String getRouteName(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor data =  db.rawQuery("SELECT " + DBHelper.route_name + " FROM " + DBHelper.routes_table +
                " WHERE " + DBHelper.route_id + "=?", new String[]{String.valueOf(id)});
        String name = "";
        if(data.getCount() == 1) {
            data.moveToFirst();
            name = data.getString(data.getColumnIndexOrThrow(DBHelper.route_name));
        }
        return name;
    }

    /*    public static final String route_table = "routes";
    public static final String route_id = "routeID";
    public static final String route_truck_id = "routeTruckID";
    public static final String route_name = "routeName";
    public static final String route_sync_status = "routeSyncStatus";*/

    public List<Route> getRoutes(){
        List<Route> routeList = new ArrayList<Route>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + routes_table, null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            int rot_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.route_id));
            String rot_tru_id = res.getString(res.getColumnIndexOrThrow(DBHelper.route_truck_id));
            String rot_name = res.getString(res.getColumnIndexOrThrow(DBHelper.route_name));
            int rot_sync_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.route_sync_status));
            routeList.add(new Route(rot_id,rot_tru_id,rot_name,rot_sync_status));
            res.moveToNext();
        }
        return routeList;
    }

    public List<Route> getTruckRoutes(String truckID){
        List<Route> routeList = new ArrayList<Route>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + DBHelper.routes_table +
                " WHERE " + DBHelper.route_truck_id + "=?", new String[]{String.valueOf(truckID)});
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            int rot_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.route_id));
            String rot_tru_id = res.getString(res.getColumnIndexOrThrow(DBHelper.route_truck_id));
            String rot_name = res.getString(res.getColumnIndexOrThrow(DBHelper.route_name));
            int rot_sync_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.route_sync_status));
            routeList.add(new Route(rot_id,rot_tru_id,rot_name,rot_sync_status));
            res.moveToNext();
        }
        return routeList;
    }

    public Route getRouteWithID(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + DBHelper.routes_table +
                " WHERE " + DBHelper.route_id + "=?", new String[]{String.valueOf(id)});
        Route route = new Route();
        if(res.getCount() == 1) {
            res.moveToFirst();
            int rot_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.route_id));
            route.setRouteID(rot_id);
            String rot_tru_id = res.getString(res.getColumnIndexOrThrow(DBHelper.route_truck_id));
            route.setTruckID(rot_tru_id);
            String rot_name = res.getString(res.getColumnIndexOrThrow(DBHelper.route_name));
            route.setRouteName(rot_name);
            int rot_sync_status = res.getInt(res.getColumnIndexOrThrow(DBHelper.route_sync_status));
            route.setIsSynced(rot_sync_status);
        }
        return route;
    }

    public boolean addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(user_identification, user.getUser_identification());
        contentValues.put(user_name, user.getName());
        contentValues.put(user_truck_id, user.getTruckID());
        db.insert(user_table, null, contentValues);
        return true;
    }

    public User getUserWithID(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + DBHelper.user_table +
                " WHERE " + DBHelper.user_id + "=?", new String[]{String.valueOf(id)});
        User user = new User();
        if(res.getCount() == 1) {
            res.moveToFirst();
            int us_id = res.getInt(res.getColumnIndexOrThrow(DBHelper.user_id));
            user.setUser_id(us_id);
            String us_identification = res.getString(res.getColumnIndexOrThrow(DBHelper.user_identification));
            user.setUser_identification(us_identification);
            String us_name = res.getString(res.getColumnIndexOrThrow(DBHelper.user_name));
            user.setName(us_name);
            String us_truck_id = res.getString(res.getColumnIndexOrThrow(DBHelper.user_truck_id));
            user.setTruckID(us_truck_id);
        }
        return user;
    }

    public String getUSerTruckID(int num) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor data =  db.rawQuery("SELECT " + DBHelper.user_truck_id + " FROM " + DBHelper.user_table +
                " WHERE " + DBHelper.user_id + "=?", new String[]{String.valueOf(num)});
        String tid = "";
        if(data.getCount() == 1) {
            data.moveToFirst();
            tid = data.getString(data.getColumnIndexOrThrow(DBHelper.user_truck_id));
        }
        return tid;
    }

    public boolean addPayment(Payment payment){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(payment_code, payment.getPaymentCode());
        contentValues.put(payment_saleTransactionIdentifier, payment.getSaleTransactionIdentifier());
        contentValues.put(payment_type, payment.getPaymentType());
        contentValues.put(payment_amount, payment.getPaymentAmount());
        contentValues.put(payment_date, payment.getPaymentDate());
        db.insert(payments_table, null, contentValues);
        return true;
    }

    public Payment getPaymentUsingSaleIdentifier(String sale_identifier){
        SQLiteDatabase db = getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + DBHelper.payments_table +
                " WHERE " + DBHelper.payment_saleTransactionIdentifier + "=?", new String[]{String.valueOf(sale_identifier)});//product_category_table_name
        Payment payment = new Payment();
        if(res.getCount() == 1) {
            res.moveToFirst();
            int id = res.getInt(res.getColumnIndexOrThrow(DBHelper.payment_id));
            payment.setPaymentID(id);
            String pay_code = res.getString(res.getColumnIndexOrThrow(DBHelper.payment_code));
            payment.setPaymentCode(pay_code);
            String pay_sale_identifier = res.getString(res.getColumnIndexOrThrow(DBHelper.payment_saleTransactionIdentifier));
            payment.setSaleTransactionIdentifier(pay_sale_identifier);
            String pay_type = res.getString(res.getColumnIndexOrThrow(DBHelper.payment_type));
            payment.setPaymentType(pay_type);
            float pay_amount = res.getFloat(res.getColumnIndexOrThrow(DBHelper.payment_amount));
            payment.setPaymentAmount(pay_amount);
            long pay_date = res.getLong(res.getColumnIndexOrThrow(DBHelper.payment_date));
            payment.setPaymentDate(pay_date);
        }
        return payment;
    }

    @SuppressLint("Range")
    public float getReceiptPaymentsTotal(String sale_identifier){
        float sum = 0435;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT SUM(" + DBHelper.payment_amount + ") as paymentAmount FROM " + DBHelper.payments_table +
                " WHERE " + DBHelper.payment_saleTransactionIdentifier + "=?", new String[]{String.valueOf(sale_identifier)});
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            sum = res.getFloat(res.getColumnIndex("paymentAmount"));
            res.moveToNext();
        }
        return sum;
    }

}
