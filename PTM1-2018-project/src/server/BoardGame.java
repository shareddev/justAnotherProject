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
				if(compare.equals("-") || compare.equals("|"))
					rotateNeighbotingTile(possibleStatesList, newState, sourceState,
							neighboringStates,2,i);
				else rotateNeighbotingTile(possibleStatesList, newState, sourceState,
						neighboringStates,4,i);
			}
		}
		return possibleStatesList;
	}


	//Rotating according to the PipeGame, need to ajust after testing
	private void rotateNeighbotingTile(ArrayList<State<BoardGame>> possibleStatesList, 
			State<BoardGame> newState,State<BoardGame> sourceState, 
				ArrayList<MyTile> neighboringStates, int timesToRotate, int i) {
		//resetting the given newState to add to the possibleStatesList
		for (int j = 1 ; j < timesToRotate ; j++) {
			newState = new State<BoardGame>(copyBoard(sourceState.getState()), 
					calCost(newState.getState()), sourceState);
			
			newState.getState().getBoardGame()[neighboringStates.get(i).getTileRow()]
												[neighboringStates.get(i).getTileColumn()]
														.rotateTile(j);
			newState.getState().setCurrentTile(newState.getState().getBoardGame()
					[neighboringStates.get(i).getTileRow()][neighboringStates.get(i).getTileColumn()]);
			if (isCanConnect(newState.getState().getBoardGame()
					[sourceState.getState().getCurrent().getTileRow()]
							[sourceState.getState().getCurrent().getTileColumn()]
							,newState.getState().getBoardGame()
							[newState.getState().getNumberOfRows()]
									[newState.getState().getNumberOfColumns()]) 
											&& !isExistLoop(newState)) 
				possibleStatesList.add(newState);
		}
	}



	private boolean isCanConnect(MyTile current, MyTile next) {
		String currentValue = new String(current.getTileValue());
		String nextValue = new String(next.getTileValue());
		
		if(currentValue.equals("s") || currentValue.equals("g")
		{
			// checking right
			if(next.getTileColumn()>currentValue.getTileColumn() && next.getRow()==currentValue.getRow())
			{
				if(next.getTileValue().equals("-") || next.getTileValue()=='7' ||next.getTileValue()=='J')
					return true;
			}	
			// checking left?
			else if(next.getTileColumn()<currentValue.getTileColumn() && next.getRow()==currentValue.getRow())
			{
				if(next.getTileValue().equals("-") || next.getTileValue()=='L' ||next.getTileValue()=='F')
					return true;
			}
			
			// checking down?
			else if(next.getTileColumn()==currentValue.getTileColumn() && next.getRow()>currentValue.getRow())
			{
				if(next.getTileValue()'|' || next.getTileValue()=='L' ||next.getTileValue()=='J')
					return true;
			}
			
			// checking up?
			else if(next.getTileColumn()==currentValue.getTileColumn() && next.getRow()<currentValue.getRow())
			{
				if(next.getTileValue()=='|' || next.getTileValue()=='7' ||next.getTileValue()=='F')
					return true;
			}
		}
		else if(currentValue.getTileValue()=='|')
		{
			// checking down?
			if(next.getTileColumn()==currentValue.getTileColumn() && next.getRow()>currentValue.getRow())
			{
				if(next.getTileValue()=='|' || next.getTileValue()=='L' || next.getTileValue()=='J'|| next.getTileValue()=='g')
					return true;
			}
			
			// checking up?
			else if(next.getTileColumn()==currentValue.getTileColumn() && next.getRow()<currentValue.getRow())
			{
				if(next.getTileValue()=='|' || next.getTileValue()=='7' ||next.getTileValue()=='F'|| next.getTileValue()=='g')
					return true;
			}
		}
		
		else if(currentValue.getTileValue()=='-')
		{
			// neigbor from right?
			if(next.getTileColumn()>currentValue.getTileColumn() && next.getRow()==currentValue.getRow())
			{
				if(next.getTileValue()=='-' || next.getTileValue()=='7' ||next.getTileValue()=='J'|| next.getTileValue()=='s')
					return true;
			}	
			// checking left?
			else if(next.getTileColumn()<currentValue.getTileColumn() && next.getRow()==currentValue.getRow())
			{
				if(next.getTileValue()=='-' || next.getTileValue()=='L' ||next.getTileValue()=='F'|| next.getTileValue()=='s')
					return true;
			}
		}
		else if(currentValue.getTileValue()=='F')
		{
			// neigbor from right?
			if(next.getTileColumn()>currentValue.getTileColumn() && next.getRow()==currentValue.getRow())
			{
				if(next.getTileValue()=='-' || next.getTileValue()=='7' ||next.getTileValue()=='J'|| next.getTileValue()=='g')
					return true;
			}
			
			// neigbor from down?
			else if(next.getTileColumn()==currentValue.getTileColumn() && next.getRow()>currentValue.getRow())
			{
				if(next.getTileValue()=='|' || next.getTileValue()=='L' ||next.getTileValue()=='J'|| next.getTileValue()=='g')
					return true;
			}
		}
		
		else if(currentValue.getTileValue()=='L')
		{
			// neigbor from right?
			if(next.getTileColumn()>currentValue.getTileColumn() && next.getRow()==currentValue.getRow())
			{
				if(next.getTileValue()=='-' || next.getTileValue()=='7' ||next.getTileValue()=='J'|| next.getTileValue()=='g')
					return true;
			}
			
			// neigbor from up?
			else if(next.getTileColumn()==currentValue.getTileColumn() && next.getRow()<currentValue.getRow())
			{
				if(next.getTileValue()=='|' || next.getTileValue()=='7' ||next.getTileValue()=='F'|| next.getTileValue()=='g')
					return true;
			}
		}
		
		else if(currentValue.getTileValue()=='7')
		{
			// neigbor from left?
			if(neighbor.getTileColumn()<currentTile.getTileColumn() && neighbor.getRow()==currentTile.getRow())
			{
				if(neighbor.getValue()=='-' || neighbor.getValue()=='L' ||neighbor.getValue()=='F'|| neighbor.getValue()=='g')
					return true;
			}
			// neigbor from down?
			else if(neighbor.getTileColumn()==currentTile.getTileColumn() && neighbor.getRow()>currentTile.getRow())
			{
				if(neighbor.getValue()=='|' || neighbor.getValue()=='L' || neighbor.getValue()=='J'|| neighbor.getValue()=='g')
					return true;
			}
		}
		
		else if(currentTile.getValue()=='J')
		{
			// neigbor from left?
			if(neighbor.getTileColumn()<currentTile.getTileColumn() && neighbor.getRow()==currentTile.getRow())
			{
				if(neighbor.getValue()=='-' || neighbor.getValue()=='L' ||neighbor.getValue()=='F'|| neighbor.getValue()=='g')
					return true;
			}
			//neigbor from up?
			else if(neighbor.getTileColumn()==currentTile.getTileColumn() && neighbor.getRow()<currentTile.getRow())
			{
				if(neighbor.getValue()=='|' || neighbor.getValue()=='7' ||neighbor.getValue()=='F'|| neighbor.getValue()=='g')
					return true;
			}
			
		}
		return false;
	}





	private boolean isExistLoop(State<BoardGame> newState) {
		State<BoardGame> previousState = new State<BoardGame>();
		
		if (newState.getCameFrom() == null) {
			return false;
		}
		else {
			previousState = newState.getCameFrom();
			while(previousState.getCameFrom() != null) {
				if(previousState.getState() != null
						&& newState.getState().getCurrent().equals(previousState.getState().getCurrent())
						&& newState.getState().getCurrent().toString().equals(previousState.getState().getCurrent().toString())) {
					newState = previousState;
					return true;
				}
				previousState = previousState.getCameFrom();
			}
		}
		return false;
	}





	//calculating a cost to the goal State, but i don't think
	//this is a good way, better fix it
	private double calCost(BoardGame state) {
		return Double.valueOf(Math.abs(state.getCurrent().getTileColumn() - 
				state.getGoal().getTileColumn() + 
				Math.abs(state.getCurrent().getTileRow() - 
						state.getGoal().getTileRow())));
	}





	private MyTile[][] getBoardGame() {
		return this.getTiles();
	}





	private BoardGame copyBoard(BoardGame board) {
		return
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
