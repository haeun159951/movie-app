package com.example.movies_haeun_hekim4.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_haeun_hekim4.databinding.TicketRowLayoutBinding;
import com.example.movies_haeun_hekim4.listeners.OnPurchaseItemClickListener;
import com.example.movies_haeun_hekim4.models.Purchase;

import java.util.List;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.ItemViewHolder> {
    private final Context context;
    private final List<Purchase> itemArrayList;
    TicketRowLayoutBinding binding;

    private final OnPurchaseItemClickListener clickListener;

    public PurchaseAdapter(Context context, List<Purchase> items, OnPurchaseItemClickListener clickListener) {
        this.itemArrayList = items;
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(TicketRowLayoutBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Purchase currentItem = itemArrayList.get(position);
        holder.bind(context, currentItem, clickListener);
    }

    @Override
    public int getItemCount() {
        Log.d("PurchaseAdapter", "getItemCount: Number of tickets: " + this.itemArrayList.size());
        return this.itemArrayList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TicketRowLayoutBinding itemBinding;

        public ItemViewHolder(TicketRowLayoutBinding binding) {
            super(binding.getRoot());
            this.itemBinding = binding;
        }

        public void bind(Context context, Purchase currentItem, OnPurchaseItemClickListener clickListener) {
            // TODO: Update the UI for the row
            itemBinding.tvLine1.setText(currentItem.getMovieTitle());
            itemBinding.tvLine2.setText("Tickets Purchased: " + currentItem.getQuantity());
            // click detection
            itemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.OnItemClickListener(currentItem);
                }
            });
        }
    }
}
