package com.example.neo.storyfinderneo;

import android.content.Intent;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by neo on 12/8/2016.
 */

public class StoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView mTitleTextView;
    private TextView mDescriptionTextView;
    private TextView mNameTextView;
    private ImageView mImageView;
    private TextView mImageURLView;
    private Story mStory;
    private StoryListFragment mFrag;



    public StoryHolder(View itemView, StoryListFragment mFrag) {
        super(itemView);
        itemView.setOnClickListener(this);

        mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_story_title_text_view);
        mDescriptionTextView = (TextView) itemView.findViewById(R.id.list_item_story_desc_text_view);
        mNameTextView = (TextView) itemView.findViewById(R.id.list_item_story_name_text_view);
        mImageURLView = (TextView) itemView.findViewById(R.id.list_item_story_imageURL);
        mImageView = (ImageView) itemView.findViewById(R.id.list_item_story_image_image_view);
        this.mFrag = mFrag;


    }

    public void bindStory(Story story) {
        mStory = story;

        //use a drawable instaed of bitmap for listview, rounded images are cool
        RoundedBitmapDrawable RBD = RoundedBitmapDrawableFactory.create(mFrag.getResources(),mStory.getmImage());
        RBD.setCornerRadius(10.0f);
        RBD.setAntiAlias(true);

        mTitleTextView.setText(mStory.getmTitle());
        mImageURLView.setText(mStory.getImageURL());
        mDescriptionTextView.setText(mStory.getmDescription().toString() + "\n\nlat = " + mStory.getLat() + "\nlon = " + mStory.getLon());
        mNameTextView.setText(mStory.getmName().toString());
        //mImageView.setImageBitmap(mStory.getmImage());
        mImageView.setImageDrawable(RBD);
    }

    @Override
    public void onClick(View v) {
        Intent intent = StoryActivity.newIntent(mFrag.getActivity(), mStory.getmUuid());
        mFrag.startActivity(intent);
    }
}
