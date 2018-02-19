package server;
//i might be over complicating this, mayne PipeGameBoard is really just enough
public abstract class BoardGame implements ISearchable<BoardGame> {
	//Nathan said that we might want to change the 2-Dim array
	//what if we have generic besides 2-D?
	private MyTile tiles[][];
	private MyTile source;
	private MyTile goal;
	//an iterator for the future searcher
	private MyTile current;
	
	private int numberOfRows;
	private int numberOfColumns;

	//C'Tor
	public BoardGame(String board) {
		//need to convert a string to a tiles[][]
	}

	public BoardGame(MyTile tiles[][], MyTile start, MyTile goal, 
			MyTile current, int numberOfRows, int numberOfColumns) {
		
		this.tiles = tiles;
		this.source = start;
		this.goal = goal;
		this.current = current;
		this.numberOfRows = numberOfRows;
		this.numberOfColumns = numberOfColumns;
		
	}
	
	public MyTile getGoal() {
		return this.goal;
	}
	public MyTile getStart() {
		return this.source;
	}
	public MyTile getCurrent() {
		return this.current;
	}
	public int getRows() {
		return this.numberOfRows;
	}
	public int getColumns() {
		return this.numberOfColumns;
	}
	
	//setting the Start MyTile and Goal MyTile
	private void setStartAndGoal(MyTile[][] tiles) {
		for (int i = 0; i < this.numberOfColumns; i++) {
			for (int j = 0; j < this.numberOfRows ; j++) {
				if (tiles[j][i].getTileValue().equals('g'))
					this.goal = tiles[j][i];
				else if (tiles[j][i].getTileValue().equals('s'))
					this.source = tiles[j][i];
			}
		}
	}
	//Override
	public boolean equals(BoardGame boardGame) {
		if(this.getCurrent().equals(boardGame.getCurrent())
				&& this.getCurrent().getTileValue()
					.equals(boardGame.getCurrent().getTileValue()))
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
