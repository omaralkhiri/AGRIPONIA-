package com.example.myfarmer;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login_Activity extends AppCompatActivity {
    private AlertDialog.Builder alert;
    String job;
    String URL="http://"+info.getIpAddress()+"/myfarmer/login.php";
    RequestQueue queue;
    AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText phone=findViewById(R.id.phone);
        EditText pass=findViewById(R.id.Password);
        Button login=findViewById(R.id.login);
        TextView signup=findViewById(R.id.Signup);
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation( this,R.id.phone, RegexTemplate.NOT_EMPTY,R.string.inputEmail);
        awesomeValidation.addValidation( this,R.id.Password, RegexTemplate.NOT_EMPTY,R.string.inputPass);
        alert=new AlertDialog.Builder(this);
        queue= Volley.newRequestQueue(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ph=phone.getText().toString();
                String pas=pass.getText().toString();
             if(awesomeValidation.validate()){
              StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                  @Override
                  public void onResponse(String response) {
                      checkjob(response);
                      if(job.trim().equals("Admin")){
                          Intent intent=new Intent(getApplicationContext(),AdminActivity.class);
                          startActivity(intent);
                          pass.setText("");
                          phone.setText("");
                      }else if (job.trim().equals("Engineer")||job.trim().equals("Farmer")){
                          Intent intent1=new Intent(getApplicationContext(),EngFarActivity.class);
                          startActivity(intent1);
                          pass.setText("");
                          phone.setText("");
                      }else if (job.trim().equals("false")){
                          alert.setTitle("Invalid Login");
                          alert.setMessage("Invalid ID Number or password");
                          alert.setCancelable(true);
                          alert.setPositiveButton("OK", null);
                          alert.show();
                          pass.setText("");
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
                      params.put("email",ph);
                      params.put("pass",pas);
                      return params;
                  }
              };
                 queue.add(request);
             }else{
                 Toast.makeText(getApplicationContext(),"Validation Failed",Toast.LENGTH_LONG).show();
                 pass.setText("");
             }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SignUp_Activity.class);
                startActivity(intent);
            }
        });
    }
    private void checkjob(String respones){
        try {
            JSONObject jsonObject = new JSONObject(respones);
            job = jsonObject.getString("job");
            info.setId(jsonObject.getString("Id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}