package server;

import java.util.Collection;

public interface ISearchable<GAME> {
	public State<GAME> getOriginalState();
	public Collection<State<GAME>> getPossibleStates(State<GAME> allStates);
	public boolean isGoalState(State<GAME> goalState);
	public String stringToUnique(String string);
}
