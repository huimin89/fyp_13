/*
 * Purpose: Backend processing for DisplayReviews.jsp. 
 * Description: This file calls to retrieve all related reviews and reviewer 
 *              for the selected hotel. 
 * Date: 14 July 2013
 * Author: Huimin
 * 
 */

package controllers;

import Entities.Hotel;
import Entities.Review;
import DAO.*;
import Entities.Comment;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpSession;


public class DisplayReviews extends ActionSupport {
    
    private List<Review> listOfReviews;
    private List<Comment> listOfComments;
    private Hotel hotel;
    private DAOService daoService;
    private HttpServletRequest request;
    private int hotelID ;
    private int userID;
    private String name;
    private HttpSession session;
   
    private String reason;
    private String otherInfo;
    private String spamIndicator;
    private int reviewID; 
    private int reviewerID;
    
    public String execute() throws Exception
    {
        request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        hotelID = Integer.parseInt(request.getParameter("hotelID"));
        session = request.getSession();
        userID = (Integer)session.getAttribute("userID");
        name = (String)session.getAttribute("username");
        return "SUCCESS";
    }
    public String processData() throws Exception {
        request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        hotelID = Integer.parseInt(request.getParameter("hotel_ID"));
        session = request.getSession();
        userID = (Integer)session.getAttribute("userID");
        
        daoService = new DAOService();
        listOfReviews = new ArrayList();
        hotel = daoService.getHotel(hotelID);
        
        listOfReviews = daoService.getAllReviews(hotel.getHotelID());
        listOfComments = daoService.getAllComments(userID);
        markSpamReview();
        
        return ActionSupport.SUCCESS;
    }

    public String updateData() throws Exception{
        request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        session = request.getSession();
        userID = (Integer)session.getAttribute("userID");
        
        daoService = new DAOService();
        Comment comment = new Comment(reason, otherInfo,spamIndicator,userID,reviewID,reviewerID);
        boolean result = daoService.addReviewComment(comment);
        if (result)
            return ActionSupport.SUCCESS;
        else
            return ActionSupport.ERROR;
    }

    public void markSpamReview(){
        for (Comment c: listOfComments){
            for (Review r: listOfReviews){
                if (r.getReviewID() == c.getReview().getReviewID()){
                    r.setMarkByUser(true);
                    break;
                }
            }
        }
    }
    
    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public int getReviewerID() {
        return reviewerID;
    }

    public void setReviewerID(int reviewerID) {
        this.reviewerID = reviewerID;
    }
    
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
    
    public List<Review> getListOfReviews() {
        return listOfReviews;
    }

    public void setListOfReviews(List<Review> listOfReviews) {
        this.listOfReviews = listOfReviews;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Comment> getListOfComments() {
        return listOfComments;
    }

    public void setListOfComments(List<Comment> listOfComments) {
        this.listOfComments = listOfComments;
    }
  
    
}

