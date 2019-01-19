package arrowsmith

class SearchController 
{

	def searchService;
	
	
    def search1()
	{
		String aTerm = params.termA;
		String cTerm = params.termC;
		
		List<SearchResult1> results = searchService.searchVersionOne( aTerm, cTerm );
		
		// return results for rendering in view...
		[results:results];
	}
}
