package spring;

// Place your Spring DSL code here
beans = {
	
	generalStopwordsBean(valmont.StopwordList)
	{
		fileName = "/usr/local/tomcat/webapps/stopwords.csv";
		fileFormat = "csv";
	}

	
	clinicalStopwordsBean(valmont.StopwordList)
	{
		fileName = "/usr/local/tomcat/webapps/clinical-stopwords.txt";
		fileFormat = "linesep";
	}
}
