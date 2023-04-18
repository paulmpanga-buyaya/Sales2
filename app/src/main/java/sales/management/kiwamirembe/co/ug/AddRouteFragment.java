package sales.management.kiwamirembe.co.ug;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class AddRouteFragment extends DialogFragment {

    public static final String TAG = "add_route_dialog";
    private Toolbar toolbar;
    DBHelper dbHelper;
    Button button_save_route;
    TextInputEditText input_route_name;

    public static AddRouteFragment display(FragmentManager fragmentManager) {
        AddRouteFragment addRouteFragment = new AddRouteFragment();
        addRouteFragment.show(fragmentManager, TAG);
        return addRouteFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().setWindowAnimations(R.style.AppTheme_Slide);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_adding_route, container, false);

        toolbar = view.findViewById(R.id.adding_route_toolbar);
        dbHelper = new DBHelper(getActivity());
        button_save_route = view.findViewById(R.id.button_save_route);
        input_route_name = (TextInputEditText) view.findViewById(R.id.input_route_name);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle("Adding route");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        button_save_route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String route_name = input_route_name.getText().toString();
                dbHelper.addRoute(new Route("UAX111A",route_name,0));
                if (dbHelper.routeExists(route_name)){
                    dismiss();
                }
            }
        });

    }
}