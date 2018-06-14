<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
            <a href="/AntiPlagiarism/"><img src="images/back.png" width="40px" height="40px" style="margin-top: 20px"></a>

            <div class="row text-center" style="display: flex; align-items: center; justify-content: center; margin: 0px 0px 50px 0px">
                <div class="row text-left">
                    <strong style="font-size: 40px; color: blue">${finalScore} %</strong><span style="font-size: 35px"> identical</span><br>
                    <strong class="${resultColor}" style="padding-left: 20px">${resultMess}</strong>
                </div>
            </div>

            <div class="row text-left" style="margin-left: 8%">
                <div class="col-md-5" style="margin-right: 2%">
                    <div style="font-size: 18px; font-weight: bold; margin-bottom: 5px">TEXT 1</div>
                        <table class="table table-striped table-hover">
                            <tr>
                                <th>Sentences</th>
                                <th>Score</th>
                            </tr>
                            <c:forEach items="${testPragraphResult}" var="testResult">
                            <tr>
                                <td>${testResult.getSentenceContent()}</td>
                                <td>${testResult.getScore()}</td>
                            </tr>
                            </c:forEach>
                        </table>
                </div>

                <hr class="col-md-1" style="border: none; border-left: 1px solid hsla(200, 10%, 50%,100); height: 45vh; width: 1px">

                <div class="col-md-5">
                    <div style="font-size: 18px; font-weight: bold; margin-bottom: 5px">TEXT 2</div>
                    <table class="table table-striped table-hover">
                        <tr>
                            <th>Sentences</th>
                            <th>Score</th>
                        </tr>
                        <c:forEach items="${orinPragraphResult}" var="orinResult">
                            <tr>
                                <td>${orinResult.getSentenceContent()}</td>
                                <td>${orinResult.getScore()}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>

<style>
    .green  {
        color: #00CD66;
        font-size: 35px;
        text-align: center;
    }

    .red{
        color: red;
        font-size: 35px;
        text-align: center;
    }
</style>