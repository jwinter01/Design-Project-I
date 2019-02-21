package com.example.jswin.designproject1.RecyclerViewStory;

public class StoryPalsObject {

    // Pass email, Pass PalID
    private String email;
    private String pid;
    private String chatStory;

    // Call this to pass new Pal to user
    // To later attach to the Recycler Pal View
    public StoryPalsObject(String email, String pid, String chatStory){

        this.email = email;
        this.pid = pid;
        this.chatStory = chatStory;


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

    // Get and Set to chat OR story
    public String getChatStory(){
        return pid;
    }

    public void setChatStory(String pid){
        this.pid = pid;
    }

    @Override
    public boolean equals(Object obj) {
        boolean same = false;
        if(obj != null && obj instanceof StoryPalsObject){
            same = this.pid == ((StoryPalsObject) obj).pid;
        }

        return same;

    }

    @Override
    public int hashCode() {

        int result = 17;
        result = 31 * result + (this.pid == null ? 0 : this.pid.hashCode());
        return result;
    }
}
