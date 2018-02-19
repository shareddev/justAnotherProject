package server;

/*
 * ISolver handles an un-solved board game.
 * after receiving a game, 
 */
public interface ISolver<GAME> {
	public Solution<GAME> solve(ISearchable<GAME> searchable);

	public String getRequiredChanges(String solution);
}
