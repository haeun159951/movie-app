package com.example.movies_haeun_hekim4.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.movies_haeun_hekim4.R;
import com.example.movies_haeun_hekim4.adapter.PurchaseAdapter;
import com.example.movies_haeun_hekim4.databinding.Fragment2Binding;
import com.example.movies_haeun_hekim4.db.MyDatabase;
import com.example.movies_haeun_hekim4.listeners.OnPurchaseItemClickListener;
import com.example.movies_haeun_hekim4.models.Purchase;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class Fragment2 extends Fragment implements OnPurchaseItemClickListener {

    // binding
    private Fragment2Binding binding;
    // database
    private MyDatabase db;
    //adapter
    private PurchaseAdapter adapter;
    // list of purchases
    List<Purchase> purchaseList;

    public Fragment2() {
        super(R.layout.fragment_2);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = Fragment2Binding.inflate(inflater, container, false);
        View view = binding.getRoot();
        db = MyDatabase.getDatabase(getContext());
        //retrieve all from the db
        purchaseList = db.purchaseDAO().getAllTickets();
        if (purchaseList.size() == 0) {
            binding.tvResult.setText("You do not have any tickets");
        }
        this.binding.rvTickets.setLayoutManager(new LinearLayoutManager(getContext()));
        this.binding.rvTickets.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        this.adapter = new PurchaseAdapter(view.getContext(), purchaseList, this::OnItemClickListener);
        this.binding.rvTickets.setAdapter(this.adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void OnItemClickListener(Purchase currentItem) {
        Snackbar.make(binding.getRoot(), "Deleted!", Snackbar.LENGTH_SHORT).show();
        db.purchaseDAO().deleteTicket(currentItem);
        purchaseList.clear();
        purchaseList.addAll(db.purchaseDAO().getAllTickets());
        adapter.notifyDataSetChanged();
        if (purchaseList.size() == 0) {
            binding.tvResult.setText("You do not have any tickets");
        }
    }
}