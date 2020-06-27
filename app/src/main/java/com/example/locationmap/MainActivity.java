package com.example.locationmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.locationmap.Retrofit.IRetrofit;
import com.example.locationmap.Retrofit.RetrofitApiClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.JsonObject;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(MainActivity.this,map.class);
        intent.putExtra("latitude",40.6971494);
        intent.putExtra("longitude",-74.2598655);

        startActivity(intent);


        //call getLocation after 1 minute

//        JsonObject object=new JsonObject();
//        object.addProperty("id","5ef7096349cc7e2fbc2db420");
//
//         timer=new Timer();
//        TimerTask minuteTask=new TimerTask() {
//            @Override
//            public void run() {
//
//                getLocation(object);
//            }
//        };
//        timer.schedule(minuteTask,0l,1000*70);  //after every one minute

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
