package server;

import java.util.Collection;

public interface ISearchable<T> {
	//according to the PPT from class
	public State<T> getInitialState();
	public boolean getGoalState(State<T> goalState);
	public Collection<State<T>> getAllStates(State<T> allStates);
}
