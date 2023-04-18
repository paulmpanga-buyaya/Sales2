package sales.management.kiwamirembe.co.ug;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private List<Product> productList;
    private List<Product> productListFiltered;
    private ProductAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price, code;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            code = view.findViewById(R.id.code);
            price = view.findViewById(R.id.price);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //listener.onItemSelected(productListFiltered.get(getAdapterPosition()));
                    if (view.getContext() instanceof MainActivity) {
                        //itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_layout, parent, false);
                        listener.onProductSelected(productListFiltered.get(getAdapterPosition()),1);
                    } else {
                        //listener.onProductSelected(productListFiltered.get(getAdapterPosition()));
                        PopupMenu popup = new PopupMenu(context, view);
                        //Inflating the Popup using xml file
                        popup.getMenuInflater().inflate(R.menu.product_popup_menu, popup.getMenu());
                        //registering popup with OnMenuItemClickListener
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                //Toast.makeText(context,"You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                                if ((menuItem.getTitle()).equals("Add stock")){
                                    //listener.onItemSelected(productListFiltered.get(getAdapterPosition()),1);
                                    listener.onProductSelected(productListFiltered.get(getAdapterPosition()),1001);
                                } else if ((menuItem.getTitle()).equals("View")){

                                    //listener.onItemSelected(productListFiltered.get(getAdapterPosition()),1);

                                 /*   Intent intent = new Intent(context, AddProductActivity.class);
                                    intent.putExtra("source","products_and_inventory");
                                    intent.putExtra("action","viewing");
                                    intent.putExtra("sku",productListFiltered.get(getAdapterPosition()).getItemSKU());
                                    context.startActivity(intent);*/

                                }else if ((menuItem.getTitle()).equals("Change price")){

                                }else if ((menuItem.getTitle()).equals("Edit")){
/*
                                    Intent intent = new Intent(context, AddProductActivity.class);
                                    intent.putExtra("source","products_and_inventory");
                                    intent.putExtra("action","editing");
                                    intent.putExtra("sku",productListFiltered.get(getAdapterPosition()).getItemSKU());
                                    context.startActivity(intent);*/

                                }
                                return true;
                            }
                        });

                        popup.show();//showing popup menu*/
                    }
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return false;
                }
            });
        }
    }

    public ProductAdapter(Context context, List<Product> productList, ProductAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.productList = productList;
        this.productListFiltered = productList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_row_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Product product = productListFiltered.get(position);
        holder.name.setText(product.getProduct_name());
        holder.code.setText(product.getProduct_sku());
        holder.price.setText("Price : " + " " +String.valueOf(product.getProduct_unit_price()));
    }

    @Override
    public int getItemCount() {
        return productListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    productListFiltered = productList;
                } else {
                    List<Product> filteredList = new ArrayList<>();
                    for (Product row : productList) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        /*if (row.getItemDescription().toLowerCase().contains(charString.toLowerCase()) || row.getItemName().toLowerCase().contains(charString.toLowerCase()) ||
                                row.getItemSKU().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }*/

                        if ( row.getProduct_name().toLowerCase().contains(charString.toLowerCase()) ||
                                row.getProduct_sku().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }

                    }
                    productListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = productListFiltered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                productListFiltered = (ArrayList<Product>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ProductAdapterListener {
        void onProductSelected(Product product,int count);
    }

}
