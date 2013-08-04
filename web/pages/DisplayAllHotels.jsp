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
    $.getJSON('/SpamDetectionWeb/pages/processHotelData.action', function(jd) {
        $.each(jd.listOfHotels, function(i) {   
           
            html +='<li class="list"><a href="/SpamDetectionWeb/pages/DisplayReviews.action?hotelID='+jd.listOfHotels[i].hotelID+'">'
                    +jd.listOfHotels[i].name+'</a>&nbsp&nbsp&nbsp<small>'+jd.listOfHotels[i].avgRating+' of 5 stars</small></li>'
        });
        
        $('#hotelLinks').html(html);
    });
 }
 </script>
 <div class="row-fluid">
        <div class="span12 well well-small">
            <h1>List of Hotels</h1>
        </div>
 </div>
 <div class="row-fluid">
     <div class="span12" id="hotelLinks">
         
     </div>
 </div>
 <jsp:include page="/layout/BottomLayout_1.jsp"/>		