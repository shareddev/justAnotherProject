package server;

import java.util.ArrayList;
import java.util.Collection;

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
		//counting the numberOfRows and numberOfColumns
		setFromStringNumberOfColumnsAndRows(board);
		//converting the String the represents a board to a 2D MyTiles array
		setTilesFromString(board);
		//setting the values in the tiles
		setStartAndGoal();
		//setting the current tile as Source tile for our future ISearcher algorithms
		setCurrentTile(this.source);
	}
	



	//going over each character from the board string, using our knowlendge of the 
	private void setTilesFromString(String board) {
		this.tiles = new MyTile[this.numberOfColumns][this.numberOfRows];
		for (int i = 0; i < this.numberOfRows; i++) {
			for (int j = 0; j < this.numberOfColumns; j++) {
				this.tiles[i][j].setTileRowColumn(i, j);
				this.tiles[i][j].setTileValue(Character.toString(board.charAt(j)));
			}
		}
	}
	
	//counting the number of Rows and Columns that String holds
	private void setFromStringNumberOfColumnsAndRows(String board) {
		/*splitting the board to a String[] array
		 *the number of Strings in the String[] array are the number of
		 *rows we have
		 *the number of characters each string holds is the number of Columns
		*/
		String[] splitter = board.split("\n");
		this.numberOfColumns = splitter[0].length();
		this.numberOfRows = splitter.length;
	}
	
	//Getters
	public MyTile[][] getTiles() {
		return this.tiles;
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
	public int getNumberOfRows() {
		return this.numberOfRows;
	}
	public int getNumberOfColumns() {
		return this.numberOfColumns;
	}
	//Setters
	public void setCurrentTile(MyTile newCurrent) {
		this.current = newCurrent;
	}
	
	//setting the Start MyTile and Goal MyTile
	private void setStartAndGoal() {
		this.tiles = new MyTile[this.numberOfColumns][this.numberOfRows];
		for (int i = 0; i < this.numberOfRows; i++) {
			for (int j = 0; j < this.numberOfColumns ; j++) {
				if (tiles[j][i].getTileValue().equals("g"))
					this.goal = new MyTile(tiles[j][i]);
				else if (tiles[j][i].getTileValue().equals("s"))
					this.source  = new MyTile(tiles[j][i]);
			}
			//if we found both Source and Goal, we stop the loop
			if (this.goal.getTileValue().equals("g")
					&& this.source.getTileValue().equals("s"))
				break;
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
	
	//going over each tile from tiles, copying the Column, Row, Value and finishes the 
	//row with an "\n"
	//at the end of the we add "done\n" to indicate that we got the entire board
	@Override
	public String toString() {
		String string = new String("");
		for (int i = 0; i < this.getNumberOfRows(); i++) {
			for (int j = 0; j < this.getNumberOfColumns(); j++) {
				string = string.concat(
						this.getTiles()[i][j].toString()).concat(
								this.getTiles()[i][j].getTileValue()).concat("\n");
			}
			string = string.concat("done\n");
		}
		return string;
	}
	
	//getting all possible states that our board can produce
	@Override
	public Collection<State<BoardGame>> getAllStates(
			State<BoardGame> sourceState){
		
		//setting an ArrayList to hold all the possible states we will create
		ArrayList<State<BoardGame>> possibleStatesList = 
				new ArrayList<State<BoardGame>>();
		
		//creating a list of possible changes of the "Neighboring" States
		ArrayList<MyTile> neighboringStates = new ArrayList<MyTile>(
				sourceState.getState().getNeighboringTiles(
						sourceState.getState().getCurrent()));
		
		//creating a temporary State for each iteration of a State
		State<BoardGame> newState = new State<BoardGame>();
		
		for (int i = 0; i < neighboringStates.size() ; i++) {
			String compare = new String("");
			compare = neighboringStates.get(i).getTileValue();
			
			if(!compare.equals(" ") && !compare.equals("s") 
					&& !compare.equals("g")) {
				if(compare.equals("-") || compare.equals("|")) {
					
				}
			}
		}
	}



	//getting all the possible differand NeighoringTiles we can get
	//we put them into a collection ArrayList to make sure we won't repeat 
	//the same one
	private Collection<MyTile> getNeighboringTiles(MyTile current) {
		
		//an ArrayList<MyTile> to make sure we won't create duplicate states
		ArrayList<MyTile> neighboringTiles = new ArrayList<MyTile>();
		
		
		return null;
	}
	
	
	
	
	



}
