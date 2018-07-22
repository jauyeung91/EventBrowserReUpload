package com.example.neo.storyfinderneo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.UUID;


/**
 * Created by neo on 11/28/2016.
 */

public class StoryFragment extends Fragment{

    private static final String ARG_STORY_ID = "story_id";

    private Story mStory;
    private TextView mDescriptionTextView;
    private TextView mNameTextView;
    private TextView mTitleField;
    private ImageView mImageView;
    //private LocationManager mgr=null;


    //LocationFind mylocation = new LocationFind();


    public  static StoryFragment newInstance(UUID storyId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_STORY_ID, storyId);


        StoryFragment fragment = new StoryFragment();
        fragment.setArguments(args);



        return fragment;


    }




    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID storyID = (UUID) getArguments().getSerializable(ARG_STORY_ID);
        mStory = StorySingleton.getInstance(getActivity()).getStory(storyID);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_story, container, false);

      /*
      * Get the story title, description, name from the XML file
      *
      * */

        //get and set the title
        mTitleField = (TextView) v.findViewById(R.id.story_title);
        mTitleField.setText(mStory.getmTitle() + " , ");

        //get and set the description
        mDescriptionTextView = (TextView) v.findViewById(R.id.story_desc);
        mDescriptionTextView.setText(mStory.getmDescription() + " , ");

        //get and set the name
        mNameTextView = (TextView) v.findViewById(R.id.story_name);
        mNameTextView.setText(mStory.getmName());


        //get and set the image
        mImageView = (ImageView) v.findViewById(R.id.story_image);
        mImageView.setImageBitmap(mStory.getmImage());


        return v;


    }


}
