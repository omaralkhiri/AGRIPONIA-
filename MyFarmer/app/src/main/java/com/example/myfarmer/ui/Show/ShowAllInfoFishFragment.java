package com.example.myfarmer.ui.Show;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myfarmer.Aquarium;
import com.example.myfarmer.R;
import com.example.myfarmer.Users;
import com.example.myfarmer.info;
import com.example.myfarmer.ui.AcceptAccount.acceptAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ShowAllInfoFishFragment extends Fragment {
    private RecyclerView recyclerView;
    private ShowAdapter adapter;
    private List<Aquarium> aquariumList;
    private RequestQueue queue;
    private String URL ="http://"+ info.getIpAddress()+"/myfarmer/alldataoffish.php";
    private double tem,humidity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_show_all_info_fish, container, false);
        recyclerView = root.findViewById(R.id.recyclerfish);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        URL+="?fish_id="+ info.getFish_id();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                buildaquariumlist(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue= Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(request);
        Button moredetails=root.findViewById(R.id.moreDetails);
        moredetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.action_showAllInfoFishFragment_to_dataFragment);
            }
        });

        return root;
    }
    public void buildaquariumlist(JSONObject response) {
        try {
            aquariumList = new ArrayList<Aquarium>();
            JSONArray allfish=response.getJSONArray("fish");
            for (int i=0;i<allfish.length();i++){
                JSONObject oneuser=allfish.getJSONObject(i);
                String date=oneuser.getString("date1");
                String time=oneuser.getString("time");
                tem=oneuser.getDouble("temperature");
                humidity=oneuser.getDouble("Humidity");

                Aquarium aquarium=new Aquarium(date,time,tem,humidity);
                aquariumList.add(aquarium);
            }
            adapter = new ShowAdapter(getActivity().getApplicationContext(), aquariumList);
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);

        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}