package server;

public class MySolver<T> implements ISolver<T> {

	//variables
	ISolver<T> solver;
	//can be PipeGame, 8SomethingGame
	//has to be for same kind of GAME as the ISolver
	private ISearchable<T> searchable;
	//can be bfs, dfs, HillClimbing
	private ISearcher searcher;
	
	public MySolver(ISearcher<T> searcher) {
		this.solver = new SearcherAdapterSolver<T>(searcher);
	}
	
	@Override
	public Solution<T> solve(ISearchable<T> searchable) {
		
		return null;
	}

	@Override
	public String getRequiredChanges(String solution) {
		
	}

}
