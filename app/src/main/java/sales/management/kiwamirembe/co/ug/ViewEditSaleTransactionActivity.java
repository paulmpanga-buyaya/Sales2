package sales.management.kiwamirembe.co.ug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewEditSaleTransactionActivity extends BaseActivity implements SaleItemAdapter.SaleItemAdapterListener {

    LinearLayout transactionDate, startPaymentDialog, goToInvoices;
    private RecyclerView recyclerView;
    private List<SaleItem> saleItemList;
    private SaleItemAdapter mAdapter;
    private CustomerAdapter mCustomerAdapter;
    private SearchView searchView;
    DBHelper dbHelper;

    EditText transaction_dateTxt,transaction_customer_name,transactionAmount;
    TextView amount;
    MaterialButton pay;

    private List<Customer> customerList = new ArrayList<>();

    private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_edit_sale_transaction);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Transaction summary");
        dbHelper = new DBHelper(this);

        String identifier = getIntent().getExtras().getString("identifier");
        //int sales_transaction = getIntent().getExtras().getString("identifier");customerCode;transactionTotal
        String customerCode = getIntent().getExtras().getString("customerCode");
        String transactionTotal = getIntent().getExtras().getString("transactionTotal");
        String transaction_date = getIntent().getExtras().getString("transactionDate");

        recyclerView = findViewById(R.id.productsTransactionsRecyclerView);
        saleItemList = dbHelper.getSalesItemsWithIdentifier(identifier);
        mAdapter = new SaleItemAdapter(ViewEditSaleTransactionActivity.this, saleItemList, this::onSaleItemSelected);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ViewEditSaleTransactionActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(ViewEditSaleTransactionActivity.this, DividerItemDecoration.VERTICAL, 36));
        recyclerView.setAdapter(mAdapter);

        transactionDate = findViewById(R.id.transactionDate);
        transaction_dateTxt = findViewById(R.id.transaction_dateTxt);
        final DateFormat dateFormatter = SimpleDateFormat.getDateInstance();
        transaction_dateTxt.setText(dateFormatter.format(new Date(Long.parseLong(transaction_date))));

        Customer customer = dbHelper.getCustomerWithCode(customerCode);
        transaction_customer_name = findViewById(R.id.transaction_customer_name);
        transaction_customer_name.setText(customer.getCustomerName());

        transactionAmount = findViewById(R.id.transactionAmount);
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        transactionAmount.setText(String.valueOf(formatter.format(Float.parseFloat(transactionTotal))));
    }

    @Override
    public void onSaleItemSelected(SaleItem product) {
        //Toast.makeText(getApplicationContext(), product.getSaleItemName(),Toast.LENGTH_SHORT).show();
       /* Intent intent = new Intent(getApplicationContext(), EditCartItemActivity.class);
        intent.putExtra("sku",product.getSaleItemSKU());
        intent.putExtra("source","createSale");
        startActivity(intent);*/
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("start","receipts");
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}