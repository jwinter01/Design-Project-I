package com.example.jswin.designproject1.RecyclerViewFollow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jswin.designproject1.PalInformation;
import com.example.jswin.designproject1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

// This will connect everything and chooses
// what we place inside of the Recycler Pal view
// This CONTROLS it !! Important
public class FollowRCAdapter extends RecyclerView.Adapter<FollowRCViewHolders> {

    private List<FollowPalsObject> palList;
    private Context context;

    public FollowRCAdapter(List<FollowPalsObject> palList, Context context) {
        this.palList = palList;
        this.context = context;

    }

    @Override
    public FollowRCViewHolders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rcv_pals_item, null);
        FollowRCViewHolders rcv = new FollowRCViewHolders(layoutView);
        return rcv;
    }

    // Contain every detail of viewholder
    @Override
    // Save and remove from database functions
    public void onBindViewHolder(final FollowRCViewHolders rcViewHolders, int i) {
        rcViewHolders.myEmail.setText(palList.get(i).getPEmail());

        // If it contains: the user following this current user
        // The user is following, change text to following
        if(PalInformation.listFollowing.contains(palList.get(rcViewHolders.getLayoutPosition()).getPid())){
            rcViewHolders.myFollow.setText("following");
        }else{ // User is not following
            rcViewHolders.myFollow.setText("follow");
        }

        rcViewHolders.myFollow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String palId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                // If button says Follow then change to following (Want to follow)
                if(!PalInformation.listFollowing.contains(palList.get(rcViewHolders.getLayoutPosition()).getPid())){
                    rcViewHolders.myFollow.getText().equals("following");
                    FirebaseDatabase.getInstance().getReference().child("users").child(palId).child("following").child(palList.get(rcViewHolders.getLayoutPosition()).getPid()).setValue(true);
                }else{
                    FirebaseDatabase.getInstance().getReference().child("users").child(palId).child("follow").child(palList.get(rcViewHolders.getLayoutPosition()).getPid()).removeValue();

                }
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
