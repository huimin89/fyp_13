
package Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Buddy {
    private int reviewerID;
    private int reviewerID_2;
    private List<String> hotelList;

    public Buddy() {
        hotelList = new ArrayList<String>();
    }

    public int getReviewerID() {
        return reviewerID;
    }

    public void setReviewerID(int reviewerID) {
        this.reviewerID = reviewerID;
    }

    public int getReviewerID_2() {
        return reviewerID_2;
    }

    public void setReviewerID_2(int reviewerID_2) {
        this.reviewerID_2 = reviewerID_2;
    }

    public List<String> getHotelList() {
        return hotelList;
    }

    public void setHotelList(String hotels) {
        StringTokenizer tokenizer = new StringTokenizer(hotels,",");
        while (tokenizer.hasMoreTokens()){
            this.hotelList.add(tokenizer.nextToken().trim());
        }

    }
    
    
}
