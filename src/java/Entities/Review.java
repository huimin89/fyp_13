/*
 * Purpose: Instantiates a review object
 * Description: A model(AKA bean in Struts 2) to store the data retrieved from DB.
 * Date:14 July 2013
 * Author: Huimin
 * 
 */
package Entities;

import java.util.Date;

public class Review{
    private Reviewer reviewer;
    private Hotel hotel;
    private int reviewID;
    private String rating;
    private String title;
    private String content;
    private String date;
    private int numHelpfulVotes;
    private String reviewURL;
    
    private boolean markByUser;

    public Review(){};
    public Review(Reviewer reviewer, Hotel hotel,int reviewID,String rating, 
            String title, String content,String date,int numHelpfulVotes,String reviewURL) {
        setReviewer(reviewer);
        setHotel(hotel);
        setReviewID(reviewID);
        setRating(rating);
        setTitle(title);
        setContent(content);
        setDate(date);
        setNumHelpfulVotes(numHelpfulVotes);
        setReviewURL(reviewURL);
        setMarkByUser(false);
    }
    
    public Reviewer getReviewer() {
        return reviewer;
    }

    public void setReviewer(Reviewer reviewer) {
        this.reviewer = reviewer;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int review_Id) {
        this.reviewID = review_Id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumHelpfulVotes() {
        return numHelpfulVotes;
    }

    public void setNumHelpfulVotes(int numHelpfulVote) {
        this.numHelpfulVotes = numHelpfulVote;
    }

    public String getReviewURL() {
        return reviewURL;
    }

    public void setReviewURL(String reviewURL) {
        this.reviewURL = reviewURL;
    }   
    
    public boolean isMarkByUser() {
        return markByUser;
    }

    public void setMarkByUser(boolean markByUser) {
        this.markByUser = markByUser;
    }
    
}
