package com.example.companydetails;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean loginStatus = preferences.getBoolean("loginStatus", false);
        if(loginStatus) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
            LoginView loginView = new LoginView();
            transaction.add(R.id.container,loginView,"loginView");
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

}
