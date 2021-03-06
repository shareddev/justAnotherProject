package server;

import java.util.ArrayList;

public interface ISearchable<T> {
	// according to the PPT from class
	public State<PipeBoardGame> getInitialState();

	public boolean getGoalState(State<T> goalState);

	public ArrayList<State<PipeBoardGame>> getAllStates(State<PipeBoardGame> next);

	public String getStringUniqueId(String inputBuffer);

	public boolean IsGoalState(State<PipeBoardGame> currentState);
}
