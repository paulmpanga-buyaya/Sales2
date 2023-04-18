package sales.management.kiwamirembe.co.ug;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateSaleActivity extends AppCompatActivity implements InventoryAdapter.InventoryAdapterListener, CustomerAdapter.CustomerAdapterListener {

    LinearLayout cartTotalDisplay;
    DBHelper dbHelper;
    private RecyclerView recyclerView;
    private List<Inventory> inventoryList;
    private InventoryAdapter mAdapter;
    private CustomerAdapter mCustomerAdapter;
    private SearchView searchView;
    private TextView amount;
    //private List<Customer> customerList = new ArrayList<>();
    private List<Customer> customerList = new ArrayList<>();
    private Menu menu;

    String get_all_customers = "https://kiwamirembe.com/pos2/auth/get-all-customers.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sale);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dbHelper = new DBHelper(this);
        getSupportActionBar().setTitle("Ticket");

        //String source = getIntent().getExtras().getString("source");
        Intent intent = getIntent();
        if (intent.getExtras() != null){
            //Toast.makeText(getApplicationContext(), "There is data",Toast.LENGTH_LONG).show();
        } else {
            //Toast.makeText(getApplicationContext(), "There is no data",Toast.LENGTH_LONG).show();
        }


        recyclerView = findViewById(R.id.createSaleInventoryRecycler);
        //searchView = findViewById(R.id.searchInventory);
         //searchItems(searchView);
        //productList = new ArrayList<>();
        //inventoryList = dbHelper.getInventory();
        inventoryList = dbHelper.getItemsWithInventory(0);
        mAdapter = new InventoryAdapter(CreateSaleActivity.this, inventoryList, this::onInventorySelected);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(CreateSaleActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(CreateSaleActivity.this, DividerItemDecoration.VERTICAL, 36));
        recyclerView.setAdapter(mAdapter);

        DecimalFormat formatter = new DecimalFormat("#,###.00");
        amount = findViewById(R.id.amount);
        amount.setText(String.valueOf(formatter.format(dbHelper.getTotal())));
        //amount.setText(String.valueOf(dbHelper.getTotal()));
        amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
            }
        });

        cartTotalDisplay = findViewById(R.id.cartTotalDisplay);
        cartTotalDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
            }
        });

        int current_transactions = dbHelper.numberOfRows(DBHelper.current_transaction_details_table);
        if (current_transactions == 0){
            String invoice_identifier = String.valueOf(UUID.randomUUID());
            dbHelper.addCurrentTransactionDetails(new CurrentTransactionDetails(invoice_identifier,"none"));
        }


    }

    @Override
    public void onInventorySelected(Inventory inventory) {
        Product item = dbHelper.getProductWithSKU(inventory.getProductSKU());
        //String UUID
        if (dbHelper.saleItemExists(inventory.getProductSKU())){
            float saved_price = item.getProduct_unit_price();
            float item_sale_price = dbHelper.getSaleItemPrice(item.getProduct_sku());
            if (saved_price == item_sale_price){
                SaleItem saleItem1 = dbHelper.getSaleItemWithSKU(inventory.getProductSKU());
                int current_quantity = saleItem1.getSaleItemQuantity();
                int new_quantity = current_quantity + 1;
                float total = saleItem1.getSaleItemPrice() * new_quantity;
                dbHelper.updateSaleItemQuantity(saleItem1.getSaleItemSKU(),new_quantity);
                dbHelper.updateSaleItemTotal(saleItem1.getSaleItemSKU(),total);
                startActivity(new Intent(getApplicationContext(), CreateSaleActivity.class));
            } else {
                SaleItem saleItem1 = dbHelper.getSaleItemWithSKU(inventory.getProductSKU());
                int current_quantity = saleItem1.getSaleItemQuantity();
                int new_quantity = current_quantity + 1;
                float total = item_sale_price * new_quantity;
                dbHelper.updateSaleItemQuantity(saleItem1.getSaleItemSKU(),new_quantity);
                dbHelper.updateSaleItemTotal(saleItem1.getSaleItemSKU(),total);
                startActivity(new Intent(getApplicationContext(), CreateSaleActivity.class));
            }
        } else {
            Product product = dbHelper.getProductWithSKU(inventory.getProductSKU());
            //SaleItem(String saleItemSKU, String saleItemName, float saleItemPrice, int saleItemQuantity, float saleItemTotal, float saleItemDiscount, int saleItemSyncStatus)
            SaleItem saleItem = new SaleItem(inventory.getProductSKU(),product.getProduct_name(),product.getProduct_unit_price(),1,product.getProduct_unit_price(),0,0);
            dbHelper.addSaleItem(saleItem);
            startActivity(new Intent(getApplicationContext(), CreateSaleActivity.class));
        }
    }

    @Override
    public void onCustomerSelected(Customer customer) {
        dbHelper.updateCurrentTransactionCustomerCode(1,customer.getCustomerCode());
        if ((dbHelper.getCurrentCustomerCode(customer.getCustomerID())).equals("none")){
            Toast.makeText(getApplicationContext(), "Customer code not added",Toast.LENGTH_SHORT).show();
        } else {
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_baseline_person_added_24));
            startActivity(new Intent(getApplicationContext(), CreateSaleActivity.class));
        }
    }

    public void searchCustomers(SearchView sv) {
        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        sv.setMaxWidth(Integer.MAX_VALUE);
        // listening to search query text change
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_sale_menu, menu);
        this.menu = menu;
        String code = dbHelper.getCurrentCustomerCode(1);
        if (code.equals("none") || code.equals("")){
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_baseline_person_add_24));
        }else {
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_baseline_person_added_24));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.add_customer_to_cart){
            //addCustomerDialog();
            //startActivity(new Intent(getApplicationContext(), SelectCustomerActivity.class));
            Intent intent = new Intent(getApplicationContext(), SelectCustomerActivity.class);
            intent.putExtra("source","create_sale_activity");
            startActivity(intent);
        }
        if (id == R.id.clear) {
            dbHelper.delete(DBHelper.sale_items_table);
            dbHelper.delete(DBHelper.current_transaction_details_table);
            startActivity(new Intent(getApplicationContext(), CreateSaleActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("start","home");
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}