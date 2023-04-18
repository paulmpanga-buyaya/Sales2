package sales.management.kiwamirembe.co.ug;

import android.app.ListActivity;
import android.content.Intent;
import android.hardware.lights.LightsManager;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SettingsFragment extends Fragment {

    String[] controls = {
            "Units",
            "Routes"
    };

    TextView textView;
    ListView listView;

    Toolbar toolbar;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_settings, container, false);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        toolbar = getActivity().findViewById(R.id.main_toolbar);
        //toolbar.setVisibility(View.GONE);

       // loadSettingsSelectionFragment();

        //setListAdapter(new SettingsSelectionAdapter(this, MOBILE_OS));


        listView = (ListView)root.findViewById(R.id.listView);
        //textView = (TextView)root.findViewById(R.id.textView);

        final ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, controls);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub

                //Toast.makeText(getActivity(), value, Toast.LENGTH_SHORT).show();
                String value = String.valueOf(adapter.getItem(position));
                if (value.equals("Units")){
                    //((MainActivity) getActivity()).loadUnitsFragment();
                    startActivity(new Intent(getActivity(), UnitsActivity.class));
                } else if (value.equals("Routes")){
                    //((MainActivity) getActivity()).loadRoutesFragment();
                    startActivity(new Intent(getActivity(), RoutesActivity.class));
                }
            }
        });


        return root;
    }

  /*  public void loadSettingsSelectionFragment(){
        toolbar.setTitle("Settings");
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.settings_container, new SettingsSelectionFragment());
        ft.commit();
    }
*/
}