package server;

/*
 * ISolver handles an un-solved board game.
 * after receiving a game, 
 */
public interface ISolver<GAME> {
	public ISolution<GAME> solve(ISearchable<GAME> searchable);
	
	
}
