package server;
//The object adapter that bridges between ISearcher to ISolver
public class SearcherAdapterSolver<T> implements ISolver<T> {

	private ISearcher<T> searcher;
	
	public SearcherAdapterSolver(ISearcher<T> searcher) {
		this.searcher = searcher;
	}
	
	@Override
	public Solution<T> solve(ISearchable<T> searchable) {
		return searcher.search(searchable);
	}



}
