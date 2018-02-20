package server;

import java.util.Collection;
import java.util.List;

public interface ISearchable<T> {
	//according to the PPT from class
	public State<T> getInitialState();
	public boolean getGoalState(State<T> goalState);
	public Collection<State<PipeBoardGame>> getAllStates(State<T> allStates);
	public String getStringUniqueId(String inputBuffer);
}
