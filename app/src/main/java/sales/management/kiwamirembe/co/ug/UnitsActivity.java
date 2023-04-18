package sales.management.kiwamirembe.co.ug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class UnitsActivity extends BaseActivity implements UnitsAdapter.UnitAdapterListener {

    DBHelper dbHelper;
    MaterialButton add_unit;

    private RecyclerView unitsRecyclerView;
    private List<Unit> unitList;
    private UnitsAdapter unitsAdapter;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_units);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Units");
        dbHelper = new DBHelper(this);

        unitsRecyclerView = findViewById(R.id.unitsRecycler);
        searchView = findViewById(R.id.searchUnits);
        searchUnits(searchView);
        unitList = dbHelper.getUnits();
        unitsAdapter = new UnitsAdapter(UnitsActivity.this, unitList, this::onUnitSelected);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        unitsRecyclerView.setLayoutManager(mLayoutManager);
        unitsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        unitsRecyclerView.setAdapter(unitsAdapter);

        add_unit = findViewById(R.id.add_unit);
        add_unit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUnitDialog();
            }
        });

    }

    public void addUnitDialog(){
        Bundle bundle = new Bundle();
        bundle.putString("title","Adding unit");
        AddUnitFragment addUnitFragment = new AddUnitFragment();
        addUnitFragment.setArguments(bundle);
        addUnitFragment.display(getSupportFragmentManager());

    }

    public void searchUnits(SearchView sv) {
        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        sv.setMaxWidth(Integer.MAX_VALUE);
        // listening to search query text change
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                unitsAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                unitsAdapter.getFilter().filter(query);
                return false;
            }
        });
    }

    @Override
    public void onUnitSelected(Unit unit) {
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