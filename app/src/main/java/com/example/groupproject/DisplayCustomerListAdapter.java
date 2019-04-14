package com.example.groupproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import Model.Customer;
import Model.Owner;

public class DisplayCustomerListAdapter extends RecyclerView.Adapter<DisplayCustomerListAdapter.ViewHolder> {
    private Owner owner;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    DisplayCustomerListAdapter(Context context, Owner _owner) {
        this.mInflater = LayoutInflater.from(context);
        this.owner = _owner;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //String category = allReminders.getCategory(position).toString();
        String customerName = owner.getCustomerList().get(position).getCustomerName();
        holder.myTextView.setText(customerName);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        //return allReminders.getCategories().size();
        return owner.getCustomerList().size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.ownerapts);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Customer getItem(int id) {
        //return allReminders.getCategory(id).toString();
        return owner.getCustomerList().get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
