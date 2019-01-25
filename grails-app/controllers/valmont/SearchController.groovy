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
	
    def doArrowSmithProcedureOne()
	{
		String aTerm = params.termA;
		
		List<SearchResult1> results = searchService.swansonLinkingProcedureOne( aTerm );
		
		// return results for rendering in view...
		[results:results];
	}
	
	def doArrowSmithProcedureTwo()
	{
		String aTerm = params.termA;
		String cTerm = params.termC;
		
		List<SearchResult1> results = new ArrayList<SearchResult1>();
		
		SearchResult1 result = searchService.swansonLinkingProcedureTwo( aTerm, cTerm );
		
		results.add( result );
		
		// return results for rendering in view...
		[results:results];
	}
	
}
