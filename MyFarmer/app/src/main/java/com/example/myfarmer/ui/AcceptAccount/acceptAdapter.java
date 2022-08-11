package com.example.myfarmer.ui.AcceptAccount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myfarmer.R;
import com.example.myfarmer.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class acceptAdapter extends RecyclerView.Adapter<acceptAdapter.accept_rejectViewHolder>{
    private Context context;
    private List<Users> usersList;
    private RequestQueue queue;

    public acceptAdapter(Context context, List<Users> usersList) {
        this.context = context;
        this.usersList = usersList;
        queue= Volley.newRequestQueue(context);
    }
    @NonNull
    @Override
    public accept_rejectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.acceptlayout, parent, false);
        return new accept_rejectViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull accept_rejectViewHolder holder, int position) {
        Users users = usersList.get(position);
        holder.setValues(users);
    }
    @Override
    public int getItemCount() {
        return usersList.size();
    }
    public void filterList(ArrayList<Users> filteredlist) {
        usersList=filteredlist;
        notifyDataSetChanged();
    }

    class accept_rejectViewHolder extends RecyclerView.ViewHolder
    {
        private TextView username,gender_user,birthday_user,phone_user,job_user,email_user;
        private ImageView imgtrue,imgcancel,imgname,imggender,imgbirth,imgphone,imgemail,imgjob;
        private String URL="http://"+ com.example.myfarmer.info.getIpAddress()+"/myfarmer/accept.php";
        private String URL1="http://"+com.example.myfarmer.info.getIpAddress()+"/myfarmer/removeuser.php";
        private String id;
        public accept_rejectViewHolder(View itemView) {
            super(itemView);
            imgname=itemView.findViewById(R.id.imageView4);
            imggender=itemView.findViewById(R.id.imageView5);
            imgbirth=itemView.findViewById(R.id.imageView6);
            imgphone=itemView.findViewById(R.id.imageView7);
            imgemail=itemView.findViewById(R.id.imageView8);
            imgjob =itemView.findViewById(R.id.imageView9);
            username=itemView.findViewById(R.id.textname);
            email_user=itemView.findViewById(R.id.textemail);
            gender_user=itemView.findViewById(R.id.textgender);
            birthday_user=itemView.findViewById(R.id.textbirthday);
            phone_user=itemView.findViewById(R.id.textphone);
            imgtrue=itemView.findViewById(R.id.image10);
            imgcancel=itemView.findViewById(R.id.image11);
            job_user=itemView.findViewById(R.id.textjob);

            imgtrue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    Users users = usersList.get(pos);
                    id=users.getId()+"";

                    StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.trim().equals("true")){
                                Toast.makeText(context.getApplicationContext(), "successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params=new HashMap<>();
                            params.put("id_num",id);
                            return params;
                        }
                    };
                    queue.add(request);
                }
            });
            imgcancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    Users users = usersList.get(pos);
                    id=users.getId()+"";
                    StringRequest request=new StringRequest(Request.Method.POST, URL1, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.trim().equals("true"))
                            {
                                Toast.makeText(context.getApplicationContext(), "Delete Successful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params =new HashMap<>();
                            params.put("id_num",id);
                            return params;
                        }
                    };
                    queue.add(request);
                }
            });

        }

        public void setValues(Users users)
        {
            imgcancel.setImageResource(R.drawable.delete);
            imgtrue.setImageResource(R.drawable.checked);
            imgname.setImageResource(R.drawable.team);
            imggender.setImageResource(R.drawable.sexism);
            imgbirth.setImageResource(R.drawable.calendar);
            imgphone.setImageResource(R.drawable.telephone);
            imgemail.setImageResource(R.drawable.gmail);
            imgjob.setImageResource(R.drawable.suitcase);
            username.setText(users.getName());
            email_user.setText(users.getEmail());
            gender_user.setText(users.getGender());
            birthday_user.setText(users.getBirthday());
            phone_user.setText(users.getPhone());
            job_user.setText(users.getJob());
        }
    }
}
