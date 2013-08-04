<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>

 <jsp:include page="/layout/TopLayout.jsp"/>

 <script>
 window.onload = function() {
    $.getJSON('/SpamDetectionWeb/pages/processData.action', function(jd) {
        
        $('#heading').html(jd.hotel.name);
        $('#heading').append('&nbsp &nbsp<small>' + jd.hotel.avgRating +" of 5 stars</small>");
        $('#subHeading').html('<small>' + jd.hotel.location + '</small> &nbsp &nbsp &nbsp')
        $('#subHeading').append('<span class="label label-info">' + jd.hotel.totalNumReviews +' reviews</span>');
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
            html += '<a href="#"><i class="icon-flag"></i></a></p></div>';
            html += '<div class="span4 well well-small"><h3>'+jd.listOfReviews[i].reviewer.name+'</h3>';
            html += '<table class="table"><tr><th>Username</th><td>'+jd.listOfReviews[i].reviewer.username+'</td></tr>';
            html += '<tr><th>Location</th><td>'+jd.listOfReviews[i].reviewer.location+'</td></tr>';
            html += '<tr><th>DateJoined</th><td>'+jd.listOfReviews[i].reviewer.dateJoined +'</td></tr>';
            html += '<tr><th>Gender</th><td>'+jd.listOfReviews[i].reviewer.gender+'</td></tr>';
            html += '<tr><th>No. Of Written Reviews</th><td>'+jd.listOfReviews[i].reviewer.numWrittenReviews+'</td></tr></table>';
            html += '</div></div></div>';
            $('.content').append(html);  
            });
         
            $('#reviews').pajinate({
                items_per_page : 5	
            });
        });
        
     };

   
  </script>
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
 <jsp:include page="/layout/BottomLayout.jsp"/>					
                

