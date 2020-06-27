package com.example.locationmap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import com.example.locationmap.Retrofit.IRetrofit;
import com.example.locationmap.Retrofit.RetrofitApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.JsonObject;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class map extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap gMap=null;
    double latitude,longitude;
    MarkerOptions markerOptions;
    LatLng coords;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        latitude=getIntent().getDoubleExtra("latitude",0.0);
        longitude=getIntent().getDoubleExtra("longitude",0.00);

        SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        coords=new LatLng(latitude,longitude);
        markerOptions=new MarkerOptions()
                .position(coords);

        mapFragment.getMapAsync(this);


        timer=new Timer();




//        markerOptions.position()


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap=googleMap;
        float zoomLevel = (float) 16.0;
        TimerTask repeatTask=new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        JsonObject object=new JsonObject();
//                        object.addProperty("id","5ef7096349cc7e2fbc2db420");
//                        getLocation(object);
                        if(gMap!=null)
                        {
                            double oldLat=latitude;
                            double oldLong=longitude;

                            latitude +=1.0;
                            longitude +=1.0;

                            PolylineOptions options=new PolylineOptions().add(new LatLng(oldLat,oldLong)).add(new LatLng(latitude,longitude))
                                    .width((float)5.0).color(Color.RED).geodesic(true);
                            gMap.addPolyline(options);

                            LatLng coords=new LatLng(latitude,longitude);
//                            gMap.clear();
                            gMap.addMarker(new MarkerOptions().position(coords).icon(bitmapDescriptorfromVector(map.this,R.drawable.icon_map)));
                            float zoomLevel = (float) 7.0;
                            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coords,zoomLevel));

                        }
                    }
                });



            }
        };


        timer.schedule(repeatTask,0l,1000*30);
    }

    private BitmapDescriptor bitmapDescriptorfromVector(Context context, int vectorId)
    {
        Drawable vectorDrawable= ContextCompat.getDrawable(context,vectorId);
        vectorDrawable.setBounds(0,0,vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight());

        Bitmap bitmap=Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),vectorDrawable.getMinimumHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        vectorDrawable.draw(canvas);

        return BitmapDescriptorFactory.fromBitmap(bitmap);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        timer.cancel();
    }


    private void getLocation(JsonObject object)
    {

        RetrofitApiClient.getClient().create(IRetrofit.class).get_location(object).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful())
                {
                    JsonObject obj=response.body();

                    Toast.makeText(map.this,"Response: "+response.body(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(map.this,t.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

}
