package sales.management.kiwamirembe.co.ug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

public class PaymentActivity extends BaseActivity {

    LinearLayout transactionDate, startPaymentDialog, goToInvoices;
    private RecyclerView recyclerView;
    private List<SaleItem> saleItemList;
    private SaleItemAdapter mAdapter;
    private CustomerAdapter mCustomerAdapter;
    private SearchView searchView;
    DBHelper dbHelper;

    EditText amountTendered, dateTxt;
    TextView totalSumText;

    MaterialButton paymentCash, paymentCredit, paymentCheque;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        dbHelper = new DBHelper(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Payment");

        CurrentTransactionDetails currentTransactionDetails = dbHelper.getCurrentTransactionDetails(1);
        Customer customer = dbHelper.getCustomerWithCode(currentTransactionDetails.getCurrentTransactionCustomerCode());

        DecimalFormat formatter = new DecimalFormat("#,###.00");

        totalSumText = findViewById(R.id.totalSumText);
        totalSumText.setText(String.valueOf(formatter.format(dbHelper.getTotal())));

        amountTendered = findViewById(R.id.amountTendered);
        amountTendered.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //String amountT = amountTendered.getText().toString();
                //float f_amountT = Float.parseFloat(amountT);
                //amountTendered.setText(String.valueOf(formatter.format(f_amountT)));
            }

            @Override
            public void afterTextChanged(Editable editable) {
                /*String amountT = amountTendered.getText().toString();
                float f_amountT = Float.parseFloat(amountT);
                amountTendered.setText(String.valueOf(formatter.format(f_amountT)));*/
            }
        });

        paymentCash = findViewById(R.id.paymentCash);
        paymentCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                withCash();
            }
        });
        paymentCredit = findViewById(R.id.paymentCredit);
        paymentCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                withCredit();
            }
        });
        paymentCheque = findViewById(R.id.paymentCheque);

        saleItemList = dbHelper.getSalesItems();

        dateTxt = findViewById(R.id.dateTxt);
        String transaction_date = getIntent().getExtras().getString("date");
        dateTxt.setText(transaction_date);
        //setDate(dateTxt,PaymentActivity.this);

        hideKeyboard();
    }

    public void withCash(){
        Bundle bundle = getIntent().getExtras();
        String trans_date = bundle.getString("date", "0");

        String tendered = amountTendered.getText().toString();
        if (tendered.equals("")){
            //Toast.makeText(getApplicationContext(), "No amount has been tendered",Toast.LENGTH_LONG).show();
            createAlert("Cash Payment","tendered amount is blank",PaymentActivity.this);
        } else {
            double tendered_num = Double.parseDouble(tendered);
            double total_num = dbHelper.getTotal();
            double balance_change = tendered_num - total_num;

            if (tendered_num < total_num){
                createAlert("Cash Payment","tendered amount is not enough",PaymentActivity.this);
            } else {

                for (int counter = 0; counter < saleItemList.size(); counter++) {
                    //Toast.makeText(getApplicationContext(),String.valueOf(saleItemList.get(counter).saleItemName),Toast.LENGTH_SHORT).show();
                    //InvoiceItem(String invoiceItemSKU, String invoiceItemName, double invoiceItemPrice, int invoiceItemQuantity, double invoiceItemDiscount, double invoiceItemTotal)
                    CurrentTransactionDetails currentTransactionInfo = dbHelper.getCurrentTransactionDetails(1);
                    int current_stock = dbHelper.getProductStock(saleItemList.get(counter).getSaleItemSKU());
                    int stock_to_be_sold = saleItemList.get(counter).getSaleItemQuantity();
                    int new_stock = current_stock - stock_to_be_sold;
                    dbHelper.updateInventoryStock(saleItemList.get(counter).getSaleItemSKU(),new_stock);
                    //SaleTransactionItem(String saleTransactionIdentifier, String saleTransactionItemName, String saleTransactionItemSKU, float saleTransactionItemPrice, int saleTransactionItemQuantity, float saleTransactionItemTotal, int saleTransactionItemSyncStatus)
                    dbHelper.addSaleTransactionItem(new SaleTransactionItem(currentTransactionInfo.getCurrentTransactionIdentifier(),saleItemList.get(counter).saleItemName,saleItemList.get(counter).saleItemSKU,saleItemList.get(counter).saleItemPrice,saleItemList.get(counter).saleItemQuantity,saleItemList.get(counter).saleItemTotal,0));
                }
                String date = dateTxt.getText().toString();
                CurrentTransactionDetails currentTransactionInfo = dbHelper.getCurrentTransactionDetails(1);
                ///SaleTransaction(int saleTransactionID, String customerCode, String saleTransactionIdentifier, float saleTransactionTotal, long saleTransactionDate, int saleTransactionSyncStatus)
                dbHelper.addSaleTransaction(new SaleTransaction(currentTransactionInfo.getCurrentTransactionCustomerCode(),currentTransactionInfo.getCurrentTransactionIdentifier(),dbHelper.getTotal(),dateLong(trans_date),1,0));
                //Payment(String paymentCode, String saleTransactionIdentifier, String paymentType, float paymentAmount, long paymentDate)
                String uuid = UUID.randomUUID().toString();
                String pay_code = uuid.replaceAll("\\D", "");
                dbHelper.addPayment(new Payment(pay_code,currentTransactionInfo.getCurrentTransactionIdentifier(),"cash",dbHelper.getTotal(),dateLong(trans_date)));
                dbHelper.delete(DBHelper.sale_items_table);
                dbHelper.delete(DBHelper.current_transaction_details_table);
                //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("start","home");
                intent.putExtra("total",String.valueOf(dbHelper.getTotal()));
                intent.putExtra("balance_change",String.valueOf(balance_change));
                startActivity(intent);

            }
        }

    }

    public void withCredit(){
        Bundle bundle = getIntent().getExtras();
        String trans_date = bundle.getString("date", "0");
        double total_num = dbHelper.getTotal();

        for (int counter = 0; counter < saleItemList.size(); counter++) {
            //Toast.makeText(getApplicationContext(),String.valueOf(saleItemList.get(counter).saleItemName),Toast.LENGTH_SHORT).show();
            //InvoiceItem(String invoiceItemSKU, String invoiceItemName, double invoiceItemPrice, int invoiceItemQuantity, double invoiceItemDiscount, double invoiceItemTotal)
            CurrentTransactionDetails currentTransactionInfo = dbHelper.getCurrentTransactionDetails(1);
            int current_stock = dbHelper.getProductStock(saleItemList.get(counter).getSaleItemSKU());
            int stock_to_be_sold = saleItemList.get(counter).getSaleItemQuantity();
            int new_stock = current_stock - stock_to_be_sold;
            dbHelper.updateInventoryStock(saleItemList.get(counter).getSaleItemSKU(),new_stock);
            //SaleTransactionItem(String saleTransactionIdentifier, String saleTransactionItemName, String saleTransactionItemSKU, float saleTransactionItemPrice, int saleTransactionItemQuantity, float saleTransactionItemTotal, int saleTransactionItemSyncStatus)
            dbHelper.addSaleTransactionItem(new SaleTransactionItem(currentTransactionInfo.getCurrentTransactionIdentifier(),saleItemList.get(counter).saleItemName,saleItemList.get(counter).saleItemSKU,saleItemList.get(counter).saleItemPrice,saleItemList.get(counter).saleItemQuantity,saleItemList.get(counter).saleItemTotal,0));
        }
        String date = dateTxt.getText().toString();
        CurrentTransactionDetails currentTransactionInfo = dbHelper.getCurrentTransactionDetails(1);
        ///SaleTransaction(int saleTransactionID, String customerCode, String saleTransactionIdentifier, float saleTransactionTotal, long saleTransactionDate, int saleTransactionSyncStatus)
        dbHelper.addSaleTransaction(new SaleTransaction(currentTransactionInfo.getCurrentTransactionCustomerCode(),currentTransactionInfo.getCurrentTransactionIdentifier(),dbHelper.getTotal(),dateLong(trans_date),0,0));
        dbHelper.delete(DBHelper.sale_items_table);
        dbHelper.delete(DBHelper.current_transaction_details_table);
        //startActivity(new Intent(getApplicationContext(), MainActivity.class));
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("start","home");
        intent.putExtra("total",String.valueOf(dbHelper.getTotal()));
        intent.putExtra("balance_change",String.valueOf(0));
        startActivity(intent);
    }

    public void withCheck(){

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),CartActivity.class));
        finish();
        return;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}