<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>ArrowSmith - Procedure Two</title>
	</head>
	<body>
		<div id="page-body" role="main">
			
			<h1>Procedure Two</h1>
			<div id="entry-form-div" style="margin-top:30px;">
				<g:form controller="search" action="search2" >
					<label for="termA">"A" term</label><g:textField name="termA"/>
					<label for="termC">"C" term</label><g:textField name="termC"/>
					<g:submitButton name="submit"/>
				</g:form>
			</div>
		</div>
	</body>
</html>