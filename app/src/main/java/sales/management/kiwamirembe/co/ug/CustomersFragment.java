package sales.management.kiwamirembe.co.ug;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class CustomersFragment extends Fragment implements CustomerAdapter.CustomerAdapterListener {

    DBHelper dbHelper;

    private RecyclerView recyclerView;
    //private List<Customer> customerList;
    private List<Customer> customerList = new ArrayList<>();
    private CustomerAdapter mAdapter;
    private SearchView searchView;
    MaterialButton add_customers_from_fragment;

    String get_all_customers = "https://kiwamirembe.com/pos2/auth/get-all-customers.php";

    public CustomersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_customers, container, false);
        View root = inflater.inflate(R.layout.fragment_customers, container, false);
        dbHelper = new DBHelper(getActivity());
        recyclerView = root.findViewById(R.id.customersRecyclerView);
        searchView = root.findViewById(R.id.searchCustomers);
        searchItems(searchView);
        customerList = dbHelper.getCustomers();
        mAdapter = new CustomerAdapter(getActivity(), customerList, this::onCustomerSelected);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new MyDividerItemDecoration(CustomerActivity.this, DividerItemDecoration.VERTICAL, 36));
        recyclerView.setAdapter(mAdapter);
        //getCustomers(get_all_customers);

        add_customers_from_fragment = root.findViewById(R.id.add_customers_from_fragment);
        add_customers_from_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getActivity(), CreateCustomerActivity.class));
                Intent intent = new Intent(getActivity(), CreateCustomerActivity.class);
                intent.putExtra("source","customers");
                intent.putExtra("action","adding");
                startActivity(intent);
            }
        });

        return root;
    }

    /*public void getCustomers(String url){
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
                                Toast.makeText(getActivity(),"There are no customers",Toast.LENGTH_LONG).show();
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }*/

    public void searchItems(SearchView sv) {
        SearchManager searchManager = (SearchManager)getActivity().getSystemService(Context.SEARCH_SERVICE);
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

    public void startAddCustomerActivity(){
        Intent intent = new Intent(getActivity(), CreateCustomerActivity.class);
        intent.putExtra("source","customers_fragment");
        intent.putExtra("action","adding");
        startActivity(intent);
    }

    @Override
    public void onCustomerSelected(Customer customer) {
    }
}