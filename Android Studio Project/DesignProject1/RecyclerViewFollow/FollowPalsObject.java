package com.example.jswin.designproject1.RecyclerViewFollow;

public class FollowPalsObject {

    // Pass email, Pass PalID
    private String email;
    private String pid;

    // Call this to pass new Pal to user
    // To later attach to the Recycler Pal View
    public FollowPalsObject(String email, String pid){

        this.email = email;
        this.pid = pid;

    }

    // Get and Set Pal Email
    public String getPEmail(){
        return email;
    }

    public void setPEmail(String email){
        this.pid = email;
    }

    // Get and Set Pal ID
    public String getPid(){
        return pid;
    }

    public void setPid(String pid){
        this.pid = pid;
    }



}
