package com.example.neo.storyfinderneo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import java.util.UUID;

public class StoryActivity extends SingleFragmentActivity {




    public static final String EXTRA_STORY_ID =
            "com.example.neo.storyfinderneo.story_ID";

    public static Intent newIntent(Context packageContext, UUID storyID){

        Intent intent = new Intent(packageContext, StoryActivity.class);
        intent.putExtra(EXTRA_STORY_ID, storyID);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        UUID storyID = (UUID)getIntent()
                .getSerializableExtra(EXTRA_STORY_ID);
        return StoryFragment.newInstance(storyID);
    }


}
