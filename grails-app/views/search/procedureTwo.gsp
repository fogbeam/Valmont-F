<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>Valmont/F: ArrowSmith Procedure Two</title>
</head>
<body>
	<div id="content" role="main" class="container-fluid">

		<div class="row">

			<!--  left -->
			<div class="col-md-1"></div>

			<!--  right -->
			<div class="col-md-11">

				<h1>Procedure Two</h1>
				<div id="entry-form-div" style="margin-top: 30px;">
					<g:form controller="search" action="doArrowSmithProcedureTwo">
						<label for="termA">"A" term</label>
						<g:textField name="termA" />
						<label for="termC">"C" term</label>
						<g:textField name="termC" />
						<g:submitButton name="submit" />
					</g:form>
				</div>
			</div>
		</div>

	</div>
</body>
</html>