package com.example.myfarmer.ui.aquariumandplantpool;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.provider.AlarmClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myfarmer.Aquarium;
import com.example.myfarmer.GlassHouse;
import com.example.myfarmer.Plant;
import com.example.myfarmer.R;
import com.example.myfarmer.info;
import com.example.myfarmer.ui.GlassHouse.glassAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class aquandplanFragment extends Fragment {
    private RecyclerView recyclerView1,recyclerView2;
    private PlantAdapter adapter1;
    private AquariumAdapter adapter2;
    private List<Plant> plantList;
    private List<Aquarium> aquariumsList;
    private RequestQueue queue,queue2;
    private SwipeRefreshLayout swipeRefreshLayout;
    double temp=25.,hum=55.;
    String URL="http://"+ info.getIpAddress()+"/myfarmer/allplant.php",
           URL2="http://"+ info.getIpAddress()+"/myfarmer/allfish.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_aquandplan, container, false);

        recyclerView1 = view.findViewById(R.id.recyclerviewplant);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(layoutManager);

        recyclerView2 = view.findViewById(R.id.recyclerviewaquarium);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView2.setLayoutManager(layoutManager1);

        URL+="?id_house="+info.getHouse_id();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                buildplantlist(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue= Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(request);


        swipeRefreshLayout=view.findViewById(R.id.frach);
        request();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                request();
                adapter2.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }
    public void check(Double temp,double hum){
        if ((temp<25.0|| temp>=30.0)||(hum<55.0|| hum>=65.0)){
            AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
            alert.setTitle("Error Message   ");
            alert.setMessage("Please check Temperature AND Humidity values");
            alert.setCancelable(true);
            alert.setIcon(R.drawable.fan);
            alert.setPositiveButton("Yes",null);
            alert.show();
        }
    }
    public void buildplantlist(JSONObject response) {
        try {
            plantList = new ArrayList<Plant>();
            JSONArray allplant=response.getJSONArray("plantpool");
            for (int i=0;i<allplant.length();i++){
                JSONObject onehouse=allplant.getJSONObject(i);
                int id=onehouse.getInt("plant_id");
                String name=onehouse.getString("plant_name");
                Plant plant=new Plant(id,name);
                plantList.add(plant);
            }
            adapter1 = new PlantAdapter(getActivity().getApplicationContext(), plantList);
            recyclerView1.setAdapter(adapter1);
            recyclerView1.setHasFixedSize(true);

        } catch (Exception x) {
            x.printStackTrace();
        }
    }
    public void buildaquariumlist(JSONObject response) {
        try {
            aquariumsList = new ArrayList<Aquarium>();
            JSONArray allaquarium=response.getJSONArray("aquarium");
            for (int i=0;i<allaquarium.length();i++){
                JSONObject oneaquarium=allaquarium.getJSONObject(i);
                int id=oneaquarium.getInt("fish_id");
                String name=oneaquarium.getString("name_fish");
                temp=oneaquarium.getDouble("temperature");
                hum=oneaquarium.getDouble("Humidity");
                Aquarium aquarium=new Aquarium(id,name,temp,hum);
                aquariumsList.add(aquarium);
                check(temp,hum);
            }
            adapter2 = new AquariumAdapter(getActivity().getApplicationContext(), aquariumsList);
            recyclerView2.setAdapter(adapter2);
            recyclerView2.setHasFixedSize(true);

        } catch (Exception x) {
            x.printStackTrace();
        }

    }
    private void request(){
        URL2+="?id_house="+info.getHouse_id();
        JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, URL2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                buildaquariumlist(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue2= Volley.newRequestQueue(getActivity().getApplicationContext());
        queue2.add(request2);

    }
}