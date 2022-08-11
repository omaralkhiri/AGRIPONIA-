package com.example.myfarmer.ui.GlassHouse;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myfarmer.GlassHouse;
import com.example.myfarmer.R;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import com.example.myfarmer.info;

public class GlassHouseFragment extends Fragment {
    private RecyclerView recyclerView;
    private glassAdapter adapter;
    private List<GlassHouse> glassHouseList;
    private RequestQueue queue;
    String URL="http://"+info.getIpAddress()+"/myfarmer/allhouse.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_glass_house, container, false);

        recyclerView = view.findViewById(R.id.glass_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                buildglasshouselist(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue= Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(request);
        return view;
    }
    public void buildglasshouselist(JSONObject response) {
        try {
            glassHouseList = new ArrayList<GlassHouse>();
            JSONArray allhouse=response.getJSONArray("house");
            for (int i=0;i<allhouse.length();i++){
                JSONObject onehouse=allhouse.getJSONObject(i);
                int id=onehouse.getInt("id_house");
                String name=onehouse.getString("glasshousename");
                GlassHouse glassHouse=new GlassHouse(id,name);
                glassHouseList.add(glassHouse);
            }
            adapter = new glassAdapter(getActivity().getApplicationContext(), glassHouseList);
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);

        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}