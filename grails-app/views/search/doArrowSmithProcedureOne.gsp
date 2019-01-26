<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>Valmont/F: Arrowsmith Procedure One Results</title>
</head>
<body>
	<div id="content" role="main" class="container-fluid">

		<div class="row">

			<!--  left -->
			<div class="col-md-1"></div>

			<!--  right -->
			<div class="col-md-11">


				<h1>Welcome to Valmont/F!</h1>
				<h2>Arrowsmith Procedure One results:</h2>
		
				<g:each in="${results}" var="result">
						<div class="row">
							<div class="col-md-4" style="min-height: 100px;">
	
								${result.bTerm}
	
							</div>
	
							<div class="col-md-4" style="min-height: 100px;">
							
								"A" Term results:
								<ul>
									<g:each in="${result.aTermDocs}" var="doc">
										<li><a target="_blank"
											href="http://www.ncbi.nlm.nih.gov/pubmed?term=${doc.uid}[uid]&cmd=DetailsSearch">${doc.uid} - ${doc.title}</a></li>
									</g:each>
								</ul>
	
							</div>
						</div>
				</g:each>
	
		</div>
		<div class="row">
				<div id="entry-form-div" class="col-md-11" style="margin-top: 50px;">
					<p>Search again?</p>

					<g:form controller="search" action="search1">
						<label for="termA">"A" term</label>
						<g:textField name="termA" />
						<g:submitButton name="submit" />
					</g:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>