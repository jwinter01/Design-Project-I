package com.example.jswin.designproject1.RecyclerViewReceiver;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.jswin.designproject1.R;

public class ReceiveRCViewHolders extends RecyclerView.ViewHolder {

    // Pass the objects by vid
    public TextView myEmail;
    public CheckBox myReceive;

    public ReceiveRCViewHolders(View itemView){
        super(itemView);
        myEmail = itemView.findViewById(R.id.email);
        myReceive = itemView.findViewById(R.id.receive);
    }

}
