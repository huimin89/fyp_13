/*
 * Purpose: Instantiates a past review object
 * Description: A model(AKA bean in Struts 2) to store the data retrieved from DB.
 * Date:14 July 2013
 * Author: Huimin
 * 
 */
package Entities;

public class PastReview {
    private int pastReviewID;
    private Reviewer reviewer;
    private String title;
    private String date;
    private String content;
    private String rating;
    private int numHelpfulVotes;
    private String pastReviewURL;
    

    public int getPastReviewID() {
        return pastReviewID;
    }

    public void setPastReviewID(int pastReviewID) {
        this.pastReviewID = pastReviewID;
    }

    public Reviewer getReviewer() {
        return reviewer;
    }

    public void setReviewer(Reviewer reviewer) {
        this.reviewer = reviewer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getNumHelpfulVotes() {
        return numHelpfulVotes;
    }

    public void setNumHelpfulVotes(int numHelpfulVotes) {
        this.numHelpfulVotes = numHelpfulVotes;
    }

    public String getPastReviewURL() {
        return pastReviewURL;
    }

    public void setPastReviewURL(String pastReviewURL) {
        this.pastReviewURL = pastReviewURL;
    }
    
}
