package com.example.myfarmer.ui.home2;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfarmer.R;


public class Home2Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home2, container, false);
        CardView cardinformation,cardglass;
        cardinformation=view.findViewById(R.id.cardinfo);
        cardglass=view.findViewById(R.id.cardglass);
        cardinformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_home2Fragment_to_information2Fragment);
            }
        });
        cardglass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_home2Fragment_to_glassHouseFragment);
            }
        });
        return view;
    }
}