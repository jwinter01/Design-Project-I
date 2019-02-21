package com.example.jswin.designproject1.RecyclerViewFollow;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jswin.designproject1.R;

public class FollowRCViewHolders extends RecyclerView.ViewHolder {

    // Pass the objects by vid
    public TextView myEmail;
    public Button myFollow;

    public FollowRCViewHolders(View itemView){
        super(itemView);
        myEmail = itemView.findViewById(R.id.email);
        myFollow = itemView.findViewById(R.id.follow);
    }

}
