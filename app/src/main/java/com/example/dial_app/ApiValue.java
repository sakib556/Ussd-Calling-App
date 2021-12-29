package com.example.dial_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class ApiValue extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_value);
        textView = findViewById(R.id.txtVid);
        String url = "https://zara-business.herokuapp.com/flexi-load/read";
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                  //      Log.e("Rest Response",response.toString());
                        try {
                            JSONArray jsonArray=response.getJSONArray("data");
                            StringBuffer maindata=new StringBuffer();
                            for (int i = 0; i <jsonArray.length() ; i++) {
                                JSONObject data=jsonArray.getJSONObject(i);
                                maindata.append(data.toString()+"\n");
                            }
                                textView.setText(maindata);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                     //   textView.setText(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

}