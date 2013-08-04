/*
 * Purpose: Instantiates a reviewer object
 * Description: A model(AKA bean in Struts 2) to store the data retrieved from DB.
 * Date:14 July 2013
 * Author: Huimin
 * 
 */
package Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Reviewer {
    private int reviewerID;
    private String name;
    private String username;
    private String gender;
    private String location;
    private int numWrittenReviews;
    private String dateJoined;
    private String reviewerURL;
    private int duplicatedDate;

    public Reviewer(){}
    public Reviewer(int reviewerID,String name, String username, String gender,String location, 
            int numWrittenReviews,String dateJoined,String reviewerURL,int duplicatedDate) {
        
        setReviewerID(reviewerID);
        setName(name);
        setUsername(username);
        setGender(gender);
        setLocation(location);
        setNumWrittenReviews(numWrittenReviews);
        setDateJoined(dateJoined);
        setReviewerURL(reviewerURL);
        setDuplicatedDate(duplicatedDate);
    }

    public int getReviewerID() {
        return reviewerID;
    }

    public void setReviewerID(int reviewer_Id) {
        this.reviewerID = reviewer_Id;
    }

    public int getDuplicatedDate() {
        return duplicatedDate;
    }

    public void setDuplicatedDate(int duplicatedDate) {
        this.duplicatedDate = duplicatedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReviewerURL() {
        return reviewerURL;
    }

    public void setReviewerURL(String profileURL) {
        this.reviewerURL = profileURL;
    }

    public int getNumWrittenReviews() {
        return numWrittenReviews;
    }

    public void setNumWrittenReviews(int numWrittenReviews) {
        this.numWrittenReviews = numWrittenReviews;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    
    
}
