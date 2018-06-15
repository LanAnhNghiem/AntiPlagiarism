<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
        <title>Compare Two Documents</title>
        <link rel="stylesheet" href="https://bootswatch.com/slate/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="styles/plugin.css">

        <!-- jQuery -->
        <style>
            * {
                box-sizing: border-box;
                margin: 0;
                padding: 0;
                border: 0;
            }

            html, body {
                height: 100%;
                font-family: 'Lora', serif;
            }

            .container { 
                margin:150px auto; 
                text-align:center;
            }
        </style>
    </head>
    
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-4 col-sm-offset-4">
                    <div class="my-progress-bar"></div>
                </div>
            </div>

            <h1>Please wait a moment</h1>
        </div>
        <script>
            window.location.replace("result")
        </script>
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script src="scripts/plugin.js"></script>
        <script src="scripts/script.js"></script>
        <script type="text/javascript">
            var _gaq = _gaq || [];
            _gaq.push(['_setAccount', 'UA-36251023-1']);
            _gaq.push(['_setDomainName', 'jqueryscript.net']);
            _gaq.push(['_trackPageview']);

            (function () {
                var ga = document.createElement('script');
                ga.type = 'text/javascript';
                ga.async = true;
                ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
                var s = document.getElementsByTagName('script')[0];
                s.parentNode.insertBefore(ga, s);
            })();
        </script>
    </body>
</html>
