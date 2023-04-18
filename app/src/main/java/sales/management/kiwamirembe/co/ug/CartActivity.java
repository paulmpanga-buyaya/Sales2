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
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends BaseActivity implements SaleItemAdapter.SaleItemAdapterListener,CustomerAdapter.CustomerAdapterListener {

    LinearLayout transactionDate, startPaymentDialog, goToInvoices;
    private RecyclerView recyclerView;
    private List<SaleItem> saleItemList;
    private SaleItemAdapter mAdapter;
    private CustomerAdapter mCustomerAdapter;
    private SearchView searchView;
    DBHelper dbHelper;

    EditText dateTxt,customer_name;
    TextView amount;
    MaterialButton pay;

    private List<Customer> customerList = new ArrayList<>();

    private Menu menu;

    AlertDialog.Builder builder;

    LinearLayout transactionCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Cart summary");
        dbHelper = new DBHelper(this);

        int num_customers = dbHelper.numberOfRows(DBHelper.customers_table);
        //Toast.makeText(getApplicationContext(), String.valueOf(num_customers),Toast.LENGTH_LONG).show();

        recyclerView = findViewById(R.id.productsInCartRecyclerView);
        //searchView = findViewById(R.id.searchProductsInCart);
        //searchItemsInCart(searchView);
        //productList = new ArrayList<>();
        saleItemList = dbHelper.getSalesItems();
        mAdapter = new SaleItemAdapter(CartActivity.this, saleItemList, this::onSaleItemSelected);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(CartActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(CartActivity.this, DividerItemDecoration.VERTICAL, 36));
        recyclerView.setAdapter(mAdapter);

        transactionDate = findViewById(R.id.transactionDate);
        dateTxt = findViewById(R.id.dateTxt);
        setDate(dateTxt,CartActivity.this);

        CurrentTransactionDetails currentTransactionDetails = dbHelper.getCurrentTransactionDetails(1);
        Customer customer = dbHelper.getCustomerWithCode(currentTransactionDetails.getCurrentTransactionCustomerCode());
        customer_name = findViewById(R.id.customer_name);
        customer_name.setText(customer.getCustomerName());

        /*transactionCustomer = (LinearLayout)findViewById(R.id.transactionCustomer);
        transactionCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectCustomerActivity.class);
                intent.putExtra("source","cart_activity");
                startActivity(intent);
            }
        });*/

        amount = findViewById(R.id.amount);
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        //amount.setText(String.valueOf(dbHelper.getTotal()));
        amount.setText(String.valueOf(formatter.format(dbHelper.getTotal())));

        pay = findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), dateTxt.getText(),Toast.LENGTH_SHORT).show();
               /* Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                intent.putExtra("source","cart");
                intent.putExtra("date",dateTxt.getText());
                startActivity(intent);*/
// creating a intent
                Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
// creating a bundle object
                Bundle bundle = new Bundle();
// storing the string value in the bundle
// which is mapped to key
                bundle.putString("date", String.valueOf(dateTxt.getText()));
// passing the bundle into the intent
                intent.putExtras(bundle);
// starting the intent
                startActivity(intent);

            }
        });

        customer_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addCustomerDialog();
            }
        });
    }

    @Override
    public void onCustomerSelected(Customer customer) {
        dbHelper.updateCurrentTransactionCustomerCode(1,customer.getCustomerCode());
        if ((dbHelper.getCurrentCustomerCode(customer.getCustomerID())).equals("none")){
            Toast.makeText(getApplicationContext(), "Customer code not added",Toast.LENGTH_SHORT).show();
        } else {
            customer_name.setText(customer.getCustomerName());
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

    public void searchItemsInCart(SearchView sv) {
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
    public void onSaleItemSelected(SaleItem product) {
        //Toast.makeText(getApplicationContext(), product.getSaleItemName(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), EditCartItemActivity.class);
        intent.putExtra("sku",product.getSaleItemSKU());
        intent.putExtra("source","createSale");
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),CreateSaleActivity.class));
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}