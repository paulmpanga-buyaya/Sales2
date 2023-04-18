package sales.management.kiwamirembe.co.ug;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;

public class AddUnitFragment extends DialogFragment {

    public static final String TAG = "add_unit_dialog";
    private Toolbar toolbar;
    DBHelper dbHelper;
    Button button_save_unit;
    TextInputEditText input_unit_name;
    TextInputEditText input_unit_short_code;

    public static AddUnitFragment display(FragmentManager fragmentManager) {
        AddUnitFragment addUnitFragment = new AddUnitFragment();
        addUnitFragment.show(fragmentManager, TAG);
        return addUnitFragment;
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
        View view = inflater.inflate(R.layout.dialog_adding_unit, container, false);

        toolbar = view.findViewById(R.id.adding_unit_toolbar);
        dbHelper = new DBHelper(getActivity());
        button_save_unit = view.findViewById(R.id.button_save_unit);
        input_unit_name = (TextInputEditText) view.findViewById(R.id.input_unit_name);
        input_unit_short_code = (TextInputEditText) view.findViewById(R.id.input_unit_short_code);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle("Adding unit");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        button_save_unit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String unit_name = input_unit_name.getText().toString();
                String unit_short_code = input_unit_short_code.getText().toString();
                dbHelper.addUnit(new Unit(unit_short_code,unit_name,0));
                if (dbHelper.unitExists(unit_name)){
                    dismiss();
                }
            }
        });


        //TextInputEditText input_unit_name
    }

}
