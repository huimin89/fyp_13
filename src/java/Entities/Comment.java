/*
 * Purpose: Instantiates a comment object to annotate potential spam review or spammer
 * Description: A model(AKA bean in Struts 2) to store the data retrieved from DB.
 * Date:14 July 2013
 * Author: Huimin
 * 
 */
package Entities;

import java.util.StringTokenizer;


public class Comment {
    private String reason;
    private String formattedReason;
    private String otherInfo;
    private String spamIndicator;
    private int commentID;
    private int userID;
    private Reviewer reviewer;
    private Review review;
    
    public Comment(String reason, String otherInfo,String spamIndicator,int user,Review review,Reviewer reviewer){
        this.reason = reason;
        this.spamIndicator = spamIndicator;
        this.otherInfo = otherInfo;
        this.userID = user;
        this.review= review;
        this.commentID = 0;
        this.reviewer= reviewer; 
    };
    public Comment(){};
    
    public Comment(String reason, String otherInfo,String spamIndicator,int user,int reviewID,int reviewerID){
        review = new Review();
        reviewer = new Reviewer();
        
        this.reason = reason;
        this.spamIndicator = spamIndicator;
        this.otherInfo = otherInfo;
        this.userID = user;
        this.review.setReviewID(reviewID);
        this.reviewer.setReviewerID(reviewerID);
        this.commentID = 0; 
    };

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {    
        this.reason = reason;
        setFormattedReason(reason);
        
    }

    public String getFormattedReason() {
        return formattedReason;
    }

    public void setFormattedReason(String reason) {
        formattedReason = "";
        StringTokenizer tokenizer = new StringTokenizer(reason,":");
        while (tokenizer.hasMoreTokens())
        {
            String token = tokenizer.nextToken().trim();
            int reasonID = 0;
            try{
                reasonID = Integer.parseInt(token);
            }
            catch (NumberFormatException e){
                continue;
            }
            switch (reasonID){
                case 1:
                    formattedReason += "-Tones of reviews(Eg:Excessive praises,CAPS,Marketing description,etc)<br/>";
                    break;
                case 2:
                    formattedReason += "-Same or close reviewed dates & frequency of reviews<br/>";
                    break;
                case 3:
                    formattedReason += "-No downside<br/>";
                    break;
                case 4:
                    formattedReason += "-Single account review<br/>";
                    break;
                case 5:
                    formattedReason += "-Repetitive brand names<br/>";
                    break;
                case 6:
                    formattedReason += "-Discount code, promote other hotel<br/>";
                    break;
                case 7:
                    formattedReason += "-Target on specific hotel<br/>";
                    break;
                case 8:
                    formattedReason += "-Inter chained reviews at different locations<br/>";
                    break;
                case 9:
                    formattedReason += "-Similar or duplicated review<br/>";
                    break;
                case 10:
                    formattedReason += "-Long winded explanation,bullshit,Inconsistency<br/>";
                    break;
                case 11:
                    formattedReason += "-Others :";
                    formattedReason += reason.substring(reason.lastIndexOf(":") + 1);
                    break;
                default:
                    break;
            }
        }
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getSpamIndicator() {
        return spamIndicator;
    }

    public void setSpamIndicator(String spamIndicator) {
        this.spamIndicator = spamIndicator;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUser(int user) {
        this.userID = user;
    }

    public Reviewer getReviewer() {
        return reviewer;
    }

    public void setReviewer(Reviewer reviewer) {
        this.reviewer = reviewer;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
    
}
