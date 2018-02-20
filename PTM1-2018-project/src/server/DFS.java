package server;

import java.util.ArrayList;
import java.util.Stack;


public class DFS<T> extends CommonSearcherAbstract<T> {

	//visited - colored black
	private ArrayList<State<T>> black = new ArrayList<State<T>>();
	private Stack<State<T>> stack = new Stack<State<T>>();
	//left to check
	private ArrayList<State<T>> possibleNeighbors = 
			new ArrayList<State<T>>();

	@Override
	public Solution search(ISearchable<T> searchable) {
		int counter = 0;
		Solution solution = new Solution();
		State<PipeBoardGame> currentState = new State();
		currentState = searchable.getInitialState();
		stack.push((State<T>) currentState);
		black.add((State<T>) currentState);
		
		//counter = 0;
		counter = 1;
		
		while(!stack.isEmpty())
		{
			currentState =  (State<PipeBoardGame>) stack.pop();
			
			counter+=1;
	
			if (searchable.IsGoalState(currentState)) {
				do {
					solution.add(currentState.getState().toString());
					currentState = currentState.getPrevious();
				} while (currentState != null);

				return solution;
			}
			possibleNeighbors = new ArrayList<State<T>>(Integer.valueOf(
					(searchable.getAllStates(currentState).toString())));
			for(int i=0; i< possibleNeighbors.size(); i++)
			{
				if (!isContainsNeighbor(possibleNeighbors.get(i), black)) {
					stack.push((State<T>) possibleNeighbors.get(i));
					black.add((State<T>) possibleNeighbors.get(i));
				}
			}
			
		}
		return solution;
	}
	
	private boolean isContainsNeighbor(State<T> state, 
			ArrayList<State<T>> explored) 
	{
		for (State<T> s : explored) 
			if (state.equals(s)) 
				return true;
		return false;
	}



}
