package com.example.myfarmer.ui.infomation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myfarmer.Login_Activity;
import com.example.myfarmer.info;
import com.android.volley.RequestQueue;
import com.example.myfarmer.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class InformationAdminFragment extends Fragment {
    private RequestQueue queue;
    private String URL="http://"+info.getIpAddress()+"/myfarmer/userinfomation.php";
    private TextView txtName,txtbirthday,txtGender;
    private EditText txtPHone,txtemail;
    String id=info.getId();
    String email,phone;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_information, container, false);

        txtName=root.findViewById(R.id.nametxt);
        txtbirthday=root.findViewById(R.id.textViewbirthday);
        txtemail=root.findViewById(R.id.editTextTextEmailAddress);
        txtPHone=root.findViewById(R.id.editTextPhone);
        txtGender=root.findViewById(R.id.textViewgender);


        Button update =root.findViewById(R.id.button4);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=txtemail.getText().toString();
                phone= txtPHone.getText().toString().trim();

                 String URL1 = "http://"+info.getIpAddress()+"/myfarmer/updateinformation.php";
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
                                    param.put("email",txtemail.getText().toString());
                                    param.put("id",id);
                                    return param;
                                }
                            };
                            queue.add(request);
                        }
        });


        Button btnSignout=root.findViewById(R.id.button3);
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
        return root;
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