package com.example.neo.storyfinderneo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Created by neo on 11/28/2016.
 */

public class StorySingleton {
    private static StorySingleton sStoryLab;
    private ArrayList<Story> mStorys;
    private Story mStory;
    private List<Story> mstoryList;
    private static Context mContext;
    private RequestQueue mRequestQueue;

    public static StorySingleton getInstance(Context context){
        if(sStoryLab == null){
            sStoryLab = new StorySingleton(context);
        }
        return sStoryLab;
    }

    private StorySingleton(Context context) {
        mStorys = new ArrayList<>();

    //    Bitmap bImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.matrix);

    //    for (int i = 1; i < 5; i++) {
    //        Story story = new Story();

    //        if (i % 2 == 0) {
    //            story.setmTitle("Magic Card " + i);
    //        } else {
    //            story.setmTitle("Pokemon Card " + i);
    //        }
    //        story.setmDescription("Description " + i);
    //        story.setName("Name " + i);
    //        story.setmImage(bImage);

    //  mStorys.add(story);
    //}

        //mContext = context;
        //mRequestQueue = getRequestQueue();


    }


    public List<Story> getStory(){
        return mStorys;
    }



    public Story getStory(UUID id){

        for(Story story : mStorys){
            if(story.getmUuid().equals(id)){
                return story;
            }
        }
        return null;
    }



}
