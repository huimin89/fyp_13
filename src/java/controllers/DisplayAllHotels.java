/*
 * Purpose: Backend processing for Index.jsp and DisplayAllHotels.jsp
 * Description: This page calls to retrieve all available hotels in the 
 *              db for display
 * Date:14 July 2013
 * Author: Huimin
 * 
 */
package controllers;
import DAO.DAOService;
import Entities.Hotel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

public class DisplayAllHotels extends ActionSupport {
    
    private List<Hotel> listOfHotels;
    private DAOService daoService;
    private String name;
    private HttpSession session;
    private HttpServletRequest request;
    
    public String execute() throws Exception{
        request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        session = request.getSession();
        name = (String)session.getAttribute("username");
        return "SUCCESS";
    }
    
    public String processData() throws Exception {
        daoService = new DAOService();
        listOfHotels = new ArrayList();
        listOfHotels = daoService.getAllHotels();
        
      
        return ActionSupport.SUCCESS;
    }

    public List<Hotel> getListOfHotels() {
        return listOfHotels;
    }

    public void setListOfHotels(List<Hotel> listOfHotels) {
        this.listOfHotels = listOfHotels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
