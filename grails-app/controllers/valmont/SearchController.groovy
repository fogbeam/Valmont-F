package valmont

import valmont.SearchResult1

class SearchController 
{

	def searchService;
	
	
	
	def procedureOne()
	{
		
		[:];
	}
	
	def procedureTwo()
	{
		[:];	
	}
	
    def search1()
	{
		String aTerm = params.termA;
		String cTerm = params.termC;
		
		List<SearchResult1> results = searchService.searchVersionOne( aTerm, cTerm );
		
		// return results for rendering in view...
		[results:results];
	}
}
