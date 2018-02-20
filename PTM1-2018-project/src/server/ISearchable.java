package server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface ISearchable<T> {
	//according to the PPT from class
	public State<T> getInitialState();
	public boolean getGoalState(State<T> goalState);
	public ArrayList<State<MyTile[][]>> getAllStates(State<T> allStates);
	public String getStringUniqueId(String inputBuffer);
}
