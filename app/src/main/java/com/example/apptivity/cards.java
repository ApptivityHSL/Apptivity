package com.example.apptivity;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Cards.
 */
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

    /**
     * Instantiates a new Cards.
     *
     * @param actID       the act id
     * @param name        the name
     * @param imURL       the im url
     * @param description the description
     * @param open        the open
     * @param closed      the closed
     * @param location    the location
     * @param street      the street
     * @param website     the website
     * @param houseNumber the house number
     * @param budget      the budget
     * @param phoneNumber the phone number
     * @param postal      the postal
     * @param mailAddress the mail address
     * @param tags        the tags
     */
    public cards(final String actID, final String name, final String imURL,
                 final String description, final String open,
                 final String closed, final String location, final String street,
                 final String website, final String houseNumber,
                 final String budget, final String phoneNumber, final String postal,
                 final String mailAddress,
                 final ArrayList<String> tags) {
        this.actID = actID;
        this.name = name;
        int iend = imURL.indexOf(",");
        String subString = imURL;
        if (iend != -1) {
            subString = imURL.substring(0, iend) + "]";
        }
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


    /**
     * Gets act id.
     *
     * @return the act id
     */
    public String getActID() {
        return actID;
    }
    //public void setActID(String actID) { this.actID = actID; }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets im url.
     *
     * @return the im url
     */
    public String getImURL() {
        return imURL;
    }
    //public void setImURL(String imURL) { this.imURL = imURL; }

    /**
     * Gets budget.
     *
     * @return the budget
     */
    public String getBudget() {
        return budget;
    }
    //public void setBudget(String budget) { this.budget = budget; }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    //public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    /**
     * Gets postal.
     *
     * @return the postal
     */
    public String getPostal() {
        return postal;
    }
    //public void setPostal(String postal) { this.postal = postal; }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    //public void setDescription(String description) { this.description = description; }

    /**
     * Gets open.
     *
     * @return the open
     */
    public String getOpen() {
        return open;
    }
    //public void setOpen(String open) { this.open = open; }

    /**
     * Gets closed.
     *
     * @return the closed
     */
    public String getClosed() {
        return closed;
    }
    //public void setClosed(String closed) { this.closed = closed; }

    /**
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }
    //public void setLocation(String location) { this.location = location; }

    /**
     * Gets street.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }
    //public void setStreet(String street) { this.street = street; }

    /**
     * Gets website.
     *
     * @return the website
     */
    public String getWebsite() {
        return website;
    }
    //public void setWebsite(String website) { this.website = website; }

    /**
     * Gets house number.
     *
     * @return the house number
     */
    public String getHouseNumber() {
        return houseNumber;
    }
    //public void setHouseNumber(String houseNumber) { this.houseNumber = houseNumber; }

    /**
     * Gets mail address.
     *
     * @return the mail address
     */
    public String getMailAddress() {
        return mailAddress;
    }
    //public void setMailAddress(String mailAddress) { this.mailAddress = mailAddress; }

    /**
     * Gets tags.
     *
     * @return the tags
     */
    public List<String> getTags() {
        return tags;
    }
    //public void setTags(List<String> tags) { this.tags = tags; }
    //public void addTags(String tag) { this.tags.add(tag);}
}
