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

				<p>
					<g:each in="${results}" var="result">

						<div
							style="float: left; width: 200px; background-color: #F8F8F8; min-height: 100px; margin-left: 20px;">

							"A" Results:

							<ul>
								<g:each in="${result.aTermDocs}" var="doc">
									<li><a target="_blank"
										href="http://www.ncbi.nlm.nih.gov/pubmed?term=${doc.uid}[uid]&cmd=DetailsSearch">${doc.uid}</a></li>
								</g:each>
							</ul>

						</div>

						<div
							style="float: left; width: 200px; background-color: #F8F8F8; min-height: 100px; margin-left: 20px;">

							"AB" Results:

							<ul>
								<g:each in="${result.abTermDocs}" var="doc">
									<li><a target="_blank" href='#'>${doc.uid}</a></li>
								</g:each>
							</ul>
						</div>

						<div
							style="float: left; width: 200px; background-color: #F8F8F8; min-height: 100px; margin-left: 20px;">

							"BC" Results:

							<ul>
								<g:each in="${result.bcTermDocs}" var="doc">
									<li><a target="_blank" href='#'>${doc.uid}</a></li>
								</g:each>
							</ul>
						</div>

						<div
							style="float: left; width: 200px; background-color: #F8F8F8; min-height: 100px; margin-left: 20px;">

							"C" Results:

							<ul>
								<g:each in="${result.cTermDocs}" var="doc">
									<li><a target="_blank"
										href="http://www.ncbi.nlm.nih.gov/pubmed?term=${doc.uid}[uid]&cmd=DetailsSearch">${doc.uid}</a></li>
								</g:each>
							</ul>
						</div>

					</g:each>
				</p>
				<div id="entry-form-div" style="clear: both; margin-top: 50px;">
					<p>Search again?</p>

					<g:form controller="search" action="search1">
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