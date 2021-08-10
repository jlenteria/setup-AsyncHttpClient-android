package com.rd.asynchttpclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.rd.asynchttpclient.AsyncHttpClient.Rest;
import com.rd.asynchttpclient.AsyncHttpClient.RestResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    Button loginBtn;
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = findViewById(R.id.loginBtn);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        loginBtn.setOnClickListener(v -> {
            String uname = username.getText().toString().trim();
            String pass = password.getText().toString().trim();

            //This how to call the async httpclient function
            Rest.getInstance().Login(uname, pass, new RestResponseHandler(){
                //These are the responses;
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Log.d("",  "On success 1: " + new Gson().toJson(response.toString()));
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    Log.d("",  "On success 2: " + new Gson().toJson(response.toString()));
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    Log.d("",  "On success 3: " + new Gson().toJson(responseString));
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.d("",  "On failure 1: " + new Gson().toJson(responseString));

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                    Log.d("",  "On failure 2: " + new Gson().toJson(errorResponse.toString()));
                }

            });
        });
    }
}