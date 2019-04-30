package com.example.companydetails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView  = (RecyclerView) findViewById(R.id.recycler_view);

        Intent intent = getIntent();
        int companyID = intent.getIntExtra("companyId", 0);
        String token = intent.getStringExtra("token");
        getAllAsserts(token,"/api/assets", companyID);

    }

    private void getAllAsserts( String token, String Url, int companyID) {

        HashMap<String, String> parameters = new HashMap<>();

        //parameters.put("page", );
        //parameters.put("limit", UUID.randomUUID().toString());
        parameters.put("company_id", String.valueOf(companyID));
        Request.Builder builder = new Request.Builder()
                .url(APIURL(Url, parameters));
        builder = builder.header("token", token);

        builder.header("content-Type", "application/json");

        builder = builder.cacheControl(CacheControl.FORCE_NETWORK);
        OkHttpClient mClient = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .cache(null)
                .build();

        mClient.newCall(builder.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("111111111111111111111111111");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String myResponse = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(myResponse);
                        try {
                            JSONObject obj = new JSONObject(myResponse);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

        private HttpUrl APIURL(String url, Map<String, String> parameters){
            HttpUrl.Builder builder = new HttpUrl.Builder()
                    .scheme("https")
                    .host("backend.klickcheck.com");
            for(String urlPart: url.split("/"))
                builder.addPathSegment(urlPart);

            if (parameters != null) {
                for (Map.Entry<String, String> entry : parameters.entrySet()) {
                    builder.addQueryParameter(entry.getKey(), entry.getValue());
                }
            }
            return builder.build();
        }
    }

