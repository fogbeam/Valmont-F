<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>ArrowSmith - Procedure One</title>
	</head>
	<body>
		<div id="page-body" role="main">
			
			<h1>Procedure One</h1>
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