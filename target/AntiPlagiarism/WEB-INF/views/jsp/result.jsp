<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
        <title>Compare Two Documents</title>
        <link href="styles/bootstrap.min.css" rel="stylesheet" />
        <link href="styles/progress.css" rel="stylesheet" />
        <script src="scripts/ui-bootstrap.min.js"></script>
        <script src="scripts/jquery.min.js"></script>
    </head>
    <body>
        <div class="container">
            <a href="index"><img src="images/back.png" width="40px" height="40px" style="margin-top: 20px"></a>

            <div class="row text-center" style="display: flex; align-items: center; justify-content: center; margin: 0px 0px 50px 0px">
                <div class="row text-left">
                    <strong style="font-size: 40px; color: blue">${result}</strong><span style="font-size: 35px"> identical</span><br>
                    <strong class="${resultColor}">No Plagiarism</strong>
                </div>
            </div>

            <div class="row text-left" style="margin-left: 8%">
                <div class="col-md-5" style="margin-right: 2%">
                    <div style="font-size: 18px; font-weight: bold; margin-bottom: 5px">TEXT 1</div>

                    <p style="font-size: 17px">Seems a little overkill for styling a single button, no? With border, padding, background, and other CSS effects you can style buttons and links to look similar without bringing over an entire framework. The methodology Bootstrap uses is good, however using Bootstrap seems excessive.</p>
                </div>

                <hr class="col-md-1" style="border: none; border-left: 1px solid hsla(200, 10%, 50%,100); height: 45vh; width: 1px">

                <div class="col-md-5">
                    <div style="font-size: 18px; font-weight: bold; margin-bottom: 5px">TEXT 2</div>

                    <p style="font-size: 17px">I'm not sure why this hasn't got more upvotes, to be honest. It works exactly as I need, and I did it this way because I wanted search engine spiders to be able to spot and follow the link. With JS that probably wouldn't have happened, and I'm not too sure of search engine behaviour when it comes to forms. It also doesn't result in any layout or behavioural side-effects.</p>
                </div>
            </div>
        </div>
    </body>
</html>

<style>
    .green  {
        color: #00CD66;
        font-size: 35px;
    }

    .red{
        color: red;
        font-size: 35px;
    }
</style>