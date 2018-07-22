package com.example.neo.storyfinderneo;

import android.support.v4.app.Fragment;

/**
 * Created by neo on 11/28/2016.
 */

public class StoryListActivity extends SingleFragmentActivity{



    @Override
    protected Fragment createFragment(){
        return new StoryListFragment();
    }
}
