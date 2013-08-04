/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.DAOService;
import Entities.Buddy;
import Entities.Reviewer;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

public class DisplayAllReviewers extends ActionSupport{
    
    private DAOService daoService;
    private List<Reviewer> listOfReviewers; 
    private List<Buddy> listOfBuddies; 
    private String name;
    private HttpSession session;
    private HttpServletRequest request;
    
    public String execute() throws Exception{
        request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        session = request.getSession();
        name = (String)session.getAttribute("username");
        return "SUCCESS";       
    }
    
    public String processData() throws Exception{
        daoService = new DAOService();
        listOfReviewers = new ArrayList<Reviewer>();   
        listOfReviewers = daoService.getAllReviewers();
        listOfBuddies = daoService.getAllBuddies();
             
        return ActionSupport.SUCCESS;
    }

    public List<Buddy> getListOfBuddies() {
        return listOfBuddies;
    }

    public void setListOfBuddies(List<Buddy> listOfBuddies) {
        this.listOfBuddies = listOfBuddies;
    }

    public List<Reviewer> getListOfReviewers() {
        return listOfReviewers;
    }

    public void setListOfReviewers(List<Reviewer> listOfReviewers) {
        this.listOfReviewers = listOfReviewers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
