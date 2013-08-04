/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.DAOService;
import com.opensymphony.xwork2.ActionSupport;
import Entities.*;
import com.opensymphony.xwork2.ActionContext;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

public class DisplaySpamReviews extends ActionSupport{
    private List<Comment> listOfComments;
    private DAOService daoService;
    private HttpServletRequest request;
    private int deletedCommentID;
    
    private int userID;
    private String name;
    private HttpSession session;
    
    public String execute() throws Exception{
        request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        session = request.getSession();
        userID = (Integer)session.getAttribute("userID");
        name = (String)session.getAttribute("username");
        
        return "SUCCESS";
    }
    
    public String processData() throws Exception{
        request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        session = request.getSession();
        userID = (Integer)session.getAttribute("userID");
        name = (String)session.getAttribute("username");
        daoService = new DAOService();
        listOfComments = daoService.getAllComments(userID);
        
        return ActionSupport.SUCCESS;
    }
    
   public String processData2() throws Exception{
        daoService = new DAOService();
        listOfComments = daoService.getAllComments(userID);
        name = daoService.getUsername(userID);
        
        return ActionSupport.SUCCESS;
    }
   
   public String deleteComment() throws Exception{
       daoService = new DAOService();
       daoService.deleteComment(deletedCommentID);
       return ActionSupport.SUCCESS;
   }

    public List<Comment> getListOfComments() {
        return listOfComments;
    }

    public void setListOfComments(List<Comment> listOfComments) {
        this.listOfComments = listOfComments;
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

    public int getDeletedCommentID() {
        return deletedCommentID;
    }

    public void setDeletedCommentID(int deletedCommentID) {
        this.deletedCommentID = deletedCommentID;
    }
    
   
}
