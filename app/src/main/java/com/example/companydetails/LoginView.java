package com.example.companydetails;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginView extends Fragment {


    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public LoginView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_view, container, false);
        final EditText email = (EditText) view.findViewById(R.id.email);
        final EditText password  = (EditText) view.findViewById(R.id.password);
        Button submit  = (Button)view.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailValue  = email.getText().toString();
                String passwordValue = password.getText().toString();

                if(!emailValue.isEmpty() && !passwordValue.isEmpty()){
                    performTheApiRequest(emailValue, passwordValue);
                } else {
                    Toast.makeText(getActivity(), "Feilds should not be empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

    void performTheApiRequest(String emailValue, String passwordValue) {

        OkHttpClient client = new OkHttpClient();
        Map<String, String> body = new HashMap<>();
        body.put("email", emailValue);
        body.put("password", passwordValue);
        RequestBody requestBody = RequestBody.create(JSON, body.toString());

        FormBody.Builder formEncodingBuilder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : body.entrySet())
            formEncodingBuilder.add(entry.getKey(), entry.getValue());

        Request.Builder requestBuilder = new Request.Builder()
                .url("https://backend.klickcheck.com/api/login")
                .post(formEncodingBuilder.build());

        client.newCall(requestBuilder.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Failed");
                call.cancel();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                final String myResponse = response.body().string();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(myResponse);
                        try {
                            JSONObject obj = new JSONObject(myResponse);
                            JSONObject data = obj.getJSONObject("data");
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            intent.putExtra("token", data.getString("token"));
                            intent.putExtra("companyId",data.getJSONObject("user").getJSONObject("company").getInt("id"));
                            getActivity().startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }

}
