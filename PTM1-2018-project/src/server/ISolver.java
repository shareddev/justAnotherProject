package server;

/*
 * ISolver handles an un-solved board game.
 * after receiving a game, 
 */
public interface ISolver {
	public Solution solve(ISearchable<T> searchable);
}
