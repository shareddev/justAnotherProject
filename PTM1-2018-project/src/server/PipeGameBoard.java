package server;

public class PipeGameBoard<String> extends BoardGame<String> {
	//variables

	public PipeGameBoard() {
		
	}

	@Override
	public State<PipeGameBoard> getFirstState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<State<PipeGameBoard>> getPossibleStates(State<GAME> allStates) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isGoalState(State<PipeGameBoard> goalState) {
		// TODO Auto-generated method stub
		return false;
	}

}
