package com.example.myfarmer.ui.aquariumandplantpool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfarmer.Plant;
import com.example.myfarmer.R;

import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantViewHolder> {
    private Context context;
    private List<Plant> plantList;

    public PlantAdapter(Context context, List<Plant> plantList) {
        this.context = context;
        this.plantList = plantList;
    }

    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.plant, parent, false);
        return new PlantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, int position) {
        Plant plant = plantList.get(position);
        holder.setValues(plant);
    }

    @Override
    public int getItemCount() {
        return plantList.size();
    }

    class PlantViewHolder extends RecyclerView.ViewHolder {

        private ImageView plantImageView;
        private TextView titleTextView;


        public PlantViewHolder(View itemView) {
            super(itemView);
            plantImageView = itemView.findViewById(R.id.imageView15);
            titleTextView = itemView.findViewById(R.id.textView9);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                }
            });
        }

        public void setValues(Plant plant) {
          plantImageView.setImageResource(R.drawable.plant);
          titleTextView.setText(plant.getPlantname());
        }
    }
}


