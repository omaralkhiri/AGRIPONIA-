package com.example.myfarmer.ui.CreateAccount;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.myfarmer.Login_Activity;
import com.example.myfarmer.R;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountFragment extends Fragment {
    EditText txtname,txtphone,txtemail,txtpass1,txtpass2;
    Spinner job,day,mounth,year;
    String name,phone,email,pass1,pass2,job1,gender,info="";
    RadioButton maleradio,femaleradio;
    String Url="http://"+com.example.myfarmer.info.getIpAddress()+"/myfarmer/CreateAccount.php";
    AlertDialog.Builder alert;
    AwesomeValidation awesomeValidation;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_createaccount,container,false);

        txtname=root.findViewById(R.id.txtname);
        txtphone=root.findViewById(R.id.txtphone);
        txtemail=root.findViewById(R.id.txtemail);
        txtpass1=root.findViewById(R.id.txtpass);
        txtpass2=root.findViewById(R.id.txtpass2);
        job=root.findViewById(R.id.job);
        day=root.findViewById(R.id.spinner);
        mounth=root.findViewById(R.id.spinner2);
        year=root.findViewById(R.id.spinner3);
        maleradio = root.findViewById(R.id.radio_male);
        femaleradio = root.findViewById(R.id.radio_female);


        alert=new AlertDialog.Builder(getContext());
        alert.setCancelable(true);
        alert.setPositiveButton("OK",null);
        Button signup=root.findViewById(R.id.button1);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=txtname.getText().toString();
                phone=txtphone.getText().toString();
                email=txtemail.getText().toString();
                pass1=txtpass1.getText().toString();
                pass2=txtpass2.getText().toString();
                job1=job.getSelectedItem().toString();
                info += day.getSelectedItem()+"/";
                info += mounth.getSelectedItem()+"/";
                info += year.getSelectedItem();
                if (maleradio.isChecked())
                    gender = "Male";
                else
                    gender = "Female";
                //to check information
                awesomeValidation =new AwesomeValidation(ValidationStyle.BASIC);
                awesomeValidation.addValidation(getActivity(),R.id.txtname, RegexTemplate.NOT_EMPTY,R.string.inputname);
                awesomeValidation.addValidation(getActivity(),R.id.txtpass, ".{8,}",R.string.inputPass);
                awesomeValidation.addValidation(getActivity(),R.id.txtpass, R.id.txtpass2,R.string.notequalpass);
                awesomeValidation.addValidation(getActivity(),R.id.txtemail, Patterns.EMAIL_ADDRESS,R.string.inputemail);
                awesomeValidation.addValidation(getActivity(),R.id.txtphone,".{10,}",R.string.inputphone);
                if (awesomeValidation.validate()){
                    StringRequest request=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.trim().equals("true")) {
                                alert.setTitle("Sign Up");
                                alert.setMessage("Success Sign Up \nplease wait two days to check your Information");
                                alert.setPositiveButton("OK", null);
                                alert.show();
                                txtname.setText("");
                                txtphone.setText("");
                                txtemail.setText("");
                                txtpass1.setText("");
                                txtpass2.setText("");

                            }else if (response.trim().equals("false")){
                                alert.setTitle("Invalid sign up ");
                                alert.setMessage("Please verify that the data is correct");
                                alert.show();
                            }else if (response.trim().equals("exist")){
                                alert.setTitle("Create Account");
                                alert.setMessage("This Number or Email has already been registered");
                                alert.show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params=new HashMap<>();
                            params.put("name",name);
                            params.put("phone",phone);
                            params.put("email",email);
                            params.put("pass",pass1);
                            params.put("job",job1);
                            params.put("gender",gender);
                            params.put("birthday",info);
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                    requestQueue.add(request);
                }
                else
                {
                    Toast.makeText(getContext(),"Validation Failed",Toast.LENGTH_LONG).show();
                }
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}