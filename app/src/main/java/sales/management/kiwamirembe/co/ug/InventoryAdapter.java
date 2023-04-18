package sales.management.kiwamirembe.co.ug;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private List<Inventory> inventoryList;
    private List<Inventory> inventoryListFiltered;
    private InventoryAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price, stock;
        public ImageView image;
        public MaterialCardView cardView;
        public LinearLayout cardLinear;

        public MyViewHolder(View view) {
            super(view);
            //image = view.findViewById(R.id.image);
            name = view.findViewById(R.id.name);
            price = view.findViewById(R.id.price);
            stock = view.findViewById(R.id.stock);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onInventorySelected(inventoryListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public InventoryAdapter(Context context, List<Inventory> inventoryList, InventoryAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.inventoryList = inventoryList;
        this.inventoryListFiltered = inventoryList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_products_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Inventory inventory = inventoryListFiltered.get(position);
        DBHelper dbHelper = new DBHelper(context);
        Product product = dbHelper.getProductWithSKU(inventory.getProductSKU());
        SaleItem saleItem = dbHelper.getSaleItemWithSKU(inventory.getProductSKU());
        int product_stock = inventory.getStock();
        int sale_item_quant = saleItem.getSaleItemQuantity();
        int current_stock = product_stock - sale_item_quant;
        holder.name.setText(product.getProduct_name());
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        holder.price.setText(String.valueOf(formatter.format(product.getProduct_unit_price())));
        //holder.stock.setText(String.valueOf(inventory.getStock()));
        holder.stock.setText(String.valueOf(current_stock));
    }

    @Override
    public int getItemCount() {
        return inventoryListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    inventoryListFiltered = inventoryList;
                } else {
                    List<Inventory> filteredList = new ArrayList<>();
                    for (Inventory row : inventoryList) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getProductSKU().toLowerCase().contains(charString.toLowerCase()) ||
                                String.valueOf(row.getStock()).toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    inventoryListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = inventoryListFiltered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                inventoryListFiltered = (ArrayList<Inventory>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface InventoryAdapterListener {
        void onInventorySelected(Inventory inventory);
    }

}
