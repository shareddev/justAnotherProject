package server;
//The class adapter that bridges between ISearcher to ISolver
public class SearcherAdapterSolver<GAME> implements ISolver<GAME> {

	private ISearcher<GAME> searcher;
	
	public SearcherAdapterSolver(ISearcher<GAME> searcher) {
		this.searcher = searcher;
	}
	
	@Override
	public Solution<GAME> solve(ISearchable<GAME> searchable) {
		return searcher.search(searchable);
	}
	

}
