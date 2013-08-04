<%-- 
    Document   : DisplayAllReviewers
    Created on : Jul 14, 2013, 12:29:55 PM
    Author     : s amsung
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>

 <jsp:include page="/layout/TopLayout_1.jsp"/>
 <style>
     .list{
         width:33%;
         float:left;
     }
     #hotelLinks{
         padding-left:2%;
     }
     
 </style>
 
 <script>
 window.onload = function() {
    
    var html ="";
    $.getJSON('/SpamDetectionWeb/pages/processReviewerData.action', function(jd) {
        $.each(jd.listOfReviewers, function(i) {   
            var check = false;
            $.each(jd.listOfBuddies, function(j) {
                if (jd.listOfReviewers[i].reviewerID === jd.listOfBuddies[j].reviewerID || jd.listOfReviewers[i].reviewerID === jd.listOfBuddies[j].reviewerID_2){
                    check = true;
                }
            });
            if (check){
                if (jd.listOfReviewers[i].duplicatedDate==1){
                    html +='<li class="list data" style="background-color:#00FF80;"><a href="/SpamDetectionWeb/pages/DisplayPastReviews.action?reviewerID='+jd.listOfReviewers[i].reviewerID+'">'
                    +jd.listOfReviewers[i].name+'</a>&nbsp&nbsp&nbsp<small>'+jd.listOfReviewers[i].dateJoined+'</small></li>'
                }
                else{
                    html +='<li class="list data" style="background-color:#FF7373;"><a href="/SpamDetectionWeb/pages/DisplayPastReviews.action?reviewerID='+jd.listOfReviewers[i].reviewerID+'">'
                    +jd.listOfReviewers[i].name+'</a>&nbsp&nbsp&nbsp<small>'+jd.listOfReviewers[i].dateJoined+'</small></li>'
                }
            }
            else{
                if (jd.listOfReviewers[i].duplicatedDate==1){
                    html +='<li class="list data" style="background-color:#FF00FF;"><a href="/SpamDetectionWeb/pages/DisplayPastReviews.action?reviewerID='+jd.listOfReviewers[i].reviewerID+'">'
                    +jd.listOfReviewers[i].name+'</a>&nbsp&nbsp&nbsp<small>'+jd.listOfReviewers[i].dateJoined+'</small></li>'
                }
                else{
                    html +='<li class="list data"><a href="/SpamDetectionWeb/pages/DisplayPastReviews.action?reviewerID='+jd.listOfReviewers[i].reviewerID+'">'
                    +jd.listOfReviewers[i].name+'</a>&nbsp&nbsp&nbsp<small>'+jd.listOfReviewers[i].dateJoined+'</small></li>'
                }
            }
            
            
        });
        $('.content').html(html);
        $('#ReviewerLinks').pajinate({
                items_per_page : 360	
        });
    });
 }
 </script>
 <div class="row-fluid">
        <div class="span12 well well-small">
            <h1>List of Reviewers</h1>
            <small><i>*Those highlighted in red are potential group spammer with min support count >= 3</i></small><br/>
            <small><i>*Those highlighted in pink are potential reviewer with duplicated reviewed date</i></small><br/>
            <small><i>*Those highlighted in green contains all of the above</i></small><br/>
            <small><i>Lastly, this page will take some time to load due to massive data</i></small>
        </div>
 </div>
 <div class="row-fluid" style="margin-top:0">
     <div class="span12" id="ReviewerLinks">
         <div class="content"></div>
         <div style="margin-bottom:2%; clear:both"></div>
         <div class="page_navigation"></div>
     </div>
     
     <div class="pagination pagination-centered">
        <ul id ="pagingBox"></ul>
     </div>
 </div>
 <jsp:include page="/layout/BottomLayout_1.jsp"/>		