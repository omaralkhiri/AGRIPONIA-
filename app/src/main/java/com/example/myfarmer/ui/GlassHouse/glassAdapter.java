package com.example.myfarmer.ui.GlassHouse;

import static com.example.myfarmer.R.drawable.house;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myfarmer.GlassHouse;
import com.example.myfarmer.R;
import com.example.myfarmer.info;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class glassAdapter extends RecyclerView.Adapter<glassAdapter.glassViewHolder> {
    private Context context;
    private List<GlassHouse> glassList;
    String URL="http://"+info.getIpAddress()+"/myfarmer/visit.php";
    private RequestQueue queue;

    public glassAdapter(Context context, List<GlassHouse> glassList) {
        this.context = context;
        this.glassList = glassList;
        queue= Volley.newRequestQueue(context);
    }

    @NonNull
    @Override
    public glassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.glasshouse, parent, false);
        return new glassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull glassViewHolder holder, int position) {
        GlassHouse glassHouse = glassList.get(position);
        holder.setValues(glassHouse);
    }

    @Override
    public int getItemCount() {
        return glassList.size();
    }

    class glassViewHolder extends RecyclerView.ViewHolder {


        private ImageView glassImageView;
        private TextView titleTextView;
        private Button information;

        public glassViewHolder(View itemView) {
            super(itemView);
            glassImageView = itemView.findViewById(R.id.imageView17);
            titleTextView = itemView.findViewById(R.id.textView12);
            information = itemView.findViewById(R.id.button2);
            information.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos=getAdapterPosition();
                    GlassHouse glassHouse=glassList.get(pos);
                    info.setHouse_id(glassHouse.getId());
                    Navigation.findNavController(itemView).navigate(R.id.action_nav_GlassHouse_to_aquandplanFragment);
                    StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

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
                            params.put("houseid",info.getHouse_id()+"");
                            params.put("userid",info.getId());
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


        public void setValues(GlassHouse glassHouse) {
              glassImageView.setImageResource(house);
              titleTextView.setText(glassHouse.getGlasshousename());
        }
    }
}

