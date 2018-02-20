package server;

import java.util.Collection;
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
	public State<BoardGame> getInitialState() {
		return (new State<BoardGane>(this));
	}








	




}
