package sales.management.kiwamirembe.co.ug;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UnitsAdapter extends RecyclerView.Adapter<UnitsAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private List<Unit> unitList;
    private List<Unit> unitListFiltered;
    private UnitAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, truckID;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onUnitSelected(unitListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public UnitsAdapter(Context context, List<Unit> unitList, UnitAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.unitList = unitList;
        this.unitListFiltered = unitList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_units, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Unit unit = unitListFiltered.get(position);
        holder.name.setText(unit.getUnit_name());
        //holder.truckID.setText(String.valueOf(route.getTruckID()));
    }

    @Override
    public int getItemCount() {
        return unitListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    unitListFiltered = unitList;
                } else {
                    List<Unit> filteredList = new ArrayList<>();
                    for (Unit row : unitList) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getUnit_name().toLowerCase().contains(charString.toLowerCase()) ||
                                row.getUnit_code().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    unitListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = unitListFiltered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                unitListFiltered = (ArrayList<Unit>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface UnitAdapterListener {
        void onUnitSelected(Unit unit);
    }

}
