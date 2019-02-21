package com.example.jswin.designproject1.RecyclerViewReceiver;

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
public class ReceiveRCAdapter extends RecyclerView.Adapter<ReceiveRCViewHolders> {

    private List<ReceivePalsObject> palList;
    private Context context;

    public ReceiveRCAdapter(List<ReceivePalsObject> palList, Context context) {
        this.palList = palList;
        this.context = context;

    }

    @Override
    public ReceiveRCViewHolders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rcv_receiver_item, null);
        ReceiveRCViewHolders rcv = new ReceiveRCViewHolders(layoutView);
        return rcv;
    }

    // Contain every detail of viewholder
    @Override
    // Save and remove from database functions
    public void onBindViewHolder(final ReceiveRCViewHolders rcViewHolders, int i) {
        rcViewHolders.myEmail.setText(palList.get(i).getPEmail());

        rcViewHolders.myReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean receiveState = !palList.get(rcViewHolders.getLayoutPosition()).getReceive();
                palList.get(rcViewHolders.getLayoutPosition()).setReceive(receiveState);
            }
        });

    }

    // Return list of pals
    @Override
    public int getItemCount() {
        return this.palList.size();
    }

    //
}
