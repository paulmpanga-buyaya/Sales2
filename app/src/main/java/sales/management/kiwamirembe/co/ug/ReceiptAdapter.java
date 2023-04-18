package sales.management.kiwamirembe.co.ug;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.MyViewHolder> implements Filterable {

   // private final Object ReceiptAdapterListener;
    private Context context;
    private List<SaleTransaction> receiptList;
    private List<SaleTransaction> receiptListFiltered;
    private ReceiptAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView date, customer, total;

        public MyViewHolder(View view) {
            super(view);
            //business_name = view.findViewById(R.id.business_name);
            date = view.findViewById(R.id.date);
            customer = view.findViewById(R.id.customer);
            total = view.findViewById(R.id.total);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onReceiptSelected(receiptListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public ReceiptAdapter(Context context, List<SaleTransaction> receiptList, ReceiptAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.receiptList = receiptList;
        this.receiptListFiltered = receiptList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sale_row_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final SaleTransaction receipt = receiptListFiltered.get(position);
        final DateFormat dateFormatter = SimpleDateFormat.getDateInstance();
        holder.date.setText(dateFormatter.format(new Date(receipt.getSaleTransactionDate())));
        DBHelper dbHelper = new DBHelper(context);
        Customer customer = dbHelper.getCustomerWithCode(receipt.getCustomerCode());
        holder.customer.setText(customer.getCustomerName());
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        //formatter.format(amount)
        //holder.total.setText(String.valueOf(dbHelper.getReceiptTotal(receipt.getReceiptCode())));
        holder.total.setText(String.valueOf(formatter.format(receipt.getSaleTransactionTotal())));
    }

    @Override
    public int getItemCount() {
        return receiptListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    receiptListFiltered = receiptList;
                } else {
                    List<SaleTransaction> filteredList = new ArrayList<>();
                    for (SaleTransaction row : receiptList) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                       /* if (row.getCustomerName().toLowerCase().contains(charString.toLowerCase()) || row.getCustomerEmail().toLowerCase().contains(charString.toLowerCase()) ||
                                row.getCustomerPhone().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }*/
                    }
                    receiptListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = receiptListFiltered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                receiptListFiltered = (ArrayList<SaleTransaction>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ReceiptAdapterListener {
        void onReceiptSelected(SaleTransaction receipt);
    }
}
