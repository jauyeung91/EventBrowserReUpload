package com.example.neo.storyfinderneo;

import android.widget.Filter;

import java.util.ArrayList;

/**
 * Created by neo on 12/8/2016.
 */


public class SearchFilter extends Filter {

    private StoryAdapter adapter;
    private ArrayList<Story> filterList;

    public SearchFilter(ArrayList<Story> filterList, StoryAdapter adapter){
        this.adapter = adapter;
        this.filterList = filterList;
    }

    //Filter function start
    @Override
    protected FilterResults performFiltering(CharSequence arg){
        FilterResults results = new FilterResults();

        if(arg != null && arg.length() > 0){
            arg = arg.toString().toUpperCase();
            //Story the filtered cards
            ArrayList<Story> filteredStory = new ArrayList<>();

            for(int i=0; i< filterList.size(); i++){
                if(filterList.get(i).getmTitle().toUpperCase().contains(arg)){
                    //add card to filtered cards
                    filteredStory.add(filterList.get(i));
                }
            }
            results.count=filteredStory.size();
            results.values=filteredStory;
        }else{
            results.count=filterList.size();
            results.values=filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence arg, FilterResults results){
        adapter.mStory = (ArrayList<Story>) results.values;

        //REFRESH RECYCLERVIEW
        adapter.notifyDataSetChanged();
    }
}
