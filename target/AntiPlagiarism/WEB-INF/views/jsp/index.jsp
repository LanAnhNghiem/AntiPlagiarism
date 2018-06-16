<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>Compare Two Documents</title>
    <link href="styles/animate.min.css" rel="stylesheet"/>
    <link href="styles/angular-material.css" rel="stylesheet"/>
    <link href="styles/bootstrap.min.css" rel="stylesheet"/>
</head>

<body>
<div class="container">
    <div ng-app="compare" ng-controller="compare-controller" class="container compare-container">
        <div class="row" style="margin-top: 10px">
            <a href="<%=request.getContextPath()%>">
                <h1 class="col-md-9"><i class="fas fa-file-alt"></i>&nbsp;&nbsp;Compare Two Documents</h1>
            </a>

            <div class="row col-md-3" style="font-size: 20px; margin-top: 28px">
                <a href="about" style="color: #049DFF; text-decoration: none"><i class="fas fa-users"></i>
                    &nbsp;About</a>&nbsp;&nbsp;&nbsp;&nbsp;

                <a href="help" style="color: #049DFF; text-decoration: none"><i class="fas fa-question-circle"></i> Help</a>
            </div>
        </div>
        <hr/>
        <form action="upload" method="post" enctype="multipart/form-data" id="formSubmit">
            <div ng-cloak ng-show="step != STEP_PROGRESS">
                <div class="row text-left margin-bottom" style="margin-bottom: 15px">
                    <div class="col-xs-12">
                        <section layout="row" layout-align="center center">
                            <md-button class="groupX left md-raised"
                                       ng-click="selectedScanSourceTypes='file';resetStep()"
                                       ng-class="{'md-accent': selectedScanSourceTypes=='file'}"><strong><i
                                    class="fas fa-paperclip"></i>&nbsp;&nbsp;File<a href="<%=request.getContextPath()%>"></a></strong></md-button>
                            <a href="text">
                                <md-button class="groupX right md-raised"><strong><i
                                    class="far fa-file-alt"></i>&nbsp;&nbsp;Text</strong><a href=""></a></md-button>
                            </a>

                        </section>
                    </div>
                </div>
                <div class="radio">
                    <label><input type="radio" name="isEN" value="en" checked="checked">EN</label>
                    <label><input type="radio" name="isEN" value="vn">VI</label>
                </div>
                <div class="row text-left">
                    <div class="col-xs-12 col-md-6">
                        <div class="panel panel-default col-xs-12 col-md-12">
                            <div class="panel-heading">
                                <strong ng-show="selectedScanSourceTypes=='file'" style="font-size: 16px">FILE
                                    1</strong>
                                <strong ng-show="selectedScanSourceTypes=='text'" style="font-size: 16px">TEXT
                                    1</strong>
                            </div>

                            <div class="panel-body" ng-switch="selectedScanSourceTypes">
                                <div ng-switch-when="file" class="parent-center">
                                    <p style="font-size: 17px">
                                        Click to upload <span><strong>file 1</strong></span><br>

                                        <input required="required" type="file" name="files" size="50"
                                               accept=".txt,.doc,.docx,.xml,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document"
                                               style="margin-top: 3px">
                                    </p>
                                </div>

                                <%--<div class="form-group" ng-switch-when="text">--%>
                                    <%--<textarea class="form-control" ng-model="formData.source_text" rows="100"--%>
                                              <%--placeholder="Write your text here" ng-change="resetStep()"--%>
                                              <%--style="font-size: 18px; height: 206px; border-radius: 8px; resize: none"></textarea>--%>
                                <%--</div>--%>
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-12 col-md-6">
                        <div class="panel panel-default col-xs-12 col-md-12">
                            <div class="panel-heading">
                                <strong ng-show="selectedScanSourceTypes=='file'" style="font-size: 16px">FILE
                                    2</strong>
                                <strong ng-show="selectedScanSourceTypes=='text'" style="font-size: 16px">TEXT
                                    2</strong>
                            </div>

                            <div class="panel-body" ng-switch="selectedScanSourceTypes">
                                <div ng-switch-when="file" class="parent-center">
                                    <p style="font-size: 17px">
                                        Click to upload <span><strong>file 2</strong></span><br>

                                        <input required="required" type="file" name="files" size="50"
                                               accept=".txt,.doc,.docx,.xml,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document"
                                               style="margin-top: 3px">
                                    </p>
                                </div>

                                <%--<div class="form-group" ng-switch-when="text">--%>
                                    <%--<textarea class="form-control" ng-model="formData.suspected_text" rows="100"--%>
                                              <%--placeholder="Write your text here" ng-change="resetStep()"--%>
                                              <%--style="font-size: 18px; height: 206px; border-radius: 8px; resize: none"></textarea>--%>
                                <%--</div>--%>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row text-center">
                    <a class="btn btn-success btn-lg" id="btnSubmit"><strong><i
                            class="fas fa-filter"></i>&nbsp;COMPARE</strong><input type="submit" style="display: none"></a>
                </div>
                <input value="OK" type="submit" style="display: none" id="btnOK">
            </div>
        </form>
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
        <script>
            $("#btnSubmit").click(function() {
                $("#btnOK").click();
            })
        </script>
    </div>
</div>
</body>
</html>
