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
    var hotelID =$('#hidden').attr("hotelID"); 
    $.getJSON('/SpamDetectionWeb/pages/processData.action?hotel_ID=' + hotelID, function(jd) {
        $('#heading').html(jd.hotel.name);
        $('#heading').append('&nbsp &nbsp<small>' + jd.hotel.avgRating +" of 5 stars</small>");
        $('#subHeading').html('<small>' + jd.hotel.location + '</small> &nbsp &nbsp &nbsp')
        $('#subHeading').append('<span class="label label-info">' + jd.hotel.updatedNumReviews +' reviews</span>');
        $('#hotelLink').attr("href",jd.hotel.hotelURL);
        $('#excellent').html(jd.hotel.statSummary[0]);
        $('#vGood').html(jd.hotel.statSummary[1]);
        $('#good').html(jd.hotel.statSummary[2]);
        $('#poor').html(jd.hotel.statSummary[3]);
        $('#terrible').html(jd.hotel.statSummary[4]);
        
        $.each(jd.listOfReviews, function(i) {    
           
            var html ='<div class="row-fluid data"><div class="span12"><div class="span8 well well-small">';
            html += '<h3>'+jd.listOfReviews[i].title + '&nbsp<small>'+jd.listOfReviews[i].rating+'</small>&nbsp &nbsp';
            html += '<span class="badge">'+jd.listOfReviews[i].numHelpfulVotes +'&nbsp<i class="icon-thumbs-up"></i></span></h3>';
            html += '<p>' + jd.listOfReviews[i].content + '</p>';
            html += '<a style="float:left; width:5%" href="'+ jd.listOfReviews[i].reviewURL +'">Link</a>';
            html += '<p style="float:right">'+ jd.listOfReviews[i].date + '&nbsp &nbsp &nbsp';
            
            if (jd.listOfReviews[i].markByUser == false)
                html += '<a data-toggle="modal" data-backdrop="static" href="#spamBox" onclick="update('+jd.listOfReviews[i].reviewID +','+ jd.listOfReviews[i].reviewer.reviewerID + ');"><i class="icon-flag"></i></a>';
            
            html += '</p></div>';
            html += '<div class="span4 well well-small"><h3>'+jd.listOfReviews[i].reviewer.name+'</h3>';
            html += '<table class="table"><tr><th>Username</th><td><a href="/SpamDetectionWeb/pages/DisplayPastReviews.action?reviewerID='+jd.listOfReviews[i].reviewer.reviewerID+'">'+jd.listOfReviews[i].reviewer.username+'</a></td></tr>';
            html += '<tr><th>Location</th><td>'+jd.listOfReviews[i].reviewer.location+'</td></tr>';
            html += '<tr><th>DateJoined</th><td>'+jd.listOfReviews[i].reviewer.dateJoined +'</td></tr>';
            html += '<tr><th>Gender</th><td>'+jd.listOfReviews[i].reviewer.gender+'</td></tr>';
            html += '<tr><th>No. Of Written Reviews</th><td>'+jd.listOfReviews[i].reviewer.numWrittenReviews+'</td></tr>';
            html += '<tr><td><a href="'+jd.listOfReviews[i].reviewer.reviewerURL+'">Link</a></td></tr></table>';
            html += '</div></div></div>';
            $('.content').append(html);  
            });
         
            $('#reviews').pajinate({
                items_per_page : 20	
            });
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
                    reasons += $('#others').val() + " : ";;

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
<div id="hidden" hotelID ="<s:property value="hotelID"/>"></div>
<div class="row-fluid">
        <div class="span12 well well-small">
            <div class="span8">
                <h1 id="heading"></h1>
                <h3 id="subHeading"></h3>
                <br/><br/><br/><br/>
                <a href="#" id="hotelLink">Link to actual page</a>
            </div>
            <div class="span4">
                <table cellpadding="5" width="80%">
                    <tr>
                        <th colspan = "2" style="text-align:center">
                            <u>Summary Statistics</u>
                        </th>
                    </tr>
                    <tr>
                        <th width="50%">Excellent</th>
                        <td id ="excellent" style="padding-left:20px"></td>
                    </tr>
                    <tr>
                        <th width="50%">Very Good</th>
                        <td id ="vGood" style="padding-left:20px"></td>
                    </tr>
                    <tr>
                        <th width="50%">Good</th>
                        <td id ="good" style="padding-left:20px"></td>
                    </tr>
                    <tr>
                        <th width="50%">Poor</th>
                        <td id ="poor" style="padding-left:20px"></td>
                    </tr>
                    <tr>
                        <th width="50%">Terrible</th>
                        <td id ="terrible" style="padding-left:20px"></td>
                    </tr>
                </table>
            </div>	
        </div>
    </div>
<div id="reviews">
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
                

