package com.abhishek.internshipproject;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        if (isConnected()){
            intent = new Intent(MainActivity.this, DataShow.class);
        }else {
            Toast.makeText(this, "No Internet Connection. Please Connect with any Network then try again. Thank You", Toast.LENGTH_LONG).show();
            finish();
        }

        StartLogo startLogo = new StartLogo();
        startLogo.start();
    }

    private class StartLogo extends Thread{
        public void run(){
            try {
                sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            startActivity(intent);
            MainActivity.this.finish();
        }
    }

    public boolean isConnected() {
        boolean connected;
        try {
            ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}