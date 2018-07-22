package com.example.neo.storyfinderneo;


import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.renderscript.ScriptGroup;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static android.os.ParcelFileDescriptor.MODE_WORLD_READABLE;

/**
 * Created by neo on 11/28/2016.
 */

public class StoryListFragment extends Fragment {
    private int MY_PERMISSIONS_REQUEST_COARSE_LOCATION = 11;
    private RecyclerView mStoryRecyclerView;
    private StoryAdapter mAdapter;
    private SearchView mStorySearchView;
    double lat,lng;
    private LocationFind gps;
    Context mContext = this.getContext();
    //double longitude,latitude = 0.0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story_list, container, false);



        mStorySearchView = (SearchView) view.findViewById(R.id.story_search_view);
        mStoryRecyclerView = (RecyclerView) view
                .findViewById(R.id.story_recycler_view);
        mStoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mStorySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query){
                mAdapter.getFilter().filter(query);
                return false;
            }


            @Override
            public boolean onQueryTextChange(String query){
                //this will filter as you type

                mAdapter.getFilter().filter(query);
                return false;
            }
        });

        getData();

        return view;
    }




    private void getData(){



        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://api.meetup.com/find/groups?&key=36364169732121155437801d7d5953d&page=80",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        parseData(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

    private void parseData(JSONArray array){
        //parse the JSON data

        StorySingleton storyLab = StorySingleton.getInstance(getActivity());
        ArrayList<Story> cards = (ArrayList<Story>)storyLab.getStory();
        String urlSauce = "";

        gps =new LocationFind(getContext().getApplicationContext());
        if (gps.canGetLocation()){
            lng = gps.getLongitude();
            lat = gps.getLatitude();
            Toast.makeText(getContext(),"Longitude:"+Double.toString(lng)+"\nLatitude:"+Double.toString(lat),Toast.LENGTH_SHORT).show();
        }
        else
        { gps.showSettingsAlert();}

       //LocationFind mylocation= new LocationFind(getContext().getApplicationContext());


       // latitude = mylocation.getLatitude();
        //longitude = mylocation.getLongitude();

        if(mAdapter == null){
            for(int i =0; i< array.length(); i++){
            Story mStory = new Story();



            JSONObject json = null;
            try {

                json = (JSONObject) array.get(i);
                mStory.setmTitle(json.getString("name"));
                mStory.setmDescription(json.getString("city"));
                mStory.setName(json.getString("name"));
                //mStory.setLat();
                mStory.setLon(mStory.getLon());
                mStory.setLat(distance(latitude,longitude,Double.parseDouble(json.getString("lat")),Double.parseDouble(json.getString("lon"))));
                //mStory.setLon(lng)

                //get nested array Json Objects
                JSONObject imageURL = json.getJSONObject("group_photo");
                mStory.setImageURL(imageURL.getString("photo_link"));
                urlSauce = imageURL.getString("photo_link");


                //set strict mode to all
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                //set the image
                mStory.setmImage(loadImageFromWeb(urlSauce));


            }catch(JSONException e ){
                e.printStackTrace();
                Log.e(TAG, "couldn't parse " + array.toString());
            }//end catch

            cards.add(mStory);
        }

        mAdapter = new StoryAdapter(getContext(),cards, this);
        mStoryRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }
    }


    private Bitmap loadImageFromWeb(String url)
    {
        try
        {
            InputStream is = (InputStream) new URL(url).getContent();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap d = BitmapFactory.decodeStream(is,null,options);
            return d;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }





    private double distance(double lat1, double long1, double lat2, double long2) {
        double earthRadius = 3958.75;

        double mLat = Math.toRadians(lat2 - lat1);
        double mLong = Math.toRadians(long2 - long1);

        double sindLat = Math.sin(mLat / 2);
        double sindLong = Math.sin(mLong / 2);

        double a = Math.pow(sindLat, 2) + Math.pow(sindLong, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double dist = earthRadius * c;

        return dist;
    }



}


//TODO:


