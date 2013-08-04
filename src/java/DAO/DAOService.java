/*
 * Purpose: List of function related to MySql Query and its execution
 * Description: All SQL queries used to retrieve data from db 
 *              are stored in this file. Only DAOService object
 *              handles DB connection 
 * Date:14 July 2013
 * Author: Huimin
 * 
 */
package DAO;

import Entities.*;
import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedList;


public class DAOService {
    
    private DB db;
    private String sql; 
    private ResultSet rs;
    LinkedList<Review> result = new LinkedList<Review>();
    
    public DAOService(){
        //singleton pattern to avoid choking server
        db = DB.getInstance();
    }
    public LinkedList<Hotel> getAllHotels(){
        
        LinkedList<Hotel> listOfHotels = new LinkedList<Hotel>();
        String sql = "SELECT HotelID,Name,AvgRating FROM hotel Order By Name";
        try{
            rs = db.getStatement().executeQuery(sql);
            while (rs.next())
            {
                Hotel hotel = new Hotel();
                //hotel.setHotelURL(rs.getString("HotelURL"));
                hotel.setAvgRating(rs.getString("AvgRating"));
                //hotel.setLocation(rs.getString("Location"));
                hotel.setName(rs.getString("Name"));
                //hotel.setTotalNumReviews(rs.getInt("TotalNumReview"));
                //hotel.setStatSummary(rs.getString("StatSummary"));
                hotel.setHotelID(rs.getInt("HotelID"));
                listOfHotels.add(hotel);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            closeConn();
        }
        return listOfHotels;
    }
    
    public LinkedList<Reviewer> getAllReviewers(){
        Reviewer reviewer;
        LinkedList<Reviewer> listOfReviewers = new LinkedList<Reviewer>();
        
        String sql = "SELECT * FROM reviewer ORDER BY username";
        try{
            rs = db.getStatement().executeQuery(sql);
            while (rs.next())
            {
                reviewer = new Reviewer();
                reviewer.setDateJoined(rs.getString("DateJoined"));
                reviewer.setGender(rs.getString("Gender"));
                reviewer.setLocation(rs.getString("Location"));
                reviewer.setName(rs.getString("Name"));
                reviewer.setReviewerID(rs.getInt("ReviewerID"));
                reviewer.setNumWrittenReviews(rs.getInt("NumWrittenReview"));
                reviewer.setUsername(rs.getString("Username"));
                reviewer.setReviewerURL(rs.getString("ReviewerURL"));
                reviewer.setDuplicatedDate(rs.getInt("DuplicatedDate"));
                listOfReviewers.add(reviewer);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            closeConn();
        }
        return listOfReviewers;
    }
    
    public LinkedList<Review> getAllReviews(int HotelID){
        LinkedList<Review> result = new LinkedList<Review>();
        LinkedList<Reviewer> reviewerList = new LinkedList<Reviewer>();
        sql = "SELECT * FROM review,reviewer WHERE review.HotelID = " + HotelID 
                + " AND review.ReviewerID = reviewer.ReviewerID";
        try{
            rs = db.getStatement().executeQuery(sql);
            while (rs.next())
            {
                Review r = new Review();
                r.setReviewID(rs.getInt("ReviewID"));
                r.setContent(rs.getString("Content"));
                r.setDate(rs.getString("review.Date"));
                r.setNumHelpfulVotes(rs.getInt("review.NumHelpfulVotes"));
                r.setReviewURL(rs.getString("review.ReviewURL"));
                r.setRating(rs.getString("review.Rating"));
                r.setTitle(rs.getString("review.Title"));
                
                Reviewer reviewer;
                int reviewerID = rs.getInt("review.ReviewerID");
                
                //check if reviewer didnt appear before,
                //if nv, create new reviewer object else used back
                reviewer = checkDuplicatedReviewer(reviewerList,reviewerID);
                //System.out.println(rs.getString("DateJoined")+ rs.getString("Location"));
                if (reviewer == null){
                    reviewer = new Reviewer();
                    reviewer.setDateJoined(rs.getString("DateJoined"));
                    reviewer.setGender(rs.getString("Gender"));
                    reviewer.setLocation(rs.getString("reviewer.Location"));
                    reviewer.setName(rs.getString("Name"));
                    reviewer.setReviewerID(reviewerID);
                    reviewer.setNumWrittenReviews(rs.getInt("reviewer.NumWrittenReview"));
                    reviewer.setUsername(rs.getString("reviewer.Username"));
                    reviewer.setReviewerURL(rs.getString("ReviewerURL"));
                    reviewerList.add(reviewer);
                }
                    
                r.setReviewer(reviewer);
                result.add(r);
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            closeConn();
        }
        return result;
    }
    
    public LinkedList<Review> getAllReviewsByReviewer(int reviewerID){
        LinkedList<Review> result = new LinkedList<Review>();
        LinkedList<Hotel> hotelList = new LinkedList<Hotel>();
        sql = "SELECT * FROM review,hotel WHERE ReviewerID = " + reviewerID + " AND hotel.HotelID = review.HotelID" ;
        System.out.println(sql);
        try{
            rs = db.getStatement().executeQuery(sql);
            while (rs.next())
            {
                Review r = new Review();
                r.setReviewID(rs.getInt("ReviewID"));
                r.setContent(rs.getString("Content"));
                r.setDate(rs.getString("review.Date"));
                r.setNumHelpfulVotes(rs.getInt("review.NumHelpfulVotes"));
                r.setReviewURL(rs.getString("review.ReviewURL"));
                r.setRating(rs.getString("review.Rating"));
                r.setTitle(rs.getString("review.Title"));
                Hotel hotel;
                int hotelID = rs.getInt("hotel.HotelID");
                hotel = checkDuplicatedHotel(hotelList,hotelID);

                if (hotel == null){
                    hotel = new Hotel();
                    hotel.setHotelID(hotelID);
                    hotel.setName(rs.getString("hotel.Name"));
                    hotelList.add(hotel);
                }
                r.setHotel(hotel);
                result.add(r);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            closeConn();
        }
        
        return result;
    }
    
    public LinkedList<PastReview> getPastReviews(int reviewerID){
        LinkedList<PastReview> result = new LinkedList<PastReview>();
        PastReview pastReview;
        sql = "SELECT * FROM pastReview where ReviewerID = " + reviewerID;
        try{
            rs = db.getStatement().executeQuery(sql);
            while (rs.next())
            {
                pastReview = new PastReview();
                pastReview.setContent(rs.getString("Content"));
                pastReview.setDate(rs.getString("Date"));
                pastReview.setNumHelpfulVotes(rs.getInt("NumHelpfulVotes"));
                pastReview.setRating(rs.getString("Rating"));
                pastReview.setTitle(rs.getString("Title"));
                pastReview.setPastReviewURL(rs.getString("PastReviewURL"));
                pastReview.setPastReviewID(rs.getInt("PastReviewID"));
                result.add(pastReview);
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            closeConn();
        }
        return result;
    }
    
    public LinkedList<Buddy> getAllBuddies(){
        LinkedList<Buddy> buddyList = new LinkedList<Buddy>();
        Buddy buddy;
        String sql = "SELECT * FROM buddy";
        try{
           rs = db.getStatement().executeQuery(sql);
           while (rs.next())
           {
               buddy = new Buddy();
               buddy.setReviewerID(rs.getInt("ReviewerID"));
               buddy.setReviewerID_2(rs.getInt("ReviewerID_2"));
               buddy.setHotelList(rs.getString("HotelList"));
               buddyList.add(buddy);
           }
        }catch(Exception e){
            e.printStackTrace();           
        } 
        finally{
            closeConn();
        }
        return buddyList;
    }
    
    public LinkedList<Comment> getAllComments(int userID){
        LinkedList<Comment> commentList = new LinkedList<Comment>();
        LinkedList<Reviewer> reviewerList = new LinkedList<Reviewer>();
        Comment comment;
        Reviewer reviewer;
        String sql = "SELECT * FROM comment c,reviewer rr,review r WHERE UserID =" + userID +
        " AND c.reviewID = r.reviewID AND c.reviewerID = rr.reviewerID ORDER BY rr.reviewerID";
        //System.out.println(sql);
        try{
            rs = db.getStatement().executeQuery(sql);
            while (rs.next())
            {
                comment = new Comment();
                comment.setCommentID(rs.getInt("CommentID"));
                comment.setReason(rs.getString("Reason"));
                comment.setOtherInfo(rs.getString("OtherInfo"));
                comment.setSpamIndicator(rs.getString("SpamIndicator"));
                
                Review r = new Review();
                r.setReviewID(rs.getInt("ReviewID"));
                r.setContent(rs.getString("Content"));
                r.setDate(rs.getString("Date"));
                r.setNumHelpfulVotes(rs.getInt("NumHelpfulVotes"));
                r.setReviewURL(rs.getString("ReviewURL"));
                r.setRating(rs.getString("Rating"));
                r.setTitle(rs.getString("Title"));
                
                comment.setReview(r);
                int reviewerID = rs.getInt("ReviewerID");
                
                //check if reviewer didnt appear before,
                //if nv, create new reviewer object else used back
                reviewer = checkDuplicatedReviewer(reviewerList,reviewerID);
                //System.out.println(rs.getString("DateJoined")+ rs.getString("Location"));
                if (reviewer == null){
                    reviewer = new Reviewer();
                    reviewer.setDateJoined(rs.getString("DateJoined"));
                    reviewer.setGender(rs.getString("Gender"));
                    reviewer.setLocation(rs.getString("Location"));
                    reviewer.setName(rs.getString("Name"));
                    reviewer.setReviewerID(reviewerID);
                    reviewer.setNumWrittenReviews(rs.getInt("NumWrittenReview"));
                    reviewer.setUsername(rs.getString("Username"));
                    reviewer.setReviewerURL(rs.getString("ReviewerURL"));
                    reviewerList.add(reviewer);
                }
                    
                comment.setReviewer(reviewer);
                commentList.add(comment);
               
            }
         }catch(Exception e){
             e.printStackTrace();           
         }   
        finally{
            closeConn();
        }
        return commentList;
    }
    
    public String getUsername(int userID){
        String sql ="SELECT Username FROM user where UserID =" + userID;
        String name= "";
        try{
            rs = db.getStatement().executeQuery(sql);
            while (rs.next()){
                name=rs.getString("Username");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            closeConn();
        }
        return name;
    }
    //get buddies of a specific reviewer only. 
    public LinkedList<Buddy> getBuddies(int reviewerID){
        LinkedList<Buddy> buddyList = new LinkedList<Buddy>();
        Buddy buddy;
        String sql = "SELECT * FROM buddy WHERE ReviewerID = " + reviewerID + " OR ReviewerID_2 = " + reviewerID;
        try{
            rs = db.getStatement().executeQuery(sql);
            while (rs.next())
            {
                buddy = new Buddy();
                buddy.setReviewerID(rs.getInt("ReviewerID"));
                buddy.setReviewerID_2(rs.getInt("ReviewerID_2"));
                buddy.setHotelList(rs.getString("HotelList"));
                buddyList.add(buddy);
            }
         }catch(Exception e){
             e.printStackTrace();           
         }    
        finally{
            closeConn();
        }
        return buddyList;
        
    }
    
    public Hotel getHotel(int hotelID){
        Hotel hotel = new Hotel();
        
        String sql = "SELECT * FROM hotel WHERE HotelID = " + hotelID;
        try{
            rs = db.getStatement().executeQuery(sql);
            while (rs.next())
            {
                hotel.setHotelURL(rs.getString("HotelURL"));
                hotel.setAvgRating(rs.getString("AvgRating"));
                hotel.setLocation(rs.getString("Location"));
                hotel.setName(rs.getString("Name"));
                hotel.setTotalNumReviews(rs.getInt("TotalNumReview"));
                hotel.setStatSummary(rs.getString("StatSummary"));
                hotel.setHotelID(hotelID);
                hotel.setUpdatedNumReviews(rs.getInt("UpdatedNumReview"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            closeConn();
        }
        
        return hotel;
    }
    
    public Reviewer getReviewer(int reviewerID){
        Reviewer reviewer = new Reviewer();
        
        String sql = "SELECT * FROM reviewer WHERE ReviewerID = " + reviewerID;
        try{
            rs = db.getStatement().executeQuery(sql);
            while (rs.next())
            {
                reviewer.setDateJoined(rs.getString("DateJoined"));
                reviewer.setGender(rs.getString("Gender"));
                reviewer.setLocation(rs.getString("Location"));
                reviewer.setName(rs.getString("Name"));
                reviewer.setNumWrittenReviews(rs.getInt("NumWrittenReview"));
                reviewer.setUsername(rs.getString("Username"));
                reviewer.setReviewerID(reviewerID);
                reviewer.setReviewerURL(rs.getString("ReviewerURL"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            closeConn();
        }
        return reviewer;
    }
    
    public boolean addReviewComment(Comment comment){
        if (comment.getReason().isEmpty()){
            comment.setReason("null");
        }
        else if(comment.getOtherInfo().isEmpty()){
            comment.setOtherInfo("null");
        }
        String sql ="INSERT INTO comment(UserID,Reason,OtherInfo,SpamIndicator,ReviewID,ReviewerID) "
                + "VALUES("+comment.getUserID()+",'"+comment.getReason()+
                "','"+comment.getOtherInfo()+"','"+comment.getSpamIndicator()+"',"+comment.getReview().getReviewID()+","+comment.getReviewer().getReviewerID()+")";
        //System.out.print(sql); 
        try{
            db.getStatement().executeUpdate(sql);
            return true;
         }
         catch(Exception e){
             e.printStackTrace();
         }
        finally{
            closeConn();
        }
        return false;
    }
    
    public boolean deleteComment(int commentID){
        String sql ="Delete FROM comment WHERE CommentID = " + commentID;
        //System.out.println(sql);
        try{
            db.getStatement().executeUpdate(sql);
            return true;
         }
         catch(Exception e){
             e.printStackTrace();
         }
        finally{
            closeConn();
        }
        return false;
    }
    
    public int validateUser(String username,String password){
        String sql ="SELECT * FROM user WHERE username='"+username
                    +"' AND password='"+password+"'";
        try{
            rs = db.getStatement().executeQuery(sql);
            if (rs.next())
            {
                return rs.getInt("UserID");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            closeConn();
        }
        return 0;
    }
    
    
    //=====Supporting functions ======================
    private Reviewer checkDuplicatedReviewer(LinkedList<Reviewer> reviewerList,int reviewerID){
        for (Reviewer person:reviewerList){
            if (reviewerID == person.getReviewerID()){
            return person;
            }
        }
      return null;
    }
    
    private Hotel checkDuplicatedHotel(LinkedList<Hotel> hotelList,int hotelID){
        for (Hotel hotel:hotelList){
            if (hotelID == hotel.getHotelID()){
            return hotel;
            }
        }
      return null;
    }
    
    public void closeConn(){
       try{
          if (rs!=null)
              rs.close();
        }
        catch(Exception e){
            System.out.print("Connection close error:");
            e.printStackTrace();
        }
    }
    
    public void closeDB(){
        db.dbClose();
    }
}