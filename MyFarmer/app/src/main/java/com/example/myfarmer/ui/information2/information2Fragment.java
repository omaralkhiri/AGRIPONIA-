package com.example.myfarmer.ui.information2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myfarmer.Login_Activity;
import com.example.myfarmer.R;
import com.example.myfarmer.info;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class information2Fragment extends Fragment {
    private RequestQueue queue;
    private String URL="http://"+ info.getIpAddress()+"/myfarmer/userinfomation.php";
    private TextView txtName,txtbirthday,txtGender;
    private EditText txtPHone,txtemail;
    String id=info.getId();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_information2, container, false);
        txtName=view.findViewById(R.id.nametxt);
        txtbirthday=view.findViewById(R.id.textViewbirthday);
        txtemail=view.findViewById(R.id.editTextTextEmailAddress);
        txtPHone=view.findViewById(R.id.editTextPhone);
        txtGender=view.findViewById(R.id.textViewgender);
        String email=txtemail.getText().toString();

        Button update =view.findViewById(R.id.button4);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String URL1 = "http://" + info.getIpAddress() + "/myfarmer/updateinformation.php";

                StringRequest request = new StringRequest(Request.Method.POST, URL1, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> param = new HashMap<>();
                        param.put("phone", txtPHone.getText().toString());
                        param.put("email", txtemail.getText().toString());
                        param.put("id", id);
                        return param;
                    }
                };
                queue.add(request);
            }
        });


        Button btnSignout=view.findViewById(R.id.button3);
        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(), Login_Activity.class);
                startActivity(intent);
            }
        });

        queue= Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GETinformation(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_num",id);
                return params;
            }
        };
        queue.add(request);
        return view;
    }
    private void GETinformation(String response){
        try {
            JSONObject user=new JSONObject(response);
            txtName.setText(user.getString("name"));
            txtbirthday.setText(user.getString("birthday"));
            txtGender.setText(user.getString("gender"));
            txtemail.setText(user.getString("email"));
            txtPHone.setText(user.getString("phone"));
        }
        catch (Exception x){
            x.printStackTrace();
        }
    }
}