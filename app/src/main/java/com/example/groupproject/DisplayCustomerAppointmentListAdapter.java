package com.example.groupproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class DisplayCustomerAppointmentListAdapter extends RecyclerView.Adapter<DisplayCustomerAppointmentListAdapter.ViewHolder> {
    //private Owner owner;
    private ArrayList<Calendar> tempList;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    DisplayCustomerAppointmentListAdapter(Context context, ArrayList _tempList) {
        this.mInflater = LayoutInflater.from(context);
        this.tempList = _tempList;
    }

    // inflates the row layout from xml when needed
    @Override
    public DisplayCustomerAppointmentListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new DisplayCustomerAppointmentListAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int month = tempList.get(position).get(Calendar.MONTH);
        int day = tempList.get(position).get(Calendar.DAY_OF_MONTH);
        int year = tempList.get(position).get(Calendar.YEAR);
        String str = "You have an appointment on " + month + "/" + day + "/" + year + ".";
        holder.myTextView.setText(str);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        //return allReminders.getCategories().size();
        return tempList.size();
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
    String getItem(int id) {
        int month = tempList.get(id).get(Calendar.WEEK_OF_MONTH);
        int day = tempList.get(id).get(Calendar.DAY_OF_MONTH);
        int year = tempList.get(id).get(Calendar.YEAR);
        String str = month + "/" + day + "/" + year;
        return str;
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
