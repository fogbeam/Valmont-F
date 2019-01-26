package valmont

import org.apache.http.HttpEntity
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils

class SearchService 
{	
	List<SearchResult1> swansonLinkingProcedureOne( final String aTerm )
	{
		List<SearchResult1> searchResults = new ArrayList();
		
		return searchResults;
	}
	
	public List<SwansonABCLink> swansonLinkingProcedureTwo( final String aTerm, final String cTerm ) 
	{	
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
			
		
		List<ResultDocument1> aTermDocuments = new ArrayList<ResultDocument1>();
		List<ResultDocument1> cTermDocuments = new ArrayList<ResultDocument1>();
			
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
							// TODO: also extract and store abstract here...
							InputStream instreamASummary = entityASummary.getContent();
							String aSummaryText = EntityUtils.toString( entityASummary );
							try
							{
								def aSummaryXmlResult = xmlSlurper.parseText( aSummaryText );
								def articleTitle = aSummaryXmlResult.depthFirst().findAll { it.name() == 'ArticleTitle' }[0]
								// println "aSummaryXmlResult: ${ articleTitle.text() }";
								doc.title = articleTitle.text()
								aTermDocuments.add( doc );
							}
							catch( Exception e )
							{
								println "Failed parsing XML: \n ${aSummaryText}"
							}

							finally
							{
								instreamASummary.close();
							}
						}
			
						Thread.sleep( 1000 );
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
							String cSummaryText = EntityUtils.toString( entityCSummary );
							try
							{
								def cSummaryXmlResult = xmlSlurper.parseText( cSummaryText );
								def articleTitle = cSummaryXmlResult.depthFirst().findAll { it.name() == 'ArticleTitle' }[0];
								// println "cSummaryXmlResult: ${ articleTitle }";
								doc.title = articleTitle.text();
								cTermDocuments.add( doc );
							}
							catch( Exception e )
							{
								println "Failed parsing XML: \n ${cSummaryText}"
							}
							finally
							{
								instreamCSummary.close();
							}
						}
						
						Thread.sleep( 1000 );
					}
				}
				finally
				{
					instreamCTerm.close();
				}
			}
				
						
			// Map<String, Boolean> candidateBTerms = new HashMap<String,Boolean>();
			Map<String, SwansonABCLink> candidateSwansonLinks = new HashMap<String, SwansonABCLink>();
			
			/* NOTE: rework this to where it iterates over a list of Objects where each Object
			 * contains the title, uid, abstract, etc., instead of only iterating over the Titles
			 * This way we can store a list of matching results right along with each bTerm
			 * without having to do a lot of extra work. 
			 */
			
			// generate a list of "B" terms common to both "A" and "C" documents
			// Note: This would exclude the original terms, and stop-words, no?
			for( ResultDocument1 aTermDocument : aTermDocuments )
			{
				// tokenize this...
				
				// for everything except stop words and the original aTerm, store the term in our
				// candidate list
				String[] tokens = aTermDocument.title.split( "\\s+" );
				
				for( String token : tokens )
				{
					if( !token.equals( aTerm ) && !stopWords.contains( token.toLowerCase() ) )
					{
						// check if we already have this key (bTerm).  If not, add a new SwansonABCLink
						// with this aTerm.  If we do already have this key (bTerm) then
						// get our existing SwansonABCLink object and add this doc to the
						// aTerms list
						if( candidateSwansonLinks.containsKey( token.toLowerCase() ))
						{
							SwansonABCLink existingLink = candidateSwansonLinks.get( token.toLowerCase() );
							existingLink.aTermDocs.add( aTermDocument );
						}
						else
						{
							SwansonABCLink newLink = new SwansonABCLink();
							newLink.bTerm = token;
							newLink.aTermDocs.add( aTermDocument );
							candidateSwansonLinks.put( token.toLowerCase(), newLink );
						}
					}
				}
				
			}

			for( ResultDocument1 cTermDocument : cTermDocuments )
			{
				// tokenize this...
				
				// for everything except stop words and the original aTerm, store the term in our
				// candidate list
				String[] tokens = cTermDocument.title.split( "\\s+" );
				
				for( String token : tokens )
				{
					if( !token.equals( cTerm ) && !stopWords.contains( token.toLowerCase() ) )
					{
						
						// check if we already have this key (bTerm).  If not, add a new SwansonABCLink
						// with this cTerm.  If we do already have this key (bTerm) then
						// get our existing SwansonABCLink object and add this doc to the
						// cTerms list
						
						if( candidateSwansonLinks.containsKey( token.toLowerCase() ))
						{
							SwansonABCLink existingLink = candidateSwansonLinks.get( token.toLowerCase() );
							existingLink.cTermDocs.add( cTermDocument );
						}
						else
						{
							SwansonABCLink newLink = new SwansonABCLink();
							newLink.bTerm = token;
							newLink.cTermDocs.add( cTermDocument );
							candidateSwansonLinks.put( token.toLowerCase(), newLink );
						}
					}
				}
			}
			
			
			
			// TODO: iterate over the list of candidateSwansonLinks and find every 
			// item where the aTermDocs list and the cTermDocs list both have at least
			// one entry.  Those are our real bTerms. Return the list of those bTerms
			// and the associated A/C doc lists...
			List<SwansonABCLink> swansonLinks = new ArrayList<SwansonABCLink>();

			for( Map.Entry<String, SwansonABCLink> entry : candidateSwansonLinks.entrySet() )
			{
				SwansonABCLink link = entry.getValue();
				if( link.aTermDocs.size() > 0 && link.cTermDocs.size() > 0 )
				{
					swansonLinks.add( link );
				}
			}
			
			return swansonLinks;
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
