<!--
 * Purpose: First half template for the all the remaining page. 
            Reusable for all other pages.(Need to include both TopLayout_1.jsp 
            and BottomLayout_1.jsp to make a complete template  
 * Date:14 July 2013
 * Author: Huimin
 * 
 -->
 <%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Spam Review Detection Panel</title>
    
    <!-- Bootstrap framework -->
        <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="../bootstrap/css/bootstrap-responsive.min.css"/>
    <!-- gebo blue theme-->
        <link rel="stylesheet" href="../css/blue.css"/>
    <!-- tooltips-->
        <link rel="stylesheet" href="../lib/qtip2/jquery.qtip.min.css" />
    <!-- main styles -->
        <link rel="stylesheet" href="../css/style.css"/>
        <link rel="stylesheet" href="../css/styles.css"/>
        <!--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=PT+Sans" />

    <!-- Favicon -->
        <!--<link rel="shortcut icon" href="favicon.ico" />

    <!--[if lte IE 8]>
        <link rel="stylesheet" href="css/ie.css" />
        <script src="js/ie/html5.js"></script>
                    <script src="js/ie/respond.min.js"></script>
    <![endif]-->

        <script>
                //* hide all elements & show preloader
                document.getElementsByTagName('html')[0].className = 'js';

        </script>

    </head>
    <body>
        <div id="maincontainer" class="clearfix">
            <header>
                <div class="navbar navbar-fixed-top">
                    <div class="navbar-inner">
                        <div class="container-fluid">
                            <a class="brand" href="#"><i class="icon-home icon-white"></i>&nbsp; <s:property value="name"/></a>
                            <nav>
                            <div class="nav-collapse">
                                <ul class="nav">
                                    <li class="dropdown">
					<a href="DisplayAllHotels.action" class="dropdown-toggle">Hotels</a>					
                                    </li>
                                    <li>
                                        <a href="DisplayAllReviewers.action" class="dropdown-toggle">Reviewers</a>
                                    </li>
                                    <li>
                                         <a href="DisplaySpamReviews.action" class="dropdown-toggle">Spam Reviews</a>
                                    </li>
                                    <li>
                                         <a href="logout.action" class="dropdown-toggle">Logout</a>
                                    </li>
                                </ul>
                            </div>
                         </nav>
                    </div>
                </div>
            </header>
            
            <!-- main content -->
            <div id="contentwrapper">
                <div class="main_content">
                    		
                