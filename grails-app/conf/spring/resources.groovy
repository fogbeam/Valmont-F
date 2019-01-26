package spring;

// Place your Spring DSL code here
beans = {
	
	generalStopwordsBean(valmont.StopwordList)
	{
		fileName = "stopwords.csv";
		fileFormat = "csv";
	}

	
	clinicalStopwordsBean(valmont.StopwordList)
	{
		fileName = "clinical-stopwords.txt";
		fileFormat = "linesep";
	}
}
