package com.example.apptivity;

public class cards {
    private String actID;
    private String name;
    private String imURL;

    public cards(String actID, String name, String imURL){
        this.actID = actID;
        this.name = name;
        this.imURL = imURL;
    }
    public String getUserID(){
        return actID;
    }
    public void setUserID(String userID){
        this.actID = userID;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getImURL(){
        return imURL;
    }
    public void setImURL(String imURL){
        this.imURL = imURL;
    }
}
