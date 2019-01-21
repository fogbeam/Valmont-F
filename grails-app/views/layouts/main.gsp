<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title><g:layoutTitle default="Grails" /></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<g:javascript>
        window.appContext = '${request.contextPath}';
        window.baseUrl = '${createLink(absolute:true, uri:'/')}'
	</g:javascript>


	<!--  JQuery -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

	<!-- Latest compiled and minified Bootstrap CSS -->
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
		integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
		crossorigin="anonymous">
	
	<!-- Optional theme -->
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
		integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
		crossorigin="anonymous">
	
	<!-- Latest compiled and minified Bootstrap JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
		integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
		crossorigin="anonymous"></script>

	<script type="text/javascript"
     	src="${resource(dir:'javascripts', file:'jquery-confirm.min.js')}"></script>

	<script type="text/javascript"
     	src="${resource(dir:'javascripts', file:'notify.js')}"></script>

	<style>
	/* Move down content because we have a fixed navbar that is 50px tall */
	body {
		padding-top: 50px;
		padding-bottom: 20px;
	}
	</style>

	<g:layoutHead />
	<r:layoutResources />
</head>
<body>

	<div id="body">
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
	
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/" style="font-size:28pt;">
						Valmont
					</a>
				</div>
	
				
					<div id="navbarRow1" class="navbar-collapse collapse">
	
						<ul class="nav navbar-nav navbar-left">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Tools: <sec:username/> <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="#">Placeholder</a></li>
									<li role="separator" class="divider"></li>
									<li class="dropdown-header">Nav header</li>
									<li><a href="#">Placeholder</a></li>
								</ul>
							</li>

							<li style="margin-top:25px;" id="notificationArea"></li>
						
						</ul>
				  </div>
			</div>
		</nav>
	</div>

	<div>
		<g:layoutBody />
	</div>
	
	<g:javascript library="application" />
	<r:layoutResources />
	
</body>
</html>
