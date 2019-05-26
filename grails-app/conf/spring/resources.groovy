package spring;

// Place your Spring DSL code here
beans = {
	
	generalStopwordsBean(valmont.StopwordList)
	{
		fileName = "${grailsApplication.config.stopwords.path.general}";
		fileFormat = "csv";
	}

	
	clinicalStopwordsBean(valmont.StopwordList)
	{
		fileName = "${grailsApplication.config.stopwords.path.clinical}";
		fileFormat = "linesep";
	}
}
