var progressbar=$("#progressbar");progressbar.progressbar({value:0});var app=angular.module('compare',['ui.bootstrap','file-model','ngMaterial','thatisuday.dropzone']).directive('bootstrapTooltip',function()
{return function(scope,element,attrs)
{attrs.$observe('title',function(title)
{element.tooltip('destroy');if(jQuery.trim(title))element.tooltip();});element.on('$destroy',function()
{element.tooltip('destroy');delete attrs.$$observers['title'];});};});;app.config(function($mdThemingProvider){$mdThemingProvider.theme('default').primaryPalette('light-blue').accentPalette('blue');});app.controller('compare-controller',function($scope,$http,$window)
{$scope.selectedScanSourceTypes='text';$scope.STEP_INIT='init';$scope.STEP_PROGRESS='progress';$scope.STEP_ERROR='errored';var PAGE_SOURCE_ID=1,PAGE_SUSPECTED_ID=2;$scope.IsSingalrReady=false;$scope.formData={};$scope.formData.suspected_text=$scope.formData.source_text="";$scope.suspected_mode=$scope.source_mode='url';$scope.progress_message="";$scope.step=$scope.STEP_INIT;$scope.source_converted_path=$scope.suspected_converted_path="";$scope.progress_number=0;var hub=$.connection.compareHubV2;function normalizeUrl(url)
{if(url.toLowerCase().startsWith('http'))
    return url
else
    return "http://"+url;}
    $scope.isValid=function(){if($scope.selectedScanSourceTypes=='file'){return $scope.haveFile1&&$scope.haveFile2;}
        return $scope.formData.source_text&&$scope.formData.suspected_text;}
    $scope.compare=function()
    {$window.scrollTo(0,0);var captcha=document.getElementById("g-recaptcha-response").value;if(captcha=="")
    {$scope.SetBadInputError("The captcha is missing");return;}
        function registerComparison(){hub.server.registerComparisonId().then(function(comparisonId){createComparison(comparisonId,captcha);});}
        if($.connection.hub.state===$.signalR.connectionState.disconnected){$.connection.hub.start().done(function(){registerComparison();});}
        else{registerComparison();}};function createComparison(comparisonId,captcha){$scope.step=$scope.STEP_PROGRESS;$scope.progress_number=10;$scope.progress_message='Creating a new comparison...';var formData=new FormData();if($scope.selectedScanSourceTypes=='file'){formData.append('files[]',$scope.newFile1);formData.append('files[]',$scope.newFile2);formData.append('text1','');formData.append('text2','');formData.append('comparisonType','file');}else{formData.append('text1',$scope.formData.source_text);formData.append('text2',$scope.formData.suspected_text);formData.append('comparisonType','text');}
    formData.append("captcha",captcha);formData.append("comparisonId",comparisonId);$http.post('uploadTocompare2',formData,{transformRequest:angular.identity,headers:{'Content-Type':undefined}}).then(function(result){if(!result.data.Success){$scope.SetBadInputError(result.data);$scope.progress_message=result.data;return;}
        window.location.href=result.data.Href;},function(err){console.log(err);});}
    $scope.updateProgress=function(message)
    {try
    {$scope.$apply(function(){$scope.progress_message=message;});}
    catch(err)
    {console.log(err);}}
    $scope.set_mode=function(isSource,mode)
    {if(isSource)
        $scope.source_mode=mode;else
        $scope.suspected_mode=mode;}
    $scope.SetBadInputError=function(message)
    {grecaptcha.reset();console.log('Incoming server error: '+message);$scope.ErrorMessage=message;$scope.step=$scope.STEP_ERROR}
    hub.client.notifyCompletion2=function(progress)
    {$scope.progress_number=Math.max(progress,10);if($scope.progress_number<progress)
        $scope.updateProgress('Waiting for second file...');else{$scope.updateProgress('Comparing documents...');}
        try
        {$scope.$apply();}
        catch(err)
        {console.log(err);}}
    $.connection.hub.start().done(function()
    {console.debug('signalr is now connected.');$scope.IsSingalrReady=true;try
    {$scope.$apply();}
    catch(err)
    {console.log(err);}});$scope.resetStep=function(){$scope.step=$scope.STEP_INIT;}
    $scope.cancel=function()
    {grecaptcha.reset();$scope.step=$scope.STEP_INIT;$scope.cid=null;}
    initDropZone();function initDropZone(){$scope.dzOptions={url:'/test',maxFilesize:'10',acceptedFiles:'application/pdf,.docx,.rtf,.xml,.pptx,.ppt,.odt,.chm,.epub,.odp,.ppsx,.pages,.xlsx,.xls,.csv,.txt',autoProcessQueue:false,}
    $scope.dzMethods1={};$scope.dzCallbacks1={'addedfile':function(file){$scope.haveFile1=true;$scope.newFile1=file;$scope.resetStep();}};$scope.removeFile1=function(){$scope.dzMethods1.removeAllFiles();$scope.haveFile1=false;$scope.resetStep();}
    $scope.dzMethods2={};$scope.dzCallbacks2={'addedfile':function(file){$scope.haveFile2=true;$scope.newFile2=file;$scope.resetStep();}};$scope.removeFile2=function(){$scope.dzMethods2.removeAllFiles();$scope.haveFile2=false;$scope.resetStep();}}});