package com.example.myfarmer.ui.allDATA;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfarmer.R;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    private Context context;
    private List<DataTH> dataList;

    public DataAdapter(Context context, List<DataTH> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.data, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        DataTH data = dataList.get(position);
        holder.setValues(data);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void filterList(ArrayList<DataTH> filteredlist) {
        dataList=filteredlist;
        notifyDataSetChanged();
    }

    class DataViewHolder extends RecyclerView.ViewHolder {
        private TextView txtdate,txttime,txtTH;

        public DataViewHolder(View itemView) {
            super(itemView);
            txtdate=itemView.findViewById(R.id.date);
            txttime=itemView.findViewById(R.id.time);
            txtTH=itemView.findViewById(R.id.TH);
        }

        public void setValues(DataTH data) {
          txttime.setText(data.getTime());
          txtdate.setText(data.getDate());
          txtTH.setText("T: "+data.getTemperature()+"H: "+data.getHumidity());
        }
    }
}



