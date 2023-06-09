package sales.management.kiwamirembe.co.ug;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ReceiptsFragment extends Fragment implements ReceiptAdapter.ReceiptAdapterListener {

    DBHelper dbHelper;
    private RecyclerView recyclerView;
    private List<SaleTransaction> receiptList;
    private ReceiptAdapter mAdapter;
    private SearchView searchView;

    public ReceiptsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_receipts, container, false);
        View root = inflater.inflate(R.layout.fragment_receipts, container, false);

        dbHelper = new DBHelper(getActivity());
        recyclerView = root.findViewById(R.id.receiptsRecycler);
        searchView = root.findViewById(R.id.searchReceipts);
        searchProducts(searchView);
        receiptList = dbHelper.getSaleTransactions();
        mAdapter = new ReceiptAdapter(getActivity(), receiptList, this::onReceiptSelected);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        return root;
    }

    public void searchProducts(SearchView sv) {
        SearchManager searchManager = (SearchManager)getActivity().getSystemService(Context.SEARCH_SERVICE);
        sv.setMaxWidth(Integer.MAX_VALUE);
        // listening to search query text change
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
    }

    @Override
    public void onReceiptSelected(SaleTransaction product) {
        //Toast.makeText(getApplicationContext(), product.getSaleItemName(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), ViewEditSaleTransactionActivity.class);
        intent.putExtra("identifier",product.getSaleTransactionIdentifier());
        intent.putExtra("customerCode",product.getCustomerCode());
        intent.putExtra("transactionTotal",String.valueOf(product.getSaleTransactionTotal()));
        intent.putExtra("transactionDate",String.valueOf(product.getSaleTransactionDate()));
        startActivity(intent);
    }

}