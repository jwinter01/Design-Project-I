package com.example.jswin.designproject1.RecyclerViewStory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jswin.designproject1.R;

import java.util.List;

// This will connect everything and chooses
// what we place inside of the Recycler Pal view
// This CONTROLS it !! Important
public class StoryRCAdapter extends RecyclerView.Adapter<StoryRCViewHolders> {

    private List<StoryPalsObject> palList;
    private Context context;

    public StoryRCAdapter(List<StoryPalsObject> palList, Context context) {
        this.palList = palList;
        this.context = context;


    }

    @Override
    public StoryRCViewHolders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rcv_story_item, null);
        StoryRCViewHolders rcv = new StoryRCViewHolders(layoutView);
        return rcv;
    }

    // Contain every detail of viewholder
    @Override
    // Save and remove from database functions
    public void onBindViewHolder(final StoryRCViewHolders rcViewHolders, int i) {
        rcViewHolders.myEmail.setText(palList.get(i).getPEmail());
        // When click some users that have story this allows
        // What the user ID was clicked (NOT JUST EMAIL)
        rcViewHolders.myEmail.setTag(palList.get(i).getPid());
        // Chat or Story
        rcViewHolders.myLayout.setTag(palList.get(i).getChatStory());

        }

    // Return list of pals
    @Override
    public int getItemCount() {
        return this.palList.size();
    }

    //
}
