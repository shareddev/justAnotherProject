package server;


public class MySolver implements ISolver {
	
	//can be bfs, dfs, HillClimbing
	private ISearcher<MyTile[][]> searcher;
	//can be PipeGame, 8SomethingGame
	//has to be for same kind of GAME as the ISolver
	private ISearchable<MyTile[][]> searchable;

	
	public MySolver(ISearcher<MyTile[][]> searcher) {
		this.solver = new SearcherAdapterSolver<MyTile[][]>(searcher);
	}
	
	@Override
	public Solution<MyTile[][]> solve(ISearchable<MyTile[][]> searchable) {
		return this.solver.solve(searchable);
	}


}
