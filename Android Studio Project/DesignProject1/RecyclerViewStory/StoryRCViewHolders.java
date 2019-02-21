package com.example.jswin.designproject1.RecyclerViewStory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jswin.designproject1.DisplayStoryActivity;
import com.example.jswin.designproject1.R;

public class StoryRCViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    // Pass the objects by vid
    public TextView myEmail;
    public LinearLayout myLayout;

    public StoryRCViewHolders(View itemView){
        super(itemView);
        itemView.setOnClickListener(this);
        myEmail = itemView.findViewById(R.id.email);
        myLayout = itemView.findViewById(R.id.layout);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), DisplayStoryActivity.class);
        Bundle bun = new Bundle();
        bun.putString("PalId", myEmail.getTag().toString());
        bun.putString("chatStory", myLayout.getTag().toString());

        intent.putExtras(bun);
        v.getContext().startActivity(intent);
    }
}
