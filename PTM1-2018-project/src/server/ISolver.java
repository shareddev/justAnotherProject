package server;

/*
 * ISolver handles an un-solved board game.
 * after receiving a game, 
 */
public interface ISolver<T> {
	public Solution<T> solve(ISearchable<T> searchable);
}
