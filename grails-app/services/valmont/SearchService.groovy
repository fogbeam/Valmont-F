package valmont

import org.apache.http.HttpEntity
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients

class SearchService 
{	
	List<SearchResult1> swansonLinkingProcedureOne( final String aTerm )
	{
		List<SearchResult1> searchResults = new ArrayList();
		
		return searchResults;
	}
	
	public SearchResult1 swansonLinkingProcedureTwo( final String aTerm, final String cTerm ) 
	{	
		SearchResult1 searchResult = new SearchResult1();
		
		List<String> stopWords = new ArrayList<String>();
		File stopWordsFile = new File( "./stopwords.csv" );
		FileReader stopWordsReader = new FileReader( stopWordsFile );
		BufferedReader bufferedSWR = new BufferedReader( stopWordsReader );
		
		String stopWordsLine = bufferedSWR.readLine();
		String[] stopWordsArray = stopWordsLine.split( "," );
		stopWords.addAll( stopWordsArray );
		
		
		// do a PubMedCentral search...
		String requestBaseUrl = "http://eutils.ncbi.nlm.nih.gov/entrez/eutils/";
		// http://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db=pubmed&term=science%5bjournal%5d+AND+breast+cancer+AND+2008%5bpdat%5d	
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		XmlSlurper xmlSlurper = new XmlSlurper();
		xmlSlurper.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false)
		xmlSlurper.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			
		
		List<String> aTermTitles = new ArrayList<String>();
		List<String> cTermTitles = new ArrayList<String>();
			
		CloseableHttpResponse responseATerm = null;
		CloseableHttpResponse responseASummary = null;
		CloseableHttpResponse responseCTerm = null;
		CloseableHttpResponse responseCSummary = null;
		try
		{
			// generate a list of items that contain the "A" term
			HttpGet httpgetATerm = new HttpGet(requestBaseUrl + "esearch.fcgi?db=pubmed&term=${aTerm}" );
			responseATerm = httpclient.execute(httpgetATerm);		
			
			println "status: ${responseATerm.statusLine}";
			
			HttpEntity entityATerm = responseATerm.getEntity();
			if (entityATerm != null) 
			{
				InputStream instreamATerm = entityATerm.getContent();
				try 
				{					
					
					// String responseText = instreamATerm.getText();
					// println "responseText: " + responseText;
					def xmlResult = xmlSlurper.parse( instreamATerm );
					
					// println "xmlResult: " + xmlResult;
					
					// add the aTerm results to the SearchResult object
					def idList = xmlResult.IdList.Id;
					
					idList.each {
						println "adding: " + it;
						ResultDocument1 doc = new ResultDocument1();
						doc.uid = it;
						
						// use this UID to locate the title and abstract and save an object into our
						// collection that has fields for UID, Title, Abstract, etc.
						
						// http://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&id=24600463&retmode=xml
						HttpGet getASummary = new HttpGet(requestBaseUrl + "efetch.fcgi?db=pubmed&id=${doc.uid}&retmode=xml&email=prhodes@fogbeam.com&tool=arrowsmithg" );
						responseASummary = httpclient.execute( getASummary );
						HttpEntity entityASummary = responseASummary.getEntity();
						if( entityASummary != null )
						{
							// EntityUtils.consumeQuietly(entityASummary);
							// TODO: extract title and abstract here...
							InputStream instreamASummary = entityASummary.getContent();
							try
							{
								def aSummaryXmlResult = xmlSlurper.parse( instreamASummary );
								def articleTitle = aSummaryXmlResult.depthFirst().findAll { it.name() == 'ArticleTitle' }[0]
								// println "aSummaryXmlResult: ${ articleTitle.text() }";
								aTermTitles.add( articleTitle.text() );
							}
							finally
							{
								instreamASummary.close();
							}
						}
						
						searchResult.aTermDocs.add( doc );
						Thread.sleep( 750 );
					}
				} 
				finally 
				{
					instreamATerm.close();
				}
			}
			
			
			
			
			// generate a list of items that contain the "C" term
			HttpGet httpgetCTerm = new HttpGet(requestBaseUrl + "esearch.fcgi?db=pubmed&term=${cTerm}" );
			responseCTerm = httpclient.execute(httpgetCTerm);
						
			
			HttpEntity entityCTerm = responseCTerm.getEntity();
			if (entityCTerm != null)
			{
				InputStream instreamCTerm = entityCTerm.getContent();
				try
				{
					
					// String responseText = instreamCTerm.getText();
					// println "responseText: " + responseText;
					def xmlResult = xmlSlurper.parse( instreamCTerm );
					
					// println "xmlResult: " + xmlResult;
					
					// add the aTerm results to the SearchResult object
					def idList = xmlResult.IdList.Id;
					
					idList.each {
						println "adding: " + it;
						ResultDocument1 doc = new ResultDocument1();
						doc.uid = it;
						
						// use this UID to locate the title and abstract and save an object into our
						// collection that has fields for UID, Title, Abstract, etc.
						
						// http://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&id=24600463&retmode=xml
						HttpGet getCSummary = new HttpGet(requestBaseUrl + "efetch.fcgi?db=pubmed&id=${doc.uid}&retmode=xml&email=prhodes@fogbeam.com&tool=arrowsmithg" );
						responseCSummary = httpclient.execute( getCSummary );
						HttpEntity entityCSummary = responseCSummary.getEntity();
						if( entityCSummary != null )
						{
							// EntityUtils.consumeQuietly(entityASummary);
							// TODO: extract title and abstract here...
							InputStream instreamCSummary = entityCSummary.getContent();
							try
							{
								def cSummaryXmlResult = xmlSlurper.parse( instreamCSummary );
								def articleTitle = cSummaryXmlResult.depthFirst().findAll { it.name() == 'ArticleTitle' }[0];
								// println "cSummaryXmlResult: ${ articleTitle }";
								cTermTitles.add( articleTitle.text() );
							}
							finally
							{
								instreamCSummary.close();
							}
						}
						
						searchResult.cTermDocs.add( doc );
						Thread.sleep( 750 );
					}
				}
				finally
				{
					instreamCTerm.close();
				}
			}
				
						
			Map<String, Boolean> candidateBTerms = new HashMap<String,Boolean>();
			
			// generate a list of "B" terms common to both "A" and "C" documents
			// Note: This would exclude the original terms, and stop-words, no?
			for( String aTermTitle : aTermTitles )
			{
				// tokenize this...
				
				// for everything except stop words and the original aTerm, store the term in our
				// candidate list
				String[] tokens = aTermTitle.split( "\\s+" );
				
				for( String token : tokens )
				{
					if( !token.equals( aTerm ) && !stopWords.contains( token.toLowerCase() ) )
					{
						candidateBTerms.put( token, false );
					}
				}
				
			}

			for( String cTermTitle : cTermTitles )
			{
				// tokenize this...
				
				// for everything except stop words and the original aTerm, store the term in our
				// candidate list
				String[] tokens = cTermTitle.split( "\\s+" );
				
				for( String token : tokens )
				{
					if( !token.equals( cTerm ) && !stopWords.contains( token ) )
					{
						if( candidateBTerms.containsKey( token.toLowerCase() ))
						{
							candidateBTerms.replace( token, true );
						}
						else
						{
							candidateBTerms.put( token, false );
						}
					}
				}
			}
			
			
			for( Map.Entry<String,Boolean> bCandidate : candidateBTerms )
			{
				if( bCandidate.value == true )
				{
					println( "bWord: " + bCandidate.key );
				}
			}
			
			return searchResult;
		} 
		finally 
		{
			if( responseATerm != null )
			{
				responseATerm.close();
			}
			
			if( responseASummary != null )
			{
				responseASummary.close();
			}
			
			if( responseCTerm != null )
			{
				responseCTerm.close();
			}
			
			if( responseCSummary != null )
			{
				responseCSummary.close();
			}
		}
    }
}
