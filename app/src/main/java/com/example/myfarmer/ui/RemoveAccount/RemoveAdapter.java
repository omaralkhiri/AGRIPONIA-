package com.example.myfarmer.ui.RemoveAccount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.myfarmer.info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveAdapter extends RecyclerView.Adapter<RemoveAdapter.userViewHolder> {
    private Context context;
    private List<Users> UsersList;
    private RequestQueue queue;
    public RemoveAdapter(Context context, List<Users> UserList) {
        this.context = context;
        this.UsersList = UserList;
        queue= Volley.newRequestQueue(context);
    }

    @NonNull
    @Override
    public userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.removeuser,parent,false);
        return new userViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull userViewHolder holder, int position) {
        Users users = UsersList.get(position);

        holder.setValues(users);
    }

    @Override
    public int getItemCount() {
        return UsersList.size();
    }
    public void filterList(ArrayList<Users> filteredlist) {
        UsersList=filteredlist;
        notifyDataSetChanged();
    }
    class userViewHolder extends RecyclerView.ViewHolder
    {
        private TextView nameuser,phoneuser,emailuser,jobuser;
        private Button remove;
        ImageView image1,image2,image3,image4;
        public userViewHolder(View itemView) {
            super(itemView);
            nameuser = itemView.findViewById(R.id.texname);
            emailuser=itemView.findViewById(R.id.texemail);
            phoneuser = itemView.findViewById(R.id.texphone);
            jobuser=itemView.findViewById(R.id.texjob);
            image1=itemView.findViewById(R.id.imagname);
            image2=itemView.findViewById(R.id.imagphone);
            image3=itemView.findViewById(R.id.imagemail);
            image4=itemView.findViewById(R.id.imagjob);
            remove=itemView.findViewById(R.id.btnremove);

            String Url="http://"+ info.getIpAddress()+"/myfarmer/removeuser.php";
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    Users users = UsersList.get(pos);
                    String id=users.getId()+"";
                    StringRequest request=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                         if(response.trim().equals("true"))
                             Toast.makeText(itemView.getContext(), "Successful Remove", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                }
            });
        }

        public void setValues(Users users)
        {
            remove.setText("Remove");
            nameuser.setText(users.getName());
            jobuser.setText(users.getJob());
            phoneuser.setText(users.getPhone());
            emailuser.setText(users.getName());
            image1.setImageResource(R.drawable.profile);
            image2.setImageResource(R.drawable.telephone);
            image3.setImageResource(R.drawable.gmail);
            image4.setImageResource(R.drawable.suitcase);
        }
    }
}
