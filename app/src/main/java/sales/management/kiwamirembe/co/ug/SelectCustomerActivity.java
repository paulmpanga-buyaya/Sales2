package sales.management.kiwamirembe.co.ug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SelectCustomerActivity extends AppCompatActivity implements CustomerAdapter.CustomerAdapterListener {

    private RecyclerView recyclerView;
    //private List<Customer> customerList;
    private List<Customer> customerList = new ArrayList<>();
    private CustomerAdapter mAdapter;
    private SearchView searchAvailableCustomers;
    MaterialButton add_customers_from_selection_activity;

    String get_all_customers = "https://kiwamirembe.com/pos2/auth/get-all-customers.php";

    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_customer);
        getSupportActionBar().setTitle(R.string.select_customer_activity_title);
        dbHelper = new DBHelper(this);
        recyclerView = findViewById(R.id.customersSelectionRecyclerView);
        searchAvailableCustomers = findViewById(R.id.searchAvailableCustomers);
        searchItems(searchAvailableCustomers);
        customerList = dbHelper.getCustomers();
        mAdapter = new CustomerAdapter(getApplicationContext(), customerList, this::onCustomerSelected);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new MyDividerItemDecoration(CustomerActivity.this, DividerItemDecoration.VERTICAL, 36));
        recyclerView.setAdapter(mAdapter);
        //getCustomers(get_all_customers);

        add_customers_from_selection_activity = findViewById(R.id.add_customers_from_selection_activity);
        add_customers_from_selection_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getActivity(), CreateCustomerActivity.class));
                Intent intent = new Intent(getApplicationContext(), CreateCustomerActivity.class);
                intent.putExtra("source","select_customers");
                intent.putExtra("action","adding");
                startActivity(intent);
            }
        });

    }

/*    public void getCustomers(String url){
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        customerList.clear();
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            //int server_num_of_rows = jsonObject.getInt("num_of_rows");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            if(success.equals("1")){
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    int customer_id = object.getInt("customer_id");
                                    String customerCode = object.getString("customer_code");
                                    String customerName = object.getString("customer_name");
                                    String customerEmail = object.getString("customer_email");
                                    String customerPhone = object.getString("customer_phone");
                                    String customerAddress = object.getString("customer_address");
                                    String customerStationID = object.getString("registered_by_station_id");
                                    String createdBy = object.getString("created");
                                    int numOfCustomers = object.getInt("num_of_customers");
                                    Customer customer = new Customer(customer_id,customerCode,customerName,customerEmail,customerPhone,1);
                                    customerList.add(customer);
                                    mAdapter.notifyDataSetChanged();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(),"There are no customers",Toast.LENGTH_LONG).show();
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }*/

    public void searchItems(SearchView sv) {
        SearchManager searchManager = (SearchManager)getApplicationContext().getSystemService(Context.SEARCH_SERVICE);
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
    public void onCustomerSelected(Customer customer) {

        dbHelper.updateCurrentTransactionCustomerCode(1,customer.getCustomerCode());
        if ((dbHelper.getCurrentCustomerCode(customer.getCustomerID())).equals("none")){
            Toast.makeText(getApplicationContext(), "Customer code not added",Toast.LENGTH_SHORT).show();
        } else {
            String source = getIntent().getExtras().getString("source");
            if (source.equals("create_sale_activity")){
                Intent intent = new Intent(getApplicationContext(), CreateSaleActivity.class);
                startActivity(intent);
            } else if (source.equals("cart_activity")){
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }

        }
    }
}