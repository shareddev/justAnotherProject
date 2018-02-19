package server;

public class MySolver<GAME> implements ISolver<GAME> {

	//variables
	ISolver<GAME> solver;
	//can be PipeGame, 8SomethingGame
	//has to be for same kind of GAME as the ISolver
	private ISearchable<GAME> searchable;
	//can be bfs, dfs, HillClimbing
	private ISearcher searcher;
	
	public MySolver(ISearcher<GAME> searcher) {
		this.solver = new SearcherAdapterSolver<GAME>(searcher);
	}
	
	@Override
	public Solution<GAME> solve(ISearchable<GAME> searchable) {
		
		return null;
	}

}
