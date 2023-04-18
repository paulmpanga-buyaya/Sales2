package sales.management.kiwamirembe.co.ug;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CreateCustomerActivity extends BaseActivity {

    DBHelper dbHelper;
    TextInputEditText input_customer_first_name,input_customer_last_name,input_customer_email,input_customer_phone_number;
    Button button_save_customer;

    String register_customer = "https://kiwamirembe.com/pos2/auth/register_customer.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_customer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        dbHelper = new DBHelper(this);

        String source = getIntent().getExtras().getString("source");
        String action = getIntent().getExtras().getString("action");
        if (source.equals("customers") && action.equals("adding")){
            getSupportActionBar().setTitle(R.string.add_customer_activity_title_adding_customer);
        }else if (source.equals("customer_activity") && action.equals("viewing")) {
            getSupportActionBar().setTitle(R.string.add_customer_activity_title_viewing_customer);
        } else if (source.equals("select_customers") && action.equals("adding")){
            getSupportActionBar().setTitle(R.string.add_customer_activity_title_adding_customer);
        }

        button_save_customer = findViewById(R.id.button_save_customer);
        button_save_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_customer_first_name = findViewById(R.id.input_customer_first_name);
                String first_name = input_customer_first_name.getText().toString();
                input_customer_last_name = findViewById(R.id.input_customer_last_name);
                String last_name = input_customer_last_name.getText().toString();
                input_customer_email = findViewById(R.id.input_customer_email);
                String email = input_customer_email.getText().toString();
                input_customer_phone_number = findViewById(R.id.input_customer_phone_number);
                String phone_number = input_customer_phone_number.getText().toString();
                String uuid = UUID.randomUUID().toString();
                String cust_code = uuid.replaceAll("\\D", "");
                //String uuid = "werhsd";
                String name = first_name + " " + last_name;
                registerCustomer(cust_code,name,phone_number,email,"000","admin");
                // Customer(String customerCode, String customerName, String customerEmail, String customerPhone)
               /* dbHelper.addCustomer(new Customer(uuid,name,email,phone_number));
                if (dbHelper.customerExists(uuid)){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("start","customers");
                    startActivity(intent);
                }*/
            }
        });
    }

    public void registerCustomer(String customer_code, String customer_name, String customer_phone, String customer_email, String customer_address, String registered_by_station_id) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, register_customer, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String message = jsonObject.getString("message");
                    String success = jsonObject.optString("success");
                    if (success.equals("1")){
                        //createAlert("Registered",message,CreateCustomerActivity.this);
                        progressDialog.dismiss();
                        dbHelper.addCustomer(new Customer(customer_code,customer_name,customer_email,customer_phone,1));

                        if (dbHelper.customerExists(customer_code)){
                            createAlert("Registered Online and lccally",message + " customer registered online and locally",CreateCustomerActivity.this);
                        } else {
                            createAlert("NOT registered", message + " customer registered online only",CreateCustomerActivity.this);
                        }

                    } else {
                        createAlert("Not Registered ",message,CreateCustomerActivity.this);
                        /*progressDialog.dismiss();
                        //Toast.makeText(CreateCustomerActivity.this,success, Toast.LENGTH_SHORT).show();
                        dbHelper.addCustomer(new Customer(customer_code,customer_name,customer_email,customer_phone,0));
                        if (dbHelper.customerExists(customer_code)){
                            createAlert("NOT registered","Customer has been registered locally only",CreateCustomerActivity.this);
                            *//*Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("start","customers");
                            startActivity(intent);*//*
                        } else {

                            createAlert("NOT registered","Customer has not been registered online or locally",CreateCustomerActivity.this);
                        }*/
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(CreateCustomerActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("customer_code", customer_code);
                params.put("customer_name", customer_name);
                params.put("customer_phone", customer_phone);
                params.put("customer_email", customer_email);
                params.put("customer_address", customer_address);
                params.put("registered_by_station_id", registered_by_station_id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(CreateCustomerActivity.this);
        requestQueue.add(request);
    }

    @Override
    public void onBackPressed() {

        String source = getIntent().getExtras().getString("source");
        String action = getIntent().getExtras().getString("action");

        if (source.equals("customers") && action.equals("adding")){

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("start","customers");
            startActivity(intent);

        }else if (source.equals("customer_activity") && action.equals("viewing")) {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("start","customers");
            startActivity(intent);

        } else if (source.equals("select_customers") && action.equals("adding")){
            //Ideally we should send the id of the newly created customer back to the activity
            Intent intent = new Intent(getApplicationContext(), SelectCustomerActivity.class);
            intent.putExtra("start","customers");
            startActivity(intent);

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}