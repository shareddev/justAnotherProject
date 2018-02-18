package server;

public interface ISearchable<T> {
	public State<GAME> getFirstState();
	public Collection<State<GAME>> getPossibleStates(State<GAME> allStates);
	public boolean isGoalState(State<GAME> goalState);
}
