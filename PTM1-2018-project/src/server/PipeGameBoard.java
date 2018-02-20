package server;


//PipeGameBoard is the implementation of the BoardGame
//this Class determines that we are specifically playing a PipeGame
public class PipeGameBoard extends BoardGame {

	public PipeGameBoard(String board) {
		super(board);
	}
	//variables


	public PipeGameBoard(MyTile[][] copyTiles, int numberOfColumns, 
			int numberOfRows, MyTile source, MyTile goal,
			MyTile current) {
		super(copyTiles, numberOfColumns, numberOfRows, source, source, goal);

	}

	public PipeGameBoard() {
		this("s|g\n");
	}


	@Override
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

	@Override
	protected BoardGame copyBoard(BoardGame anotherBoard) 	{
		
		MyTile[][] copyTiles = new MyTile[anotherBoard.getNumberOfColumns()][anotherBoard.getNumberOfRows()];
		MyTile source = anotherBoard.findSource(anotherBoard.getTiles());
		MyTile goal = anotherBoard.findGoal(anotherBoard.getTiles());
		MyTile current = anotherBoard.getSource();
		
		return new PipeGameBoard(copyTiles, anotherBoard.getNumberOfColumns(), 
				anotherBoard.getNumberOfRows(), source, goal, current);
	}


	@Override
	protected MyTile findSource(MyTile[][] boardGame) {
		for(int i = 0 ; i < this.getNumberOfRows() ; i++)
			for(int j=0;j< this.getNumberOfColumns() ;j++)
				if(boardGame[j][i].getTileValue().equals('s'))
					return boardGame[i][j];
		return null;
	}


	@Override
	protected MyTile findGoal(MyTile[][] boardGame) {
		for(int i = 0 ; i < this.getNumberOfRows() ; i++)
			for(int j=0;j< this.getNumberOfColumns() ;j++)
				if(boardGame[j][i].getTileValue().equals('g'))
					return boardGame[i][j];
		return null;
	}


	@Override
	public boolean getGoalState(State<BoardGame> goalState) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public State<PipeGameBoard> getInitialState() {
		return (new State<PipeGameBoard>(this));
	}


	/*this methods gets the board as a String, much like the C'Tor that gets a Board
	*and converts it to MyTiles[][]
	*we convert the original game to a unique String so we know that even if the 
	*client changes the same game, there's no need to re-solve the slightly 
	*Differently represented board
	*
	*For the PipeGameBoard, we consider the following Tiles as the same kind:
	*UniqueId - Kind of the pipe in the Tile
	* 1 - Curved Pipes: 'L', 'F', '7', 'J'
	* 2 - Straight Pipes: '|', '-'
	* 3 - Source Pipe: 's'
	* 4 - Goal Pipe: 'g'
	* 5 - Empty Tile:' '
	* 6 - End of line: '\n'
	*/
	@Override
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
