package server;
//
import java.util.ArrayList;
import java.util.Collection;

//i might be over complicating this, maybe PipeBoardGame is really just enough
public class PipeBoardGame implements ISearchable<PipeBoardGame> {
	//variables
	//MyTile is searchable
	protected MyTile tiles[][];
	protected MyTile source;
	protected MyTile goal;
	//an iterator for the future searcher
	protected MyTile current;
	
	protected int numberOfRows;
	protected int numberOfColumns;
	
	//C'Tor
	public PipeBoardGame(String board) {
		//counting the numberOfRows and numberOfColumns
		setFromStringNumberOfColumnsAndRows(board);
		//converting the String the represents a board to a 2D MyTiles array
		setTilesFromString(board);
		//setting the values in the tiles
		setStartAndGoal();
		//setting the current tile as Source tile for our future ISearcher algorithms
		this.setCurrent(this.source);
	}
	
	public PipeBoardGame(MyTile[][] Tiles, int numberOfColumns, 
			int numberOfRows, MyTile source, MyTile goal, MyTile current) {
		this.setTiles(tiles);
		this.setNumberOfColumns(numberOfColumns);
		this.setNumberOfRows(numberOfRows);
		this.setSource(source);
		this.setGoal(goal);
		this.setCurrent(current);
	}



	protected MyTile[][] getTiles() {
		return tiles;
	}
	protected void setTiles(MyTile[][] tiles) {
		this.tiles = tiles;
	}
	protected MyTile getSource() {
		return source;
	}
	protected void setSource(MyTile source) {
		this.source = source;
	}
	protected MyTile getGoal() {
		return goal;
	}
	protected void setGoal(MyTile goal) {
		this.goal = goal;
	}
	protected MyTile getCurrent() {
		return current;
	}
	protected void setCurrent(MyTile current) {
		this.current = current;
	}
	protected int getNumberOfRows() {
		return numberOfRows;
	}
	protected void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}
	protected int getNumberOfColumns() {
		return numberOfColumns;
	}
	protected void setNumberOfColumns(int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
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
	
	
	
	//setting the Start MyTile and Goal MyTile
	protected void setStartAndGoal() {
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
	public boolean equals(PipeBoardGame boardGame) {
		if(this.getCurrent().equals(boardGame.getCurrent())
				&& this.getCurrent().getTileValue()
					.equals(boardGame.getCurrent().getTileValue()))
			return true;
		else return false;
	}
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof PipeBoardGame))
				return false;
		if (object == this)
			return true;
		else return this.equals((PipeBoardGame) object);
	}
	
	//converts the Tiles[][] back to a string the represents a BoardGame
	@Override
	public String toString() {
		String string = new String("");
		for (int i = 0; i < this.getNumberOfRows(); i++) {
			for (int j = 0; j < this.getNumberOfColumns(); j++) {
			string = string.concat(this.getTiles()[j][i].getTileValue());
			}
			string = string.concat("\n");
		}
		return string;
	}
	
	//an abstract method for each Game to decide if the current State is the desired State
	@Override
	public boolean getGoalState(State<PipeBoardGame> goalState)
	{
		ArrayList<MyTile> neighbors = new ArrayList<MyTile>(this.getNeighboringTiles(this.getSource()));
		for (int i = 0; i < neighbors.size() ; i++) {
			if(isExistSourceToGoalPath())
				return true;
		}
		return false;
	}
	
	
	private boolean isExistSourceToGoalPath() {
		ArrayList<MyTile> neighbors = new ArrayList<MyTile>
		(this.getNeighboringTiles(this.getSource()));
		
		for (int i = 0; i < neighbors.size() ; i++) {
			if(this.isPath(neighbors.get(i), this.getSource()))
				return true;
		}
		return false;
	}

	protected boolean isPath(MyTile current, MyTile previous) {
		if(this.isCanConnect(previous, current)) {
			ArrayList<MyTile> neighbors = new ArrayList<MyTile>
			(this.getNeighboringTiles(current));
			
			for (int i = 0; i < neighbors.size() ; i++) {
				if(neighbors.get(i).equals(previous))
					neighbors.remove(i);
			}
			
			for (int i = 0; i < neighbors.size() ; i++) {
				if(this.isCanConnect(neighbors.get(i), current)
						&& neighbors.get(i).getTileValue().equals(this.getGoal().getTileValue())) {
					return true;
				}
				else return isPath(neighbors.get(i), current);
			}
		}
		return false;
	}

	//an abstract method for each Game to 
	@Override
	public State<PipeBoardGame> getInitialState() {
		return (new State<PipeBoardGame>(this));
	}
	
	
	
	//getting all possible states that our board can produce
	@Override
	public ArrayList<State<MyTile[][]>> getAllStates(
			State<PipeBoardGame> sourceState){
		
		//setting an ArrayList to hold all the possible states we will create
		ArrayList<State<MyTile[][]>> possibleStatesList = 
				new ArrayList<State<MyTile[][]>>();
		
		//creating a list of possible changes of the "Neighboring" States
		ArrayList<MyTile> neighboringStates = new ArrayList<MyTile>(
				sourceState.getState().getNeighboringTiles(
						sourceState.getState().getCurrent()));
		
		//creating a temporary State for each iteration of a State
		State<PipeBoardGame> newState = new State<PipeBoardGame>();
		
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


	//Rotating according to the PipeGame, need to just after testing
	private void rotateNeighbotingTile(ArrayList<State<MyTile[][]>> possibleStatesList, 
			State<PipeBoardGame> newState,State<PipeBoardGame> sourceState, 
				ArrayList<MyTile> neighboringStates, int timesToRotate, int i) {
		//resetting the given newState to add to the possibleStatesList
		for (int j = 1 ; j < timesToRotate ; j++) {
			newState = new State<PipeBoardGame>(copyBoard(sourceState.getState()), 
					calCost(newState.getState()), sourceState);
			
			newState.getState().getBoardGame()[neighboringStates.get(i).getTileRow()]
												[neighboringStates.get(i).getTileColumn()]
														.rotateTile(j);
			newState.getState().setCurrent(newState.getState().getBoardGame()
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



	protected boolean isCanConnect(MyTile current, MyTile next) {
		String currentValue = new String(current.getTileValue());
		String nextValue = new String(next.getTileValue());
		
		if(currentValue.equals("s") || currentValue.equals("g"))
		{
			//Checking right
			if(next.getTileColumn()>current.getTileColumn() && next.getTileRow()==current.getTileRow())
			{
				if(nextValue.equals("-") || nextValue.equals("7") || nextValue.equals("J"))
					return true;
			}	
			//Checking left
			else if(next.getTileColumn()<current.getTileColumn() && next.getTileRow()==current.getTileRow())
			{
				if(nextValue.equals("-") || nextValue.equals("L") || nextValue.equals("F"))
					return true;
			}
			
			//Checking down
			else if(next.getTileColumn()==current.getTileColumn() && next.getTileRow()>current.getTileRow())
			{
				if(nextValue.equals("|") || nextValue.equals("L") || nextValue.equals("J"))
					return true;
			}
			
			//Checking up
			else if(next.getTileColumn()==current.getTileColumn() && next.getTileRow()<current.getTileRow())
			{
				if(nextValue.equals("|") || nextValue.equals("7") || nextValue.equals("F"))
					return true;
			}
		}
		else if(currentValue.equals("|"))
		{
			//Checking down
			if(next.getTileColumn() == current.getTileColumn() && next.getTileRow()>current.getTileRow())
			{
				if(nextValue.equals("|") || nextValue.equals("L") || nextValue.equals("J") || nextValue.equals("g"))
					return true;
			}
			
			//Checking up
			else if(next.getTileColumn()==current.getTileColumn() && next.getTileRow()<current.getTileRow())
			{
				if(nextValue.equals("|") || nextValue.equals("7") || nextValue.equals("F")|| nextValue.equals("g"))
					return true;
			}
		}
		
		else if(currentValue.equals("-"))
		{
			//Checking right
			if(next.getTileColumn() > current.getTileColumn() && next.getTileRow() == current.getTileRow())
			{
				if(nextValue.equals("-") || nextValue.equals("7") ||nextValue.equals("J") || nextValue.equals("s"))
					return true;
			}	
			//Checking left
			else if(next.getTileColumn() < current.getTileColumn() && next.getTileRow() == current.getTileRow())
			{
				if(nextValue.equals("-") || nextValue.equals("L") || nextValue.equals("F")|| nextValue.equals("s"))
					return true;
			}
		}
		else if(currentValue.equals("F"))
		{
			//Checking right
			if(next.getTileColumn() > current.getTileColumn() && next.getTileRow() == current.getTileRow())
			{
				if(nextValue.equals("-") || nextValue.equals("7") || nextValue.equals("J") || nextValue.equals("g"))
					return true;
			}
			
			//Checking down
			else if(next.getTileColumn() == current.getTileColumn() && next.getTileRow() > current.getTileRow())
			{
				if(nextValue.equals("|") || nextValue.equals("L") || nextValue.equals("J") || nextValue.equals("g"))
					return true;
			}
		}
		
		else if(currentValue.equals("L"))
		{
			//Checking right
			if(next.getTileColumn() > current.getTileColumn() && next.getTileRow() == current.getTileRow())
			{
				if(nextValue.equals("-") || nextValue.equals("7") || nextValue.equals("J") || nextValue.equals("g"))
					return true;
			}
			
			//Checking up
			else if(next.getTileColumn()==current.getTileColumn() && next.getTileRow()<current.getTileRow())
			{
				if(nextValue.equals("|") || nextValue.equals("7") || nextValue.equals("F") || nextValue.equals("g"))
					return true;
			}
		}
		
		else if(currentValue.equals("7"))
		{
			//Checking left
			if(next.getTileColumn() < current.getTileColumn() && next.getTileRow() == current.getTileRow())
			{
				if(nextValue.equals("-") || nextValue.equals("L") || nextValue.equals("F") || nextValue.equals("g"))
					return true;
			}
			//Checking down
			else if(next.getTileColumn()==current.getTileColumn() && next.getTileRow()>current.getTileRow())
			{
				if(nextValue.equals("|") || nextValue.equals("L") || nextValue.equals("J") || nextValue.equals("g"))
					return true;
			}
		}
		
		else if(currentValue.equals("J"))
		{
			//Checking left
			if(next.getTileColumn()<current.getTileColumn() && next.getTileRow()==current.getTileRow())
			{
				if(nextValue.equals("-") || nextValue.equals("L") || nextValue.equals("F")|| nextValue.equals("g"))
					return true;
			}
			//Checking up
			else if(next.getTileColumn() == current.getTileColumn() && next.getTileRow() < current.getTileRow())
			{
				if(nextValue.equals("|") || nextValue.equals("7") || nextValue.equals("F") || nextValue.equals("g"))
					return true;
			}
			
		}
		return false;
	}




	private boolean isExistLoop(State<PipeBoardGame> newState) {
		State<PipeBoardGame> previousState = new State<PipeBoardGame>();
		
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
	private double calCost(PipeBoardGame state) {
		return Double.valueOf(Math.abs(state.getCurrent().getTileColumn() - 
				state.getGoal().getTileColumn() + 
				Math.abs(state.getCurrent().getTileRow() - 
						state.getGoal().getTileRow())));
	}





	private MyTile[][] getBoardGame() {
		return this.getTiles();
	}




	
	protected PipeBoardGame copyBoard(PipeBoardGame anotherBoard) {
		
		MyTile[][] copyTiles = new MyTile[anotherBoard.getNumberOfColumns()][anotherBoard.getNumberOfRows()];
		MyTile source = anotherBoard.findSource(anotherBoard.getTiles());
		MyTile goal = anotherBoard.findGoal(anotherBoard.getTiles());
		MyTile current = anotherBoard.getSource();
		
		return new PipeBoardGame(copyTiles, anotherBoard.getNumberOfColumns(), 
				anotherBoard.getNumberOfRows(), source, goal, current);
	}
	


	protected MyTile findSource(MyTile[][] boardGame) {
		for(int i = 0 ; i < this.getNumberOfRows() ; i++)
			for(int j=0;j< this.getNumberOfColumns() ;j++)
				if(boardGame[j][i].getTileValue().equals('s'))
					return boardGame[i][j];
		return null;
	}

	protected MyTile findGoal(MyTile[][] boardGame) {
		for(int i = 0 ; i < this.getNumberOfRows() ; i++)
			for(int j=0;j< this.getNumberOfColumns() ;j++)
				if(boardGame[j][i].getTileValue().equals('g'))
					return boardGame[i][j];
		return null;
	}





	//getting all the possible differand NeighoringTiles we can get
	//we put them into a collection ArrayList to make sure we won't repeat 
	//the same one
	private Collection<MyTile> getNeighboringTiles(MyTile current) {
		
		//an ArrayList<MyTile> to make sure we won't create duplicate states
		ArrayList<MyTile> neighboringTiles = new ArrayList<MyTile>();
		
		
			//upper Tile
			if (current.getTileRow() != 0)
			neighboringTiles.add(this.getTiles()
					[current.getTileColumn()][current.getTileRow() - 1]);
			//lower Tile
			if (current.getTileRow() != this.getNumberOfRows())
			neighboringTiles.add(this.getTiles()
					[current.getTileColumn()][current.getTileRow() + 1]);
			//left Tile
			if (current.getTileColumn() != 0)
			neighboringTiles.add(this.getTiles()
					[current.getTileColumn() - 1][current.getTileRow()]);
			//right Tile
			if (current.getTileColumn() != this.getNumberOfColumns())
			neighboringTiles.add(this.getTiles()
					[current.getTileColumn() + 1][current.getTileRow()]);
		return neighboringTiles;
	}
	
	
	/*this methods gets the board as a String, much like the C'Tor that gets a Board
	*and converts it to MyTiles[][]
	*we convert the original game to a unique String so we know that even if the 
	*client changes the same game, there's no need to re-solve the slightly 
	*Differently represented board
	*
	*For the PipeBoardGame, we consider the following Tiles as the same kind:
	*UniqueId - Kind of the pipe in the Tile
	* 1 - Curved Pipes: 'L', 'F', '7', 'J'
	* 2 - Straight Pipes: '|', '-'
	* 3 - Source Pipe: 's'
	* 4 - Goal Pipe: 'g'
	* 5 - Empty Tile:' '
	* 6 - End of line: '\n'
	*/
	//@Override
	public String getStringUniqueId(String inputBuffer) {
		//splitting the inputBuffer from the client to an array of Strings
		//Each String in the array hold a single row.
		//The last String in the array holds "done".
		
		//variables
		String[] splitter = inputBuffer.split("\n");
		String uniqueId = new String("");
		
		//forEach string (row on the table)
		for (String string : splitter) {
			//checking if we reached the "done" string
			if (!string.equals("done")) {
				//forEach character (column in the current row)
				for (Character character : string.toCharArray()) {
					//converting the character to its unique ID number, saving it as a character and not as an Integer
					switch (character) {
					//1 - Curved Pipes: 'L', 'F', '7', 'J'
					case 'L': //need to check if there's a chance for HigherCase, LowerCase to avoid CaseSensitive problems
					case 'l':
					case 'F':
					case 'f':
					case '7':
					case 'J':
					case 'j':
						uniqueId = uniqueId.concat("1");
						break;
					//2 - Straight Pipes: '|', '-'
					case '|':
					case '-':
						uniqueId = uniqueId.concat("2");
						break;
					//3 - Source Pipe: 's'
						case 's':
						case 'S':
							uniqueId = uniqueId.concat("3");
						break;
					//4 - Goal Pipe: 'g'
					case 'g':
					case 'G':
						uniqueId = uniqueId.concat("4");
						break;
					//5 - Empty Tile:' '
					case ' ':
						uniqueId = uniqueId.concat("5");
						break;
					//6 - End of line: '\n'
					case '\n':
						uniqueId = uniqueId.concat("6");
							break;
					default:
						break;
					}
				}
			}
			else {
				uniqueId = uniqueId.concat("done\n");
			}
		}
		return uniqueId;
	}

	



}
