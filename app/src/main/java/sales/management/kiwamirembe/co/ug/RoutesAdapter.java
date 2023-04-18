package sales.management.kiwamirembe.co.ug;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class RoutesAdapter extends RecyclerView.Adapter<RoutesAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private List<Route> routeList;
    private List<Route> routeListFiltered;
    private RouteAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, truckID;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            truckID = view.findViewById(R.id.truckID);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onRouteSelected(routeListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public RoutesAdapter(Context context, List<Route> routeList, RouteAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.routeList = routeList;
        this.routeListFiltered = routeList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_routes, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Route route = routeListFiltered.get(position);
        holder.name.setText(route.getRouteName());
        holder.truckID.setText(String.valueOf(route.getTruckID()));
    }

    @Override
    public int getItemCount() {
        return routeListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    routeListFiltered = routeList;
                } else {
                    List<Route> filteredList = new ArrayList<>();
                    for (Route row : routeList) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getRouteName().toLowerCase().contains(charString.toLowerCase()) ||
                                row.getTruckID().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    routeListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = routeListFiltered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                routeListFiltered = (ArrayList<Route>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface RouteAdapterListener {
        void onRouteSelected(Route route);
    }

}
