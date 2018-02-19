package server;

import java.util.Collection;

public interface ISearchable<T> {
	public State<T> getOriginalState();
	public Collection<State<T>> getPossibleStates(State<T> allStates);
	public boolean isGoalState(State<T> goalState);
}
