package com.example.friendsapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class viewfriends extends AppCompatActivity {
    String url="https://friendsapi-re5a.onrender.com/view";
    TextView t0,t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_viewfriends);
        t1=(TextView)findViewById(R.id.conf);
        t0=(TextView)findViewById(R.id.pic) ;
        JsonArrayRequest friend=new JsonArrayRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        t1.setText(response.toString());
                        StringBuilder result=new StringBuilder();
                        for (int p=0;p<response.length();p++)
                        {
                            try {
                                JSONObject friend= response.getJSONObject(p);
                                String name=friend.getString("name");
                                String fname=friend.getString("friendName");
                                String fnname=friend.getString("friendNickName");
                                String descp=friend.getString("DescribeYourFriend");
                                result.append(name).append(" ").append(fname).append(" ").append(fnname).append(" ").append(descp).append("\n\n");
                                t1.setText(result);

                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }


        );
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(friend);


    }
}