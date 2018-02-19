package server;

import java.util.Collection;
//PipeGameBoard is the implementation of the BoardGame
//this Class determines that we are specifically playing a PipeGame
public class PipeGameBoard extends BoardGame {
	//variables

	public PipeGameBoard() {
		this.get
	}
	
	public PipeGameBoard(String boardGame) {
		super(boardGame);
		
	}

	@Override
	public State<BoardGame> getOriginalState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<State<BoardGame>> getPossibleStates(State<BoardGame> allStates) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isGoalState(State<BoardGame> goalState) {
		// TODO Auto-generated method stub
		return false;
	}





}
