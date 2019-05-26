package valmont

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
		String cTerm = params.termC;
		
		List<SwansonABCLink> results = searchService.swansonLinkingProcedureOne( cTerm );
		
		[results:results];
	}
	
	def doArrowSmithProcedureTwo()
	{
		String aTerm = params.termA;
		String cTerm = params.termC;
		
		List<SwansonABCLink> results = searchService.swansonLinkingProcedureTwo( aTerm, cTerm );
		
		[results:results];
	}
}
