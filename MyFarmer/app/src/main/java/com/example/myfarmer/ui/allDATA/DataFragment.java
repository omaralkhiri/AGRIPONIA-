package com.example.myfarmer.ui.allDATA;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myfarmer.R;
import com.example.myfarmer.info;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DataFragment extends Fragment {
    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private List<DataTH> DATAList;
    private RequestQueue queue;
    private String URL = "http://"+info.getIpAddress()+"/myfarmer/allthreedata.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root= inflater.inflate(R.layout.fragment_data, container, false);

        recyclerView = root.findViewById(R.id.RCDATA);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        EditText editsaerch=root.findViewById(R.id.editTextTextPersonName);

        editsaerch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
        URL+="?fish_id="+ info.getFish_id();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                buildDATAlist(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
             error.printStackTrace();
            }
        });
        queue= Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(request);

        return root;
    }
    public void buildDATAlist(JSONObject response) {
        try {
            DATAList = new ArrayList<DataTH>();
            JSONArray allusers=response.getJSONArray("fish");
            for (int i=0;i<allusers.length();i++){
                JSONObject oneuser=allusers.getJSONObject(i);

                String date=oneuser.getString("date1");
                String time=oneuser.getString("time");
                String temp=oneuser.getString("temperature");
                String hum=oneuser.getString("Humidity");

                DataTH dataTH=new DataTH(date,time,temp,hum);
                DATAList.add(dataTH);
            }
            adapter = new DataAdapter(getActivity().getApplicationContext(), DATAList);
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);

        } catch (Exception x) {
            x.printStackTrace();
        }
    }
    private void filter(String text){
        ArrayList<DataTH> filteredlist=new ArrayList<>();
        for (DataTH item:DATAList){
            if (item.getDate().toLowerCase().contains(text.toLowerCase())){
                filteredlist.add(item);
            }
        }
        adapter.filterList(filteredlist);
    }
}