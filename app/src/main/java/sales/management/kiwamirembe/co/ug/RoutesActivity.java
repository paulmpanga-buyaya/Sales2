package sales.management.kiwamirembe.co.ug;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class RoutesActivity extends BaseActivity implements RoutesAdapter.RouteAdapterListener {

    DBHelper dbHelper;
    MaterialButton add_route;

    private RecyclerView routesRecyclerView;
    private List<Route> routeList;
    private RoutesAdapter routesAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Routes");
        dbHelper = new DBHelper(this);

        routesRecyclerView = findViewById(R.id.routesRecycler);
        searchView = findViewById(R.id.searchRoutes);
        searchRoutes(searchView);
        routeList = dbHelper.getRoutes();
        routesAdapter = new RoutesAdapter(RoutesActivity.this, routeList, this::onRouteSelected);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        routesRecyclerView.setLayoutManager(mLayoutManager);
        routesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        routesRecyclerView.setAdapter(routesAdapter);

        add_route = findViewById(R.id.add_route);
        add_route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRouteDialog();
            }
        });
    }

    public void addRouteDialog(){
        Bundle bundle = new Bundle();
        bundle.putString("title","Adding route");
        AddRouteFragment addRouteFragment = new AddRouteFragment();
        addRouteFragment.setArguments(bundle);
        addRouteFragment.display(getSupportFragmentManager());

    }

    public void searchRoutes(SearchView sv) {
        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        sv.setMaxWidth(Integer.MAX_VALUE);
        // listening to search query text change
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                routesAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                routesAdapter.getFilter().filter(query);
                return false;
            }
        });
    }

    @Override
    public void onRouteSelected(Route route) {
        //Toast.makeText(getApplicationContext(), product.getSaleItemName(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("start","settings");
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}