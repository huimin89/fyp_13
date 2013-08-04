<%-- 
    Document   : DisplaySpamReviews
    Created on : Jul 24, 2013, 11:12:15 PM
    Author     : s amsung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>


 <jsp:include page="/layout/TopLayout_1.jsp"/>
<script>
 window.onload = function() {
    $.getJSON('/SpamDetectionWeb/pages/processSpamReview.action', function(jd) {
        var tempReviewerID = 0;
        if (jd.listOfComments.length==0){
            
             $('.content').append("No spam review has been detected by this user.");  
        }
        $.each(jd.listOfComments, function(i) {    
            if (tempReviewerID != jd.listOfComments[i].reviewer.reviewerID){
                var reviewerHtml ='<div class="row-fluid"><div class="span12 well well-small">';
                reviewerHtml+='<h1>'+jd.listOfComments[i].reviewer.name+'&nbsp;&nbsp; <small>'+jd.listOfComments[i].reviewer.dateJoined+'</small></h1>';
                reviewerHtml+='<h3><small><b>Username: </b>' + jd.listOfComments[i].reviewer.username +' </small>';
                reviewerHtml+='<br/><small><b>Address: </b>' + jd.listOfComments[i].reviewer.location + '</small>';
                reviewerHtml+='<br/><small><b>Gender: </b>' + jd.listOfComments[i].reviewer.gender +' </small>';
                reviewerHtml+='<br/><small><b>No. of Written Reviews: </b>' + jd.listOfComments[i].reviewer.numWrittenReviews + '</small></h3>';
                reviewerHtml+='</div></div>';
                $('.content').append(reviewerHtml);  
                tempReviewerID = jd.listOfComments[i].reviewer.reviewerID;
            }
            var html ='<div class="row-fluid data"><div class="span12"><div class="span8 well well-small">';
            html += '<h3>'+jd.listOfComments[i].review.title + '&nbsp<small>'+jd.listOfComments[i].review.rating+'</small>&nbsp &nbsp';
            html += '<span class="badge">'+jd.listOfComments[i].review.numHelpfulVotes +'&nbsp<i class="icon-thumbs-up"></i></span></h3>';
            html += '<p>' + jd.listOfComments[i].review.content + '</p>';
            html += '<a style="float:left; width:5%" href="'+ jd.listOfComments[i].review.reviewURL +'">Link</a>';
            html += '<p style="float:right">'+ jd.listOfComments[i].review.date + '&nbsp &nbsp &nbsp';
            html += '</p></div>';
            //html += '<a data-toggle="modal" data-backdrop="static" href="#spamBox" onclick="update('+jd.listOfComments[i].reviewID +','+ jd.listOfReviews[i].reviewer.reviewerID + ');"><i class="icon-flag"></i></a></p></div>';
            html += '<div class="span4 well well-small">';
            html += '<table class="table"><tr><th>Spam Indicator</th><td>'+jd.listOfComments[i].spamIndicator+'</td></tr>';
            html += '<tr><th>Reason</th><td>'+jd.listOfComments[i].formattedReason+'</td></tr>';
            html += '<tr><th>Other Information</th><td>'+jd.listOfComments[i].otherInfo+'</td></tr>';
            html += '<tr><td></td><td style="text-align:right"><i class="icon-adt_trash" onclick="deleteComment('+ jd.listOfComments[i].commentID+');"></i></td></tr></table>';
            html += '</div></div></div>';
            //alert(html);
             $('.content').append(html);  
            });
         
            
        });
        
     };
     
  function changeUser(userID){
    $.getJSON('/SpamDetectionWeb/pages/processSpamReview2.action?userID='+userID, function(jd) {
        $('.content').html("");  
        if (jd.listOfComments.length==0){
            
             $('.content').append("No spam review has been detected by this user.");  
        }
        var tempReviewerID = 0;
        
        $.each(jd.listOfComments, function(i) {    
            if (tempReviewerID != jd.listOfComments[i].reviewer.reviewerID){
                var reviewerHtml ='<div class="row-fluid"><div class="span12 well well-small">';
                reviewerHtml+='<h1>'+jd.listOfComments[i].reviewer.name+'&nbsp;&nbsp; <small>'+jd.listOfComments[i].reviewer.dateJoined+'</small></h1>';
                reviewerHtml+='<h3><small><b>Username: </b>' + jd.listOfComments[i].reviewer.username +' </small>';
                reviewerHtml+='<br/><small><b>Address: </b>' + jd.listOfComments[i].reviewer.location + '</small>';
                reviewerHtml+='<br/><small><b>Gender: </b>' + jd.listOfComments[i].reviewer.gender +' </small>';
                reviewerHtml+='<br/><small><b>No. of Written Reviews: </b>' + jd.listOfComments[i].reviewer.numWrittenReviews + '</small></h3>';
                reviewerHtml+='</div></div>';
                $('.content').append(reviewerHtml);  
                tempReviewerID = jd.listOfComments[i].reviewer.reviewerID;
            }
            var html ='<div class="row-fluid data"><div class="span12"><div class="span8 well well-small">';
            html += '<h3>'+jd.listOfComments[i].review.title + '&nbsp<small>'+jd.listOfComments[i].review.rating+'</small>&nbsp &nbsp';
            html += '<span class="badge">'+jd.listOfComments[i].review.numHelpfulVotes +'&nbsp<i class="icon-thumbs-up"></i></span></h3>';
            html += '<p>' + jd.listOfComments[i].review.content + '</p>';
            html += '<a style="float:left; width:5%" href="'+ jd.listOfComments[i].review.reviewURL +'">Link</a>';
            html += '<p style="float:right">'+ jd.listOfComments[i].review.date + '&nbsp &nbsp &nbsp';
            html += '</p></div>';
            //html += '<a data-toggle="modal" data-backdrop="static" href="#spamBox" onclick="update('+jd.listOfComments[i].reviewID +','+ jd.listOfReviews[i].reviewer.reviewerID + ');"><i class="icon-flag"></i></a></p></div>';
            html += '<div class="span4 well well-small">';
            html += '<table class="table"><tr><th>Is Spam?</th><td>'+jd.listOfComments[i].spamIndicator+'</td></tr>';
            html += '<tr><th>Reason</th><td>'+jd.listOfComments[i].formattedReason+'</td></tr>';
            html += '<tr><th>Other Information</th><td>'+jd.listOfComments[i].otherInfo+'</td></tr></table>';
            html += '<i class="icon-adt_trash"></i>';
            html += '</div></div></div>';
            //alert(html);
             $('.content').append(html);  
            });    
            
        });
  };
  
  function deleteComment(commentID){
    var JSONObject= {
       "deletedCommentID": commentID
    };
        
        $.ajax({
            type: "POST",
            url: '/SpamDetectionWeb/pages/deleteComment.action',
            dataType: "json",
            data:JSONObject,
            success: function (data) {
                alert("Comment successfully deleted");
                location.reload();
            }, //success
            error: function (req) {
                
                alert("Comment isn't saved due to some technical issue");
            }
        });
  
  }
  </script>

 <div class="row-fluid">
     <div class="span12 well well-small">
         <h1>List of Spam Reviews</h1>
     </div>
     <div class="span12" style="text-align:right; padding-right:3%">
         <input type="button" value="Ken" onclick="changeUser(4);"/>
         <input type="button" value="Keith" onclick="changeUser(3);"/>
         <input type="button" value="HuiMin" onclick="changeUser(2);"/>
         <input type="button" value="Others" onclick="changeUser(1);"/>
     </div>
 </div>
 
<div id="reviews">
    <div class="content"></div>
    <div class="page_navigation"></div>	 	
</div>
<div class="pagination pagination-centered">
    <ul id ="pagingBox"></ul>
</div>
 
  <jsp:include page="/layout/BottomLayout_1.jsp"/>
