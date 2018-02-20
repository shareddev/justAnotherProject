package test;

/*
 * ISolver handles an un-solved board game.
 * after receiving a game, 
 */
public interface ISolver {
	//Eli said that the interfaces aren't suppose to know about or access
	//other implemented classes, but according to the HillClimbing code
	//we got from the assister, he put the Searchable type there
	public Solution solve(ISearchable<MyTile[][]> searchable);
}
