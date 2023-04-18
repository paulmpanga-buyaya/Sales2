package sales.management.kiwamirembe.co.ug;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class EditCartItemActivity extends BaseActivity {

    TextView nameText, stockText;
    EditText priceText, quantityText, totalText, dateText;
    MaterialButton button_submit;
    DBHelper dbHelper;

    private ArrayList<HashMap<String, Object>> customItemArrayList;
    private ListView currentSaleList;
    //private CustomItemAdapter customItemAdapter;

    private int maxRowId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cart_item);
        dbHelper = new DBHelper(this);
        String sku = getIntent().getExtras().getString("sku","sku");
        String source = getIntent().getExtras().getString("source","sku");
        int inventory_stock = dbHelper.getInventory(sku);

        if (source.equals("createSale")){

            SaleItem saleItem = dbHelper.getSaleItemWithSKU(sku);
            dateText = findViewById(R.id.date);
            //setDate(dateText,EditItemActivity.this);
            nameText = findViewById(R.id.itemName);
            nameText.setText(saleItem.getSaleItemName() + " . " + inventory_stock + " available" );
            stockText = findViewById(R.id.stock);
            stockText.setVisibility(View.GONE);
            stockText.setText("Available: " + String.valueOf(saleItem.getSaleItemQuantity()));
            priceText = findViewById(R.id.itemPrice);
            priceText.setText(String.valueOf(saleItem.getSaleItemPrice()));
            totalText = findViewById(R.id.totalAmount);
            quantityText = findViewById(R.id.itemQuantity);
            quantityText.setText(String.valueOf(saleItem.getSaleItemQuantity()));
            //totalText.setText(String.valueOf(saleItem.getSaleItemTotal()));
            DecimalFormat formatter = new DecimalFormat("#,###.00");
            totalText.setText(String.valueOf(formatter.format(saleItem.getSaleItemTotal())));

            //updateSaleItemPrice
            priceText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String ps = priceText.getText().toString();
                    String qs = quantityText.getText().toString();
                    if (ps.equals("")){
                        int quantity = Integer.parseInt(qs);
                        float price = 0;
                        float total = quantity * price;
                        totalText.setText(String.valueOf(formatter.format(total)));
                    } else {
                        int quantity = Integer.parseInt(quantityText.getText().toString());
                        float price = Float.parseFloat(ps);//saleItem.getSaleItemPrice();
                        float total = quantity * price;
                        totalText.setText(String.valueOf(formatter.format(total)));
                    }

                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            quantityText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String qs = quantityText.getText().toString();
                    if (qs.equals("")){
                        int quantity = 0;
                        double price = saleItem.getSaleItemPrice();
                        double total = quantity * price;
                        totalText.setText(String.valueOf(formatter.format(total)));
                        //priceText.setText(String.valueOf(saleItem.getSaleItemPrice()));
                    } else {

                        int ent_quant = Integer.parseInt(qs);
                        if (ent_quant > inventory_stock){
                            createAlert("STOCK ALERT!!", "Only " + inventory_stock + " pieces available",EditCartItemActivity.this);
                        } else {
                            int quantity = Integer.parseInt(quantityText.getText().toString());
                            double price = saleItem.getSaleItemPrice();
                            double total = quantity * price;
                            totalText.setText(String.valueOf(formatter.format(total)));
                        }

                    }
                   /* if (qs.equals("")){
                        int quantity = 0;
                        double price = saleItem.getSaleItemPrice();
                        double total = quantity * price;
                        totalText.setText(String.valueOf(formatter.format(total)));
                        //priceText.setText(String.valueOf(saleItem.getSaleItemPrice()));
                    } else {
                        int quantity = Integer.parseInt(quantityText.getText().toString());
                        double price = saleItem.getSaleItemPrice();
                        double total = quantity * price;
                        totalText.setText(String.valueOf(formatter.format(total)));
                    }*/

                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            button_submit = findViewById(R.id.button_submit);
            button_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    double o_price = saleItem.getSaleItemPrice();
                    double c_price = Double.parseDouble(priceText.getText().toString());
                    int quantity = Integer.parseInt(quantityText.getText().toString());
                    if (o_price == c_price){
                        double price = saleItem.getSaleItemPrice();
                        double total = quantity * price;
                        dbHelper.updateSaleItemQuantity(saleItem.getSaleItemSKU(),quantity);
                        dbHelper.updateSaleItemTotal(saleItem.getSaleItemSKU(),total);
                        startActivity(new Intent(getApplicationContext(), CartActivity.class));
                    } else {
                        double price = c_price;
                        double total = quantity * price;
                        dbHelper.updateSaleItemPrice(saleItem.getSaleItemSKU(),c_price);
                        dbHelper.updateSaleItemQuantity(saleItem.getSaleItemSKU(),quantity);
                        dbHelper.updateSaleItemTotal(saleItem.getSaleItemSKU(),total);
                        Toast.makeText(getApplicationContext(), "Price has changed",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), CartActivity.class));
                    }
                }
            });

        }
    }
}