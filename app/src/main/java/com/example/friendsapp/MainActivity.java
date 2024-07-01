package com.example.friendsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    AppCompatButton p1,p2;
    String apiurl="https://friendsapi-re5a.onrender.com/adddata";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ed1=(EditText)findViewById(R.id.name);
        ed2=(EditText)findViewById(R.id.fname);
        ed3=(EditText)findViewById(R.id.fnname);
        ed4=(EditText)findViewById(R.id.dpt);
        p1=(AppCompatButton)findViewById(R.id.subb);
        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName=ed1.getText().toString();
                String getFName =ed2.getText().toString();
                String getFNName=ed3.getText().toString();
                String getDpt=ed4.getText().toString();
                //CREATING JASON OBJECT
                JSONObject friend=new JSONObject();
                try {
                    friend.put("name",getName);
                    friend.put("friendName", getFName);
                    friend.put("friendNickName",getFNName);
                    friend.put("DescribeYourFriend",getDpt);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                //JASON OBJECT REQUEST CREAION
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,
                        apiurl,
                        friend,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "SUBMITTED", Toast.LENGTH_SHORT).show();
                            }
                        }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error Detected", Toast.LENGTH_SHORT).show();
                    }
                }

                );
                //Reqeust Queue
                RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);
            }
        });
        p2=(AppCompatButton)findViewById(R.id.frib);
        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), viewfriends.class);;
                startActivity(i);
            }
        });
    }
}