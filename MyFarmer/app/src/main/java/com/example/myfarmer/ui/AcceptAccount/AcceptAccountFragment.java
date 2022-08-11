package com.example.myfarmer.ui.AcceptAccount;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
import com.example.myfarmer.Users;
import com.example.myfarmer.databinding.FragmentAcceptaccountBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AcceptAccountFragment extends Fragment {
    private RecyclerView recyclerView;
    private acceptAdapter adapter;
    private List<Users> usersList;
    private RequestQueue queue;
    private String URL = "http://"+com.example.myfarmer.info.getIpAddress()+"/myfarmer/AcceptAccount.php";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_acceptaccount,container,false);

        recyclerView = root.findViewById(R.id.acceptuser);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        EditText editsaerch=root.findViewById(R.id.search);

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

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                builduserlist(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue= Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(request);
        return root;
    }
    public void builduserlist(JSONObject response) {
        try {
            usersList = new ArrayList<Users>();
            JSONArray allusers=response.getJSONArray("user");
            for (int i=0;i<allusers.length();i++){
                JSONObject oneuser=allusers.getJSONObject(i);
                int id=oneuser.getInt("Id");
                String name=oneuser.getString("name");
                String birthday=oneuser.getString("birthday");
                String gender=oneuser.getString("gender");
                String phone=oneuser.getString("phone");
                String email=oneuser.getString("email");
                String job=oneuser.getString("job");

                Users users=new Users(id,name,gender,birthday,phone,email,job);
                usersList.add(users);
            }
            adapter = new acceptAdapter(getActivity().getApplicationContext(), usersList);
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);

        } catch (Exception x) {
            x.printStackTrace();
        }
    }
    private void filter(String text){
        ArrayList<Users> filteredlist=new ArrayList<>();
        for (Users item:usersList){
            if (item.getPhone().toLowerCase().contains(text.toLowerCase())){
                filteredlist.add(item);
            }
        }
        adapter.filterList(filteredlist);
    }
}