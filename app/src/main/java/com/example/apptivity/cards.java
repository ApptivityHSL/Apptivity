package com.example.apptivity;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class cards {
    private String actID;
    private String name;
    private String imURL;
    private String description;
    private String open;
    private String closed;
    private String location;
    private String street;
    private String website;
    private String houseNumber;
    private String budget;
    private String phoneNumber;
    private String postal;
    private String mailAddress;
    private List<String> tags;

    public cards(String actID, String name, String imURL, String description, String open, String closed, String location, String street, String website, String houseNumber,
                 String budget, String phoneNumber, String postal, String mailAddress, ArrayList<String> tags) {
        Log.d("noSwipe", imURL);
        this.actID = actID;
        this.name = name;
        int iend = imURL.indexOf(",");
        String subString = imURL;
        if (iend != -1)
        {
            subString= imURL.substring(0 , iend)+"]";
        }
        //subString.replace("\"", "").replace("[", "").replace("]", "");
        Log.d("jArray", subString);
        this.imURL = subString;
        this.description = description;
        this.open = open;
        this.closed = closed;
        this.location = location;
        this.street = street;
        this.website = website;
        this.houseNumber = houseNumber;
        this.budget = budget;
        this.phoneNumber = phoneNumber;
        this.postal = postal;
        this.mailAddress = mailAddress;
        this.tags = tags;
    }


    public String getActID(){
        return actID;
    }
    public void setActID(String actID){
        this.actID = actID;
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

    public String getBudget() { return budget; }
    public void setBudget(String budget) { this.budget = budget; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPostal() { return postal; }
    public void setPostal(String postal) { this.postal = postal; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getOpen() { return open; }
    public void setOpen(String open) { this.open = open; }

    public String getClosed() { return closed; }
    public void setClosed(String closed) { this.closed = closed; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    public String getHouseNumber() { return houseNumber; }
    public void setHouseNumber(String houseNumber) { this.houseNumber = houseNumber; }

    public String getMailAddress() { return mailAddress; }
    public void setMailAddress(String mailAddress) { this.mailAddress = mailAddress; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
    public void addTags(String tag) { this.tags.add(tag);}
}
