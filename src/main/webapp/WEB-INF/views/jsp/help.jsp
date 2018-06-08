<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
        <title>Help</title>
        <link href="styles/style.css" rel="stylesheet" />
        <link href="styles/bootstrap.css" rel="stylesheet" />
        <link href="styles/animate.min.css" rel="stylesheet" />
        <link href="styles/angular-material.css" rel="stylesheet" />
        <link href="styles/bootstrap.min.css" rel="stylesheet" />
    </head>
    
    <body>
        <div class="container">
            <div ng-app="compare" ng-controller="compare-controller" class="container compare-container">
                <div class="row" style="margin-top: 10px">
                    <h1 class="col-md-9"><i class="fas fa-file-alt"></i>&nbsp;&nbsp;Compare Two Documents</h1>

                    <div class="row col-md-3" style="font-size: 20px; margin-top: 28px">
                        <a href="about" style="color: #049DFF; text-decoration: none"><i class="fas fa-users"></i> &nbsp;About</a>&nbsp;&nbsp;&nbsp;&nbsp;

                        <a href="help" style="color: #049DFF; text-decoration: none"><i class="fas fa-question-circle"></i> Help</a>
                    </div>
                </div><hr/>
            </div>
                
                
<!--            <div class="contact">-->
<!--                <h2 class="contact-in">CONTACT</h2>-->
                <div class="col-md-6 contact-top">
                    <h3>User manual</h3>
                    <div class="user_manual">
                        <p> Mode 1: Upload 2 file to compare </p>
                        <ul>
                            <li><span>Step 1:</span> Click "Chọn tệp" button to upload file 1</li>
                            <li><span>Step 2:</span> Click "Chọn tệp" button to upload file 2</li>
                            <li><span>Step 3:</span> Click "Compare" button to return result</li>
                            <li><span>Last step:</span> Wait to return result </li>
                        </ul>

                        <p>Mode 2: Import 2 text to compare </p>
                        <ul>
                            <li><span>Step 1:</span> Click "Text" button to change mode</li>
                            <li><span>Step 2:</span> Enter text in "Text 1" textbox </li>
                            <li><span>Step 3:</span> Enter text in "Text 2" textbox</li>
                            <li><span>Step 4:</span> Click "Compare" button to return result</li>
                            <li><span>Last step:</span> Wait to return result </li>
                        </ul>
                    </div>
                    
                    
                </div>
                <div class="col-md-6 contact-top">
                    <h3>Do you have a problem using it?</h3>
                    <div>
                        <span>Your Name </span>		
                        <input type="text" value="" >						
                    </div>
                    <div>
                        <span>Your Email </span>		
                        <input type="text" value="" >						
                    </div>
                    <div>
                        <span>Subject</span>		
                        <input type="text" value="" >	
                    </div>
                    <div>
                        <span>Your Message</span>		
                        <textarea> </textarea>	
                    </div>
                    <input type="submit" value="SEND" >	
                    <div/>
                    <div/>
                </div>
                <div class="clearfix"> </div>
                
                <script src="scripts/fontawesome-all.js"></script>
                <script src="scripts/jquery.min.js"></script>
                <script src="scripts/jquery-ui.min.js"></script>
                <script src="scripts/jquery.signalR.min.js"></script>
                <script src="scripts/signalr.hubs.min.js"></script>
                <script src="scripts/angular.min.js"></script>
                <script src="scripts/angular-animate.min.js"></script>
                <script src="scripts/angular-file-model.js"></script>
                <script src="scripts/angular-aria.js"></script>
                <script src="scripts/angular-material.js"></script>
                <script src="scripts/ui-bootstrap.min.js"></script>
                <script src="scripts/ng-dropzone.js"></script>
                <script src='scripts/compareTwoFiles.js'></script>
                
            </div>
        </div>
    </body>
</html>