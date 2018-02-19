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
	
	private void setStartAndGoal(ITile[][] boardGame) {
		for (int i = 0; i < this.numberOfColumes; i++) {
			for (int j = 0; j < this.numberOfRows ; j++) {
				if (boardGame[j][i].getTileValue().equals('g'))
					this.goal = boardGame[j][i];
				else if (boardGame[j][i].getTileValue().equals('s'))
					this.start = boardGame[j][i];
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
