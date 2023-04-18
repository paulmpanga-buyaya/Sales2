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

public class SaleItemAdapter extends RecyclerView.Adapter<SaleItemAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private List<SaleItem> saleItemList;
    private List<SaleItem> saleItemListFiltered;
    private SaleItemAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price_and_quantity, total;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            total = view.findViewById(R.id.total);
            price_and_quantity = view.findViewById(R.id.price_and_quantity);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onSaleItemSelected(saleItemListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public SaleItemAdapter(Context context, List<SaleItem> saleItemList, SaleItemAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.saleItemList = saleItemList;
        this.saleItemListFiltered = saleItemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sale_item_row_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final SaleItem product = saleItemListFiltered.get(position);
        holder.name.setText(product.getSaleItemName());
        //holder.total.setText(String.valueOf(product.getSaleItemTotal()));
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        holder.total.setText(String.valueOf(formatter.format(product.getSaleItemTotal())));
        holder.price_and_quantity.setText("Price : " + " " +String.valueOf(product.getSaleItemPrice())+" * "+ String.valueOf(product.getSaleItemQuantity()));

    }

    @Override
    public int getItemCount() {
        return saleItemListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    saleItemListFiltered = saleItemList;
                } else {
                    List<SaleItem> filteredList = new ArrayList<>();
                    for (SaleItem row : saleItemList) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getSaleItemName().toLowerCase().contains(charString.toLowerCase()) ||
                                row.getSaleItemSKU().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    saleItemListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = saleItemListFiltered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                saleItemListFiltered = (ArrayList<SaleItem>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface SaleItemAdapterListener {
        void onSaleItemSelected(SaleItem product);
    }

}
