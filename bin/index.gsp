<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to ArrowSmith</title>
	</head>
	<body>
		<div id="page-body" role="main">
			<h1>Welcome to ArrowSmith!</h1>
			<p>
			This is the Fogbeam reimplementation of the classic <a href="http://en.wikipedia.org/wiki/Arrowsmith_System">ArrowSmith</a> system.
			Here we will experiment with, and learn about, <a href="http://en.wikipedia.org/wiki/Literature-based_discovery">Literature Based Discovery</a>
			and hopefully come up with some nifty ways to integrate this into our Fogbeam offerings.
			</p>
			<div id="entry-form-div" style="margin-top:30px;">
				<g:form controller="search" action="search1" >
					<label for="termA">"A" term</label><g:textField name="termA"/>
					<label for="termC">"C" term</label><g:textField name="termC"/>
					<g:submitButton name="submit"/>
				</g:form>
			</div>
		</div>
		
	</body>
</html>
