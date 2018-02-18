package server;

public class MySolver<GAME> implements ISolver<GAME> {

	//variables
	//can be PipeGame, 8SomethingGame
	//has to be for same kind of GAME as the ISolver
	private ISearchable<GAME> searchable;
	//can be bfs, dfs, HillClimbing
	private ISearcher searcher;
	
	public MySolver() {
		
	}
	
	public MySolver(ISearchable<GAME> searchable) {
		this(searchable, new ISearcher<?>());
	}
	
	public MySolver(ISearchable<GAME> searchable, ISearcher searcher) {
		this.searchable = searchable;
		this.searcher = searcher;
	}
	
	@Override
	public ISolution<GAME> solve(ISearchable<GAME> searchable) {
		
		return null;
	}

}
