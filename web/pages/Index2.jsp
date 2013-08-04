<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            #loginBox{      
                width:50%;
                margin-left:auto;
                margin-right:auto;
                border:solid #3993ba;
                margin-top:15%;
                background-color:white;
                border-radius: 10px; 
            }
            body{
                background-image: url('../img/bg.jpg');
            }
            
            #imgBox{          
                width:50%;
                float:left;
                text-align: center;
            }
            #contentBox{
                float:left;
            }
            h1{
                border:solid #3993ba;
                border-top-left-radius: 10px;
                border-top-right-radius: 10px;
                margin-top:0;
                background-color:#3993ba;
                text-align: right;
                padding-right:2%;
                font-family:"Times New Roman", Times, serif;
            }
        </style>
        <script src="js/jquery.min.js"></script>
        <script>
            $(document).ready(function() {
                   
                   
            });
            
           /* function validateForm(){
                alert("hello");
                $.ajax({
                url: '/SpamDetectionWeb/pages/login.action',
                type: 'post',
                dataType: 'json',
                data: $('form#myForm').serialize(),
                success: function(data) {
                    alert("success");
                    }
                });
                return false;
            };*/
        </script>
        <title>Login Page</title>
    </head>
    <body>
        <div id="loginBox">
            <h1>Log In</h1>
            <div id="imgBox">
                <img src ="../img/lock-transparent.gif" width="200px" height="200px"/>
            </div>
            <div id="contentBox">
                Enter your username and password<br/>
            <form action ="login.action" method="POST" id="myForm">
                <table cellspacing="5">
                    <tr>
                        <th>Username :</th>
                        <td><input type="textbox" id="username" name="username"></td>
                    </tr>
                    <tr>
                        <th>Password :</th>
                        <td><input type="password" id="password" name="password"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Login" onsubmit="validateForm()"</td>
                    </tr>
                </table>
            </form>
            </div>
            <div style="clear:both"/>
        </div>
    </body>
</html>
