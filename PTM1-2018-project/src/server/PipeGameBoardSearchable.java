package server;

import java.util.List;

public class PipeGameBoardSearchable implements ISearchable<T> {
	
	PipeGameBoard pipeboard; 

	@Override
	public State<T> getInitialState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getGoalState(State<T> goalState) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<State<T>> getAllStates(State<T> allStates) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStringUniqueId(String inputBuffer) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
