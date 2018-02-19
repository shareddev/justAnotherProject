package server;
//i might be over complicating this, mayne PipeGameBoard is really just enough
public abstract class BoardGame implements ISearchable<BoardGame> {
	private ITile tiles[][];
	private ITile start;
	private ITile goal;
	//an iterator for the future searcher
	private ITile current;
	
	private int numberOfRows;
	private int numberOfColumes;

	//C'Tor
	public BoardGame(ITile tiles[][], ITile current, int numberOfRows, int numberOfColumes) {
		this(tiles, null, null, current, numberOfRows, numberOfColumes);
		this.setStartAndGoal(this.tiles);
		
	}
	public BoardGame(ITile tiles[][], ITile start, ITile goal, 
			ITile current, int numberOfRows, int numberOfColumes) {
		
		this.tiles = tiles;
		this.start = start;
		this.goal = goal;
		this.current = current;
		this.numberOfRows = numberOfRows;
		this.numberOfColumes = numberOfColumes;
		
	}
	
	public ITile getGoal() {
		return this.goal;
	}
	public ITile getStart() {
		return this.start;
	}
	public ITile getCurrent() {
		return this.current;
	}
	
	//setting the Start ITile and Goal ITile
	private void setStartAndGoal(ITile[][] tiles) {
		for (int i = 0; i < this.numberOfColumes; i++) {
			for (int j = 0; j < this.numberOfRows ; j++) {
				if (tiles[j][i].getTileValue().equals('g'))
					this.goal = tiles[j][i];
				else if (tiles[j][i].getTileValue().equals('s'))
					this.start = tiles[j][i];
			}
			
		}
	}
	//Override
	public boolean equals(BoardGame boardGame) {
		if(this.getCurrent().equals(boardGame.getCurrent())
				&& this.getCurrent().getTileValue().equals(boardGame.getCurrent().getTileValue()))
			return true;
		else return false;
	}
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof BoardGame))
				return false;
		if (object == this)
			return true;
		else return this.equals((BoardGame) object);
	}
	
	
	
	
	



}
