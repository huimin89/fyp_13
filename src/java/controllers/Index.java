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
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

public class Index extends ActionSupport {
    
    private String username;
    private String password;
    private DAOService daoService;
    HttpServletRequest request;
    HttpSession session;
    
    public String execute() throws Exception{
        return "SUCCESS";
    }
    

    public String validateLogin() throws Exception{
        
        String uname = username;  
        String encryptedPW = null;
        int userID = 0;
        try {
            daoService = new DAOService();     
            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");
            //Update input string in message digest
            digest.update(getPassword().getBytes(), 0, getPassword().length());
            //Converts message digest value in base 16 (hex)
            encryptedPW = new BigInteger(1, digest.digest()).toString(16);

            userID = daoService.validateUser(username, encryptedPW);
            if (userID!= 0){
                setSession(userID,username);
                
            }
            else{
                return "FAIL";

            } 
            
        } catch (NoSuchAlgorithmException e) {
 
            e.printStackTrace();
        }
        
        return "SUCCESS";
    }
    
    public String logout() throws Exception{
        
       request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
       session = request.getSession();
       session.invalidate();
       DAOService daoService = new DAOService();
       daoService.closeDB();
       
       return "SUCCESS";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setSession(int userID,String username){
       request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
       session = request.getSession();
       session.setAttribute("userID", userID); 
       session.setAttribute("username", username); 
    }
}
