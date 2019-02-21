package com.example.jswin.designproject1.RecyclerViewReceiver;

public class ReceivePalsObject {

    // Pass email, Pass PalID
    private String email;
    private String pid;
    // Whenever user clicks checkbox, then this changes to opposite
    private Boolean receive;

    // Call this to pass new Pal to user
    // To later attach to the Recycler Pal View
    public ReceivePalsObject(String email, String pid, Boolean receive){

        this.email = email;
        this.pid = pid;
        this.receive = receive;

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

    public Boolean getReceive(){
        return receive;
    }

    public void setReceive(Boolean receive){
        this.receive = receive;
    }



}
