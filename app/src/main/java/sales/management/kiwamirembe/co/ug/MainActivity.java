package sales.management.kiwamirembe.co.ug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    Toolbar toolbar;
    ImageView home_img,products_img,receipts_img,customers_img,settings_img;
    TextView home_text,products_text,receipts_text,customers_text,settings_text;
    LinearLayout goToHome,goToProducts,goToReceipts,goToCustomers,goToSettings;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        toolbar = findViewById(R.id.main_toolbar);
        int pdt_count = dbHelper.numberOfRows(DBHelper.products_table);
        int unit_count = dbHelper.numberOfRows(DBHelper.units_table);
        int category_count = dbHelper.numberOfRows(DBHelper.categories_table);
        int customer_count = dbHelper.numberOfRows(DBHelper.customers_table);
        int inventory_count = dbHelper.numberOfRows(DBHelper.inventory_table);
        int sale_trans_count = dbHelper.numberOfRows(DBHelper.sale_transactions_table);
        int sale_trans_items_count = dbHelper.numberOfRows(DBHelper.sale_transactions_items_table);

        if (unit_count == 0){
            //Unit(String unit_code, String unit_name, int unit_sync_status)
            //PiecedbHelper.addUnit(new Unit("pce","piece",0));
        }
        if (category_count == 0){
            //Category(String category_code, String category_name, String category_type, int category_syc_status)
            dbHelper.addCategory(new Category("10001","Borehole","pdt",0));
            dbHelper.addCategory(new Category("10002","UPVC Pipes","pdt",0));
            dbHelper.addCategory(new Category("10003","GI Pipes","pdt",0));
            dbHelper.addCategory(new Category("10004","HDPE Pipes","pdt",0));
            dbHelper.addCategory(new Category("10005","PPR Pipes","pdt",0));
        }
        if (pdt_count == 0){
            //Product(String product_sku, String product_name, int product_unit_id, int product_category_id, float product_unit_price, int product_sync_status)
            dbHelper.addProduct(new Product("1080","1-1/2 inch ",1,1,14000,1));
            dbHelper.addProduct(new Product("1081","2 inch",1,1,20000,1));
            dbHelper.addProduct(new Product("1082","3 inch White",1,1,23000,1));
            dbHelper.addProduct(new Product("1083","4 inch M. Gauge",1,1,33000,1));
           /* dbHelper.addProduct(new Product("100110007","1-1/4 SS Pipe 304",1,1,170000,1));
            dbHelper.addProduct(new Product("100110008","1-1/4 S Pipe 304 Loose Socket",1,1,185000,1));
            dbHelper.addProduct(new Product("100110009","1-1/4 SS Pipe 202",1,1,150000,1));
            dbHelper.addProduct(new Product("100110010","1-1/4 GI Pipe",1,1,50000,1));
            dbHelper.addProduct(new Product("100110011","SS Rod 304",1,1,70000,1));
            dbHelper.addProduct(new Product("100110012","SS Rod 202",1,1,65000,1));
            dbHelper.addProduct(new Product("100110013","MS Rod",1,1,20000,1));
            dbHelper.addProduct(new Product("100110014","1-1/4 SS Pipe 304",1,1,170000,1));
            dbHelper.addProduct(new Product("100110015","1-1/4 S Pipe 304 Loose Socket",1,1,185000,1));
            dbHelper.addProduct(new Product("100110016","1-1/4 SS Pipe 202",1,1,150000,1));
            dbHelper.addProduct(new Product("100110017","1-1/4 GI Pipe",1,1,50000,1));
            dbHelper.addProduct(new Product("100110018","SS Rod 304",1,1,70000,1));
            dbHelper.addProduct(new Product("100110019","SS Rod 202",1,1,65000,1));
            dbHelper.addProduct(new Product("100110020","MS Rod",1,1,20000,1));
            dbHelper.addProduct(new Product("100110021","1-1/4 SS Pipe 304",1,1,170000,1));
            dbHelper.addProduct(new Product("100110022","1-1/4 S Pipe 304 Loose Socket",1,1,185000,1));
            dbHelper.addProduct(new Product("100110023","1-1/4 SS Pipe 202",1,1,150000,1));
            dbHelper.addProduct(new Product("100110024","1-1/4 GI Pipe",1,1,50000,1));
            dbHelper.addProduct(new Product("100110025","SS Rod 304",1,1,70000,1));
            dbHelper.addProduct(new Product("100110026","SS Rod 202",1,1,65000,1));
            dbHelper.addProduct(new Product("100110027","MS Rod",1,1,20000,1));
            dbHelper.addProduct(new Product("100110028","1-1/4 SS Pipe 304",1,1,170000,1));
            dbHelper.addProduct(new Product("100110029","1-1/4 S Pipe 304 Loose Socket",1,1,185000,1));
            dbHelper.addProduct(new Product("100110024","1-1/4 SS Pipe 202",1,1,150000,1));
            dbHelper.addProduct(new Product("100110025","1-1/4 GI Pipe",1,1,50000,1));
            dbHelper.addProduct(new Product("100110026","SS Rod 304",1,1,70000,1));
            dbHelper.addProduct(new Product("100110027","SS Rod 202",1,1,65000,1));
            dbHelper.addProduct(new Product("100110028","MS Rod",1,1,20000,1));*/
        }
        if (customer_count == 0){
            //Customer(String customerCode, String customerName, String customerEmail, String customerPhone)
            dbHelper.addCustomer(new Customer("955899524868289252478564","Paul Mpanga","paulmpanga@gmail.com","0771874334",1));
            dbHelper.addCustomer(new Customer("0976540118847084","Buyaya Technical Services Limited Nakasero","bts.nakasero@buyaya.co.ug","0771874335",1));
            dbHelper.addCustomer(new Customer("5572524434006487074","Buyaya Technical Services Limited Katwe","bts.katwe@buyaya.co.ug","0771874336",1));
        }

        if (inventory_count == 0){
            //Inventory(String productSKU, int stock, int isSynced)
            dbHelper.addInventory(new Inventory("1080",3000,0));
            dbHelper.addInventory(new Inventory("1081",100,0));
            dbHelper.addInventory(new Inventory("1082",500,0));
            dbHelper.addInventory(new Inventory("1083",300,0));
           /* dbHelper.addInventory(new Inventory("100110007",3000,0));
            dbHelper.addInventory(new Inventory("100110008",100,0));
            dbHelper.addInventory(new Inventory("100110009",500,0));
            dbHelper.addInventory(new Inventory("100110010",300,0));
            dbHelper.addInventory(new Inventory("100110011",2000,0));
            dbHelper.addInventory(new Inventory("100110012",1500,0));
            dbHelper.addInventory(new Inventory("100110013",750,0));
            dbHelper.addInventory(new Inventory("100110014",3000,0));
            dbHelper.addInventory(new Inventory("100110015",100,0));
            dbHelper.addInventory(new Inventory("100110016",500,0));
            dbHelper.addInventory(new Inventory("100110017",300,0));
            dbHelper.addInventory(new Inventory("100110018",2000,0));
            dbHelper.addInventory(new Inventory("100110019",1500,0));
            dbHelper.addInventory(new Inventory("100110020",750,0));
            dbHelper.addInventory(new Inventory("100110021",750,0));
            dbHelper.addInventory(new Inventory("100110022",3000,0));
            dbHelper.addInventory(new Inventory("100110023",100,0));
            dbHelper.addInventory(new Inventory("100110024",500,0));
            dbHelper.addInventory(new Inventory("100110025",300,0));
            dbHelper.addInventory(new Inventory("100110026",2000,0));
            dbHelper.addInventory(new Inventory("100110027",1500,0));
            dbHelper.addInventory(new Inventory("100110028",750,0));*/

        }

        home_img = findViewById(R.id.home_img);
        products_img = findViewById(R.id.products_img);
        receipts_img = findViewById(R.id.receipts_img);
        customers_img = findViewById(R.id.customers_img);
        settings_img = findViewById(R.id.settings_img);

        home_text = findViewById(R.id.home_text);
        products_text = findViewById(R.id.products_text);
        receipts_text = findViewById(R.id.receipts_text);
        customers_text = findViewById(R.id.customers_text);
        settings_text = findViewById(R.id.settings_text);

        goToHome = findViewById(R.id.goToHome);
        goToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadHomeFragment();
            }
        });
        goToProducts = findViewById(R.id.goToProducts);
        goToProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadProductsFragment();
            }
        });
        goToReceipts = findViewById(R.id.goToReceipts);
        goToReceipts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadReceiptsFragment();
            }
        });
        goToCustomers = findViewById(R.id.goToCustomers);
        goToCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadCustomersFragment();
            }
        });
        goToSettings = findViewById(R.id.goToSettings);
        goToSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadSettingsFragment();
            }
        });

        if (getIntent().getExtras() != null){
            String source = getIntent().getExtras().getString("start");
            if (source.equals("home")){
                //loadHomeFragment();
                loadProductsFragment();
            } else if (source.equals("products")){
                loadProductsFragment();
            }else if (source.equals("receipts")){
                loadReceiptsFragment();
            }else if (source.equals("customers")){
                loadCustomersFragment();
            }else if (source.equals("settings")){
                loadSettingsFragment();
            }
        } else {
            //loadHomeFragment();
            loadProductsFragment();
        }

    }

    public void loadHomeFragment(){
        toolbar.setTitle("Home");
        home_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_home_selected));
        home_text.setTextColor(getResources().getColor(R.color.purple_700));
        products_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_products_24));
        products_text.setTextColor(getResources().getColor(R.color.grey_600));
        receipts_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_receipts_24));
        receipts_text.setTextColor(getResources().getColor(R.color.grey_600));
        customers_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_customers_24));
        customers_text.setTextColor(getResources().getColor(R.color.grey_600));
        settings_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_settings_24));
        settings_text.setTextColor(getResources().getColor(R.color.grey_600));

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_container, new HomeFragment());
        ft.commit();

       /* FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_container, new PhoneAuthFragment());
        ft.commit();*/
    }

    public void loadProductsFragment(){
        toolbar.setTitle("Products");
        home_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_home_24));
        home_text.setTextColor(getResources().getColor(R.color.grey_600));
        products_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_products_selected));
        products_text.setTextColor(getResources().getColor(R.color.purple_700));
        receipts_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_receipts_24));
        receipts_text.setTextColor(getResources().getColor(R.color.grey_600));
        customers_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_customers_24));
        customers_text.setTextColor(getResources().getColor(R.color.grey_600));
        settings_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_settings_24));
        settings_text.setTextColor(getResources().getColor(R.color.grey_600));

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_container, new ProductsFragment());
        ft.commit();
    }

    public void loadReceiptsFragment(){
        toolbar.setTitle("Receipts");
        home_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_home_24));
        home_text.setTextColor(getResources().getColor(R.color.grey_600));
        products_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_products_24));
        products_text.setTextColor(getResources().getColor(R.color.grey_600));
        receipts_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_receipts_selected));
        receipts_text.setTextColor(getResources().getColor(R.color.purple_700));
        customers_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_customers_24));
        customers_text.setTextColor(getResources().getColor(R.color.grey_600));
        settings_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_settings_24));
        settings_text.setTextColor(getResources().getColor(R.color.grey_600));

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_container, new ReceiptsFragment());
        ft.commit();
    }

    public void loadCustomersFragment(){
        toolbar.setTitle("Customers");
        home_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_home_24));
        home_text.setTextColor(getResources().getColor(R.color.grey_600));
        products_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_products_24));
        products_text.setTextColor(getResources().getColor(R.color.grey_600));
        receipts_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_receipts_24));
        receipts_text.setTextColor(getResources().getColor(R.color.grey_600));
        customers_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_customers_selected));
        customers_text.setTextColor(getResources().getColor(R.color.purple_700));
        settings_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_settings_24));
        settings_text.setTextColor(getResources().getColor(R.color.grey_600));

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_container, new CustomersFragment());
        ft.commit();
    }

    public void loadSettingsFragment(){
        toolbar.setTitle("Settings");
        home_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_home_24));
        home_text.setTextColor(getResources().getColor(R.color.grey_600));
        products_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_products_24));
        products_text.setTextColor(getResources().getColor(R.color.grey_600));
        receipts_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_receipts_24));
        receipts_text.setTextColor(getResources().getColor(R.color.grey_600));
        customers_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_customers_24));
        customers_text.setTextColor(getResources().getColor(R.color.grey_600));
        settings_img.setImageDrawable(getDrawable(R.drawable.ic_baseline_settings_selected));
        settings_text.setTextColor(getResources().getColor(R.color.purple_700));

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_container, new SettingsFragment());
        ft.commit();
    }

}