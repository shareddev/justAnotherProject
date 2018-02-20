package server;

import java.util.Collection;

public interface ISearchable<T> {
	public State<T> getInitialState();
	public Collection<State<T>> getAllStates(State<T> allStates);
	public boolean isGoalState(State<T> goalState);
}
