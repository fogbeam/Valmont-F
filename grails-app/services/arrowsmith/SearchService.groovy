package arrowsmith

import groovy.util.slurpersupport.GPathResult

import org.apache.http.HttpEntity
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils;

class SearchService 
{

    List<SearchResult1> searchVersionOne( final String aTerm, final String cTerm ) 
	{
		List<SearchResult1> searchResults = new ArrayList();
		
		
		// do a PubMedCentral search...
		String requestBaseUrl = "http://eutils.ncbi.nlm.nih.gov/entrez/eutils/";
		// http://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db=pubmed&term=science%5bjournal%5d+AND+breast+cancer+AND+2008%5bpdat%5d
		
		
		CloseableHttpClient httpclient = HttpClients.createDefault();

		
		XmlSlurper xmlSlurper = new XmlSlurper();
		
		CloseableHttpResponse responseATerm = null;
		CloseableHttpResponse responseASummary = null;
		CloseableHttpResponse responseCTerm = null;
		CloseableHttpResponse responseCSummary = null;
		try
		{
			
			SearchResult1 searchResult = new SearchResult1();

			// generate a list of items that contain the "A" term
			HttpGet httpgetATerm = new HttpGet(requestBaseUrl + "esearch.fcgi?db=pubmed&term=${aTerm}" );
			responseATerm = httpclient.execute(httpgetATerm);		
						
			
			HttpEntity entityATerm = responseATerm.getEntity();
			if (entityATerm != null) 
			{
				InputStream instreamATerm = entityATerm.getContent();
				try 
				{					
					
					// String responseText = instream.getText();
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
					
					// String responseText = instream.getText();
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
				
			
			// TODO: use the UIDs we got above, to download the title and abstract (at least)
			// of the selected documents.  For the ones that are fully open access, we may download
			// the full text of the paper as well.
			
			
			// generate a list of "B" terms common to both "A" and "C" documents
			// Note: This would exclude the original terms, and stop-words, no?
			
			
			
			// generate the "AB" list of links from A -> B
			// generate the "BC" list of links from b -> C
			
			
			
			searchResults.add( searchResult );
			
			
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
		
		return searchResults;
    }
}
