package sales.management.kiwamirembe.co.ug;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class HomeFragment extends Fragment {

    DBHelper dbHelper;

    Button createSale;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_home, container, false);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        dbHelper = new DBHelper(getActivity());

        int sale_trans_count = dbHelper.numberOfRows(DBHelper.sale_transactions_table);
        int sale_trans_items_count = dbHelper.numberOfRows(DBHelper.sale_transactions_items_table);

        createSale = root.findViewById(R.id.createSale);
        createSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CreateSaleActivity.class));

                //Toast.makeText(getActivity(), String.valueOf(sale_trans_count) + " " + String.valueOf(sale_trans_items_count),Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}