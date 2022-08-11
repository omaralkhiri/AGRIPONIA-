package com.example.myfarmer.ui.Show;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfarmer.Aquarium;
import com.example.myfarmer.R;

import java.util.List;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ShowViewHolder> {
    private Context context;
    private List<Aquarium> aquariumList;

    public ShowAdapter(Context context, List<Aquarium> aquariumList) {
        this.context = context;
        this.aquariumList = aquariumList;
    }

    @NonNull
    @Override
    public ShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.allinfofishlayout, parent, false);
        return new ShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowViewHolder holder, int position) {
        Aquarium aquarium = aquariumList.get(position);
        holder.setValues(aquarium);
    }

    @Override
    public int getItemCount() {
        return aquariumList.size();
    }

    class ShowViewHolder extends RecyclerView.ViewHolder {
        private Movie movie;

        private ImageView fishImageView;
        private TextView dateAndtimeTextView,tempAndhum;


        public ShowViewHolder(View itemView) {
            super(itemView);
            fishImageView = itemView.findViewById(R.id.imageView19);
            dateAndtimeTextView = itemView.findViewById(R.id.textView16);
            tempAndhum = itemView.findViewById(R.id.textView17);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                }
            });
        }

        public void setValues(Aquarium aquarium) {
            fishImageView.setImageResource(R.drawable.fish);
            dateAndtimeTextView.setText("DATE:"+aquarium.getDate()+"\t\tTIME:"+aquarium.getTime());
            tempAndhum.setText("Temperature:"+aquarium.getTemperature()+"\t\tHumidity:"+aquarium.getHumidity());

        }
    }
}


