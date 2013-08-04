/*
 * Purpose: Instantiates a hotel object
 * Description: A model(AKA bean in Struts 2) to store the data retrieved from DB.
 * Date:14 July 2013
 * Author: Huimin
 * 
 */
package Entities;

import java.util.StringTokenizer;

public class Hotel {
    private int hotelID;
    private String name;
    private String location;
    private String avgRating;
    private int totalNumReviews;
    private int updatedNumReviews;
    private String hotelURL;
    private int[] statSummary = new int[5];

    public Hotel(){};
    public Hotel(int hotelID,String name, String location,String avgRating,
            int totalReviews,String hotelURL,String stat,int updatedNumReviews) {
        setHotelID(hotelID);
        setName(name);
        setLocation(location);
        setAvgRating(avgRating);
        setTotalNumReviews(totalReviews);
        setHotelURL(hotelURL);
        setStatSummary(stat);
        setUpdatedNumReviews(updatedNumReviews);
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(String overallRating) {
        this.avgRating = overallRating;
    }

    //index 0 represents number of excellent reviews,
    //1 represents very good, so on and so forth
    public int[] getStatSummary() {
        return statSummary;
    }

    //processed summary data of rating will be stored in string to save db storage
    //using ':' to differentiate the number of rating for different types of reviews
    public void setStatSummary(String ratingSummary) {
        int[] summary = new int[5];
        int index = 0; 
        StringTokenizer tokenizer = new StringTokenizer(ratingSummary,":");
        
        while (tokenizer.hasMoreTokens()){
            String token = tokenizer.nextToken().trim();
            if (token.contains(","))
                token = token.replace(",", "");
            
            summary[index] = Integer.parseInt(token);
            index++;
        }
        this.statSummary = summary;
    }
    
        public int getTotalNumReviews() {
        return totalNumReviews;
    }

    public void setTotalNumReviews(int totalNumReviews) {
        this.totalNumReviews = totalNumReviews;
    }

    public int getUpdatedNumReviews() {
        return updatedNumReviews;
    }

    public void setUpdatedNumReviews(int updatedNumReviews) {
        this.updatedNumReviews = updatedNumReviews;
    }
    
    public String getHotelURL() {
        return hotelURL;
    }

    public void setHotelURL(String hotelURL) {
        this.hotelURL = hotelURL;
    }
    
}