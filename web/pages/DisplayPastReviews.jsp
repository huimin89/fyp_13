<%-- 
    Document   : DisplayPastReviews
    Created on : Jul 14, 2013, 12:46:42 PM
    Author     : s amsung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>

 <jsp:include page="/layout/TopLayout_1.jsp"/>
 <style>
     #hidden{
         visibility:hidden;
     }
 </style>

 <script>
 window.onload = function() {
    var reviewerID =$('#hidden').attr("reviewerID"); 
    $.getJSON('/SpamDetectionWeb/pages/processPastReviewData.action?reviewerID=' + reviewerID, function(jd) {
        $('#heading').html(jd.reviewer.name);
        $('#heading').append('&nbsp &nbsp<small>' + jd.reviewer.dateJoined +"</small>");
        $('#subHeading').html('<small><b>Username: </b>' + jd.reviewer.username +' </small>');
        $('#subHeading').append('<br/><small><b>Address: </b>' + jd.reviewer.location + '</small> &nbsp &nbsp &nbsp')
        $('#subHeading').append('<br/><small><b>Gender: </b>' + jd.reviewer.gender +' </small>');
        $('#subHeading').append('<br/><small><b>No. of Written Reviews: </b>' + jd.reviewer.numWrittenReviews + '</small> &nbsp &nbsp &nbsp');
        $('#reviewerLink').attr('href',jd.reviewer.reviewerURL);
        var html;
        var count = 0;
        $.each(jd.listOfBuddies, function(p) {
            if (count<1){
                html = "<p id='additionalInfo' class='well well-small' style='background-color:white'></p>";
                $('#description').append(html);
                count++;
            }
            if (reviewerID == jd.listOfBuddies[p].reviewerID_2)
                html ="<b>Buddy ID <a href='/SpamDetectionWeb/pages/DisplayPastReviews.action?reviewerID=" 
                    + jd.listOfBuddies[p].reviewerID + "'>"+ jd.listOfBuddies[p].reviewerID  +"</a> : </b>";
            else 
                html ="<b>Buddy ID <a href='/SpamDetectionWeb/pages/DisplayPastReviews.action?reviewerID=" 
                    + jd.listOfBuddies[p].reviewerID_2 + "'>"+ jd.listOfBuddies[p].reviewerID_2  +"</a> : </b>";
            
            $.each(jd.listOfBuddies[p].hotelList, function(j) {
                html += "<a href='/SpamDetectionWeb/pages/DisplayReviews.action?hotelID=" + jd.listOfBuddies[p].hotelList[j] + "'>" 
                        + jd.listOfBuddies[p].hotelList[j] +"</a>   ";
            });
            html +="<br/>";
            $('#additionalInfo').append(html);
        });
        
        if (jd.listOfPastReviews.length==0){
            
             $('.content').append("Past review for this hotel hasn't been inserted.");  
        }
        else{
            $.each(jd.listOfPastReviews, function(i) {    

                html ='<div class="row-fluid data"><div class="span12"><div class="span12 well well-small">';
                html += '<h3>'+jd.listOfPastReviews[i].title + '&nbsp<small>'+jd.listOfPastReviews[i].rating+'</small>&nbsp &nbsp';
                html += '<span class="badge">'+jd.listOfPastReviews[i].numHelpfulVotes +'&nbsp<i class="icon-thumbs-up"></i></span></h3>';
                html += '<p>' + jd.listOfPastReviews[i].content + '</p>';
                html += '<a style="float:left; width:5%" href="'+ jd.listOfPastReviews[i].pastReviewURL +'">Link</a>';
                html += '<p style="float:right">'+ jd.listOfPastReviews[i].date + '&nbsp &nbsp &nbsp';
                html += '</p></div></div></div>';

                $('.content').append(html);  
                });

                $('#pastReviews').pajinate({
                    items_per_page : 20	
                });
        }
     });
   };
     
     function viewReview(){
            
            var reviewerID =$('#hidden').attr("reviewerID"); 
            $.getJSON('/SpamDetectionWeb/pages/processReviewsByReviewer.action?reviewerID=' + reviewerID, function(jd) {
            var html="";
            $.each(jd.listOfReviews, function(i) {    
                html +='<div class="row-fluid data"><div class="span12"><div class="span12 well well-small">';
                html += '<h3>'+jd.listOfReviews[i].title + '&nbsp<small>'+jd.listOfReviews[i].rating+'</small>&nbsp &nbsp';
                html += '<span class="badge">'+jd.listOfReviews[i].numHelpfulVotes +'&nbsp<i class="icon-thumbs-up"></i></span>\n';
                html += '<small><a href="/SpamDetectionWeb/pages/DisplayReviews.action?hotelID='+ jd.listOfReviews[i].hotel.hotelID +'">'+ jd.listOfReviews[i].hotel.name +'</a></small></h3>';
                html += '<p>' + jd.listOfReviews[i].content + '</p>';
                html += '<a style="float:left; width:5%" href="'+ jd.listOfReviews[i].reviewURL +'">Link</a>';
                html += '<p style="float:right">'+ jd.listOfReviews[i].date + '&nbsp &nbsp &nbsp';
                if (jd.listOfReviews[i].markByUser == false)
                html += '<a data-toggle="modal" data-backdrop="static" href="#spamBox" onclick="update('+jd.listOfReviews[i].reviewID +','+ jd.reviewer.reviewerID +');"><i class="icon-flag"></i></a>';
                html += '</p></div></div></div>';


            });
                $('#pastReviews').html(html);  
            });
     
     };
     
     function viewPastReviews(){
        var reviewerID =$('#hidden').attr("reviewerID"); 
        var html="";
        $('#pastReviews').html("<div class='content'></div><div class='page_navigation'></div>");
        $.getJSON('/SpamDetectionWeb/pages/processPastReviewData.action?reviewerID=' + reviewerID, function(jd) {
            if (jd.listOfPastReviews.length==0){
            
                $('.content').append("Past review for this hotel hasn't been inserted.");  
            }
            else{  
                $.each(jd.listOfPastReviews, function(i) {    

                html +='<div class="row-fluid data"><div class="span12"><div class="span12 well well-small">';
                html += '<h3>'+jd.listOfPastReviews[i].title + '&nbsp<small>'+jd.listOfPastReviews[i].rating+'</small>&nbsp &nbsp';
                html += '<span class="badge">'+jd.listOfPastReviews[i].numHelpfulVotes +'&nbsp<i class="icon-thumbs-up"></i></span></h3>';
                html += '<p>' + jd.listOfPastReviews[i].content + '</p>';
                html += '<a style="float:left; width:5%" href="'+ jd.listOfPastReviews[i].pastReviewURL +'">Link</a>';
                html += '<p style="float:right">'+ jd.listOfPastReviews[i].date + '&nbsp &nbsp &nbsp';
                html += '</p></div></div></div>';

                $('.content').append(html);  
                });

                $('#pastReviews').pajinate({
                    items_per_page : 20	
                });
            }
        });
    };
    
     function update(reviewID,reviewerID){
        $('#spamBox').attr("value",reviewID);
        $('#spamBox').attr("data",reviewerID);
        var val = $('#spamBox').attr("value");
     }
     
     function saveComment(){
        var reasons = "";
        var otherInformation = $('#otherInfo').val();
        var spamIndicator = document.getElementsByTagName('input');
        var reviewID = $('#spamBox').val();
        var reviewerID = $('#spamBox').attr("data");
        var spamValue;
        
        var temp = document.getElementsByName("reason");
        for (var i = 0; i<temp.length;i++){
            if (temp[i].checked){
                reasons += temp[i].value + " : ";
            
                if (temp[i].value == "11"){
                    if ($('#others').val() == ""){
                        alert("Enter other reasons");
                        return;
                    }
                    reasons += $('#others').val() + " : ";

                }
            }
        }
        
        if (reasons!=""){
        
        for (var i = 0; i < spamIndicator.length; i++) {
        if (spamIndicator[i].type === 'radio' && spamIndicator[i].checked) {
            // get value, set checked flag or do whatever you need to
            spamValue = spamIndicator[i].value;    
        }
        }
        var JSONObject= {
            "reason":reasons,
            "otherInfo":otherInformation,
            "spamIndicator":spamValue,
            "reviewID":reviewID,
            "reviewerID":reviewerID};

        $.ajax({
            type: "POST",
            url: '/SpamDetectionWeb/pages/updateData.action',
            dataType: "json",
            data:JSONObject,
            success: function (data) {
                alert("Comment successfully saved");
            }, //success
            error: function (req) {
                
                alert("Comment isn't saved due to some technical issue");
            }
        }); 
         $('#closeBtn').click();
         resetValue();
         
        }
        else{
        
        alert("Enter a reason for treating it as spam review")
        }
        return false;
     }
     
     function resetValue(){
        $('#reason').val("");
        $('#otherInfo').val("");
        $('#spamBox').val("");
        $('#radio').attr("checked","checked");
     }

   
  </script>
<div id="hidden" reviewerID ="<s:property value="reviewerID"/>"></div>
<div class="row-fluid">
        <div class="span12 well well-small">
            <div class="span12">
                <h1 id="heading"></h1>
            </div>	
            <div class="span6"> 
                <h3 id="subHeading"></h3>
                <br/>
                <a href="#" id="reviewerLink">Link to actual page</a>
                
            </div>
            <div class="span4" id="description"> 
                
            </div>
        </div>
    <div class="span12" style="text-align:right;padding-right:4%">
        <input type ="button" value="Hotel Reviews Only" onclick="viewReview();">
        &nbsp;&nbsp;
        <input type ="button" value="All Past Reviews"  onclick="viewPastReviews();">
    </div>
</div>
<div id="pastReviews">
    <div class="content">
    </div>
    <div class="page_navigation"></div>	 	
</div>
<div class="pagination pagination-centered">
    <ul id ="pagingBox">
        
    </ul>
</div>

<div class="modal hide" id="spamBox">
    <div class="modal-header">
        <button class="close" data-dismiss="modal" id="closeBtn">×</button>
        <h3>Comment Box</h3>
    </div>
    <div class="modal-body">
    <p id="hidden" value=""></p>
    <form action="">
        <table width="100%">
            <tr>
                <th style="text-align: left" >Reasons to be Spam</th>
            </tr>
            <tr>
                <td>
                    <input type ="checkbox" name="reason" value="1">&nbsp;Tones of reviews(Eg:Excessive praises,CAPS,Marketing description,etc)<br/>
                    <input type ="checkbox" name="reason" value="2">&nbsp;Same or close reviewed dates & frequency of reviews<br/>
                    <input type ="checkbox" name="reason" value="3">&nbsp;No downside<br/>
                    <input type ="checkbox" name="reason" value="4">&nbsp;Single account review<br/>
                    <input type ="checkbox" name="reason" value="5">&nbsp;Repetitive brand names<br/>
                    <input type ="checkbox" name="reason" value="6">&nbsp;Discount code, promote other hotel<br/>
                    <input type ="checkbox" name="reason" value="7">&nbsp;Target on specific hotel<br/>
                    <input type ="checkbox" name="reason" value="8">&nbsp;Inter chained reviews at different locations<br/>
                    <input type ="checkbox" name="reason" value="9">&nbsp;Similar or duplicated review<br/>
                    <input type ="checkbox" name="reason" value="10">&nbsp;Long winded explanation,bullshit,Inconsistency<br/>
                    <input type ="checkbox" name="reason" value="11">&nbsp;Others 
                    <input type ="textbox" name="others" id="others"/>
                    <br/><br/>
                </td>
            </tr>
            <tr>
                <th style="text-align: left">Other supporting information (Eg:URL)</th>
            </tr>
            <tr>
                <td><textarea style="width:98%" rows="5" cols="50" id="otherInfo" overflow="auto"></textarea></td>
            </tr>
            <tr>
                <th style="text-align: left">Spam Indicator</th>
            </tr>
            <tr>
                <td>&nbsp;<input type="radio" value="Yes" name="spamIndicator" id="radio" checked="checked">Yes
                    &nbsp;<input type="radio" value="Maybe" name="spamIndicator" >Maybe
                    &nbsp;<input type="radio" value="No" name="spamIndicator" >No
                </td>
            </tr>
            <tr>
                <td style="text-align:center"><input type="button" value="Save" onclick="saveComment();">
                &nbsp<input type="button" value="Cancel" onclick="$('#closeBtn').click();resetValue();;"></td>
            </tr>
        </table>
    </form></div>
</div> 
 <jsp:include page="/layout/BottomLayout_1.jsp"/>					
                


