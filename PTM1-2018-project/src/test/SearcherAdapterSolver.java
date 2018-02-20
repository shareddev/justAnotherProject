package test;

//The object adapter that bridges between ISearcher to ISolver
//The ISolver injects the needed algorithm here
public class SearcherAdapterSolver implements ISolver {

	private ISearcher<MyTile[][]> searcher;
	
	public SearcherAdapterSolver(ISearcher<MyTile[][]> searcher) {
		this.searcher = searcher;
	}
	
	//@Override
	public Solution solve(ISearchable<MyTile[][]> searchable) {
		return searcher.search(searchable);
	}



}
