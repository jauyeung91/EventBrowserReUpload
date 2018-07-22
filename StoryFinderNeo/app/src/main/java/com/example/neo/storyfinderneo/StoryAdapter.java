package com.example.neo.storyfinderneo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

/**
 * Created by neo on 12/8/2016.
 */



public class StoryAdapter extends RecyclerView.Adapter<StoryHolder> implements Filterable {

    private Context c;

    protected ArrayList<Story> mStory;
    private ArrayList<Story> filterList;
    private SearchFilter filter;
    private StoryListFragment listFrag;

    public StoryAdapter(Context cont, ArrayList<Story> mStory,StoryListFragment listFrag) {
        this.c = cont;
        this.mStory = mStory;
        this.filterList = mStory;
        this.listFrag = listFrag;
    }


    @Override
    public StoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(listFrag.getActivity());
        View view = layoutInflater.inflate(R.layout.list_item_story, parent, false);
        return new StoryHolder(view,listFrag);

    }

    @Override
    public void onBindViewHolder(StoryHolder holder, int position) {
        Story card = mStory.get(position);
        holder.bindStory(card);
    }

    @Override
    public int getItemCount() {
        return mStory.size();
    }

    @Override
    public Filter getFilter(){
        //RETURN FILTERED OBJECT
        if(filter == null){
            filter = new SearchFilter(filterList,this);
        }
        return filter;
    }
}
