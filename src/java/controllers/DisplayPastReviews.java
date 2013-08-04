/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.DAOService;
import Entities.Buddy;
import Entities.Comment;
import Entities.PastReview;
import Entities.Review;
import Entities.Reviewer;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author s amsung
 */
public class DisplayPastReviews extends ActionSupport{
    private List<PastReview> listOfPastReviews;
    private List<Buddy> listOfBuddies;
    private List<Review> listOfReviews;
    private List<Comment> listOfComments;
    private Reviewer reviewer;
    private DAOService daoService;
    private HttpServletRequest request;
    private int reviewerID;
    
    private int userID;
    private String name;
    private HttpSession session;
    
    public String execute()throws Exception{
        request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        reviewerID = Integer.parseInt(request.getParameter("reviewerID"));
        session = request.getSession();
        userID = (Integer)session.getAttribute("userID");
        name = (String)session.getAttribute("username");
        return "SUCCESS";
    }
    
    public String processData() throws Exception{
        request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        reviewerID = Integer.parseInt(request.getParameter("reviewerID"));
        
        listOfPastReviews = new ArrayList<PastReview>();
        reviewer = new Reviewer();
        daoService = new DAOService();
        
        reviewer = daoService.getReviewer(reviewerID); 
        listOfPastReviews = daoService.getPastReviews(reviewer.getReviewerID());
        listOfBuddies = daoService.getBuddies(reviewerID);
        
        return ActionSupport.SUCCESS;
    }
    
    public String processReviewData() throws Exception{
        request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        reviewerID = Integer.parseInt(request.getParameter("reviewerID"));
        session = request.getSession();
        userID = (Integer)session.getAttribute("userID");
        
        listOfReviews = new ArrayList<Review>();
        reviewer = new Reviewer();
        daoService = new DAOService();
        
        reviewer = daoService.getReviewer(reviewerID); 
        listOfReviews = daoService.getAllReviewsByReviewer(reviewerID);
        listOfBuddies = daoService.getBuddies(reviewerID);
        listOfComments = daoService.getAllComments(userID);
        markSpamReview();
        
       
        return ActionSupport.SUCCESS;
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

    public List<Comment> getListOfComments() {
        return listOfComments;
    }

    public void setListOfComments(List<Comment> listOfComments) {
        this.listOfComments = listOfComments;
    }

    public List<Review> getListOfReviews() {
        return listOfReviews;
    }

    public void setListOfReviews(List<Review> listOfReviews) {
        this.listOfReviews = listOfReviews;
    }

    public List<PastReview> getListOfPastReviews() {
        return listOfPastReviews;
    }

    public void setListOfPastReviews(List<PastReview> listOfPastReviews) {
        this.listOfPastReviews = listOfPastReviews;
    }

    public List<Buddy> getListOfBuddies() {
        return listOfBuddies;
    }

    public void setListOfBuddies(List<Buddy> listOfBuddies) {
        this.listOfBuddies = listOfBuddies;
    }

    
    public Reviewer getReviewer() {
        return reviewer;
    }

    public void setReviewer(Reviewer reviewer) {
        this.reviewer = reviewer;
    }

    public int getReviewerID() {
        return reviewerID;
    }

    public void setReviewerID(int reviewerID) {
        this.reviewerID = reviewerID;
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
    
}
