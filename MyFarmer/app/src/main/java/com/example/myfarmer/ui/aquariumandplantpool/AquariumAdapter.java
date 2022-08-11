package com.example.myfarmer.ui.aquariumandplantpool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfarmer.Aquarium;
import com.example.myfarmer.R;
import com.example.myfarmer.info;

import java.util.List;

public class AquariumAdapter extends RecyclerView.Adapter<AquariumAdapter.AquariumViewHolder> {
    private Context context;
    private List<Aquarium> aquariumList;

    public AquariumAdapter(Context context, List<Aquarium> aquariumList) {
        this.context = context;
        this.aquariumList = aquariumList;
    }

    @NonNull
    @Override
    public AquariumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.aquariumlayout, parent, false);
        return new AquariumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AquariumViewHolder holder, int position) {
        Aquarium aquarium = aquariumList.get(position);
        holder.setValues(aquarium);
    }

    @Override
    public int getItemCount() {
        return aquariumList.size();
    }

    class AquariumViewHolder extends RecyclerView.ViewHolder {
        private ImageView aquariumImageView;
        private TextView nameTextView,temp,humid;

        public AquariumViewHolder(View itemView) {
            super(itemView);
            aquariumImageView = itemView.findViewById(R.id.imageView18);
            nameTextView=itemView.findViewById(R.id.textView13);
            temp=itemView.findViewById(R.id.textView14);
            humid=itemView.findViewById(R.id.textView15);
            Button btnShow=itemView.findViewById(R.id.button);
            btnShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos=getAdapterPosition();
                    Aquarium aquarium=aquariumList.get(pos);
                    info.setFish_id(aquarium.getFishid());
                    Navigation.findNavController(view).navigate(R.id.action_aquandplanFragment_to_showAllInfoFishFragment);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                }
            });
        }

        public void setValues(Aquarium aquarium) {
           aquariumImageView.setImageResource(R.drawable.fish);
           nameTextView.setText(aquarium.getFishname());
           temp.setText("Temperature:"+aquarium.getTemperature());
           humid.setText("Humidity:"+aquarium.getHumidity());
        }
    }
}


