package server;

import java.util.ArrayList;
import java.util.Stack;


public class DFS<T> extends CommonSearcherAbstract<T> implements ISearcher<T> {

	private Stack<State<T>> stack = new Stack<State<T>>();
	private ArrayList<State<T>> neighbors = new ArrayList<State<T>>();
	private ArrayList<State<T>> explored = new ArrayList<State<T>>();

	@Override
	public Solution Search(ISearchable<T> pipeGameBoardSearchable) {
		Solution solution = new Solution();
		State<PipeBoardGame> currentState = new State();
		
		currentState = pipeGameBoardSearchable.getInitialState();
		stack.push((State<T>) currentState);
		explored.add((State<T>) currentState);
		
		int counter = 1;
		
		while(!stack.isEmpty())
		{
			currentState = (State<PipeBoardGame>) stack.pop();
			
			counter+=1;
			
			if (pipeGameBoardSearchable.IsGoalState(currentState)) {
				do {
					solution.add(currentState.getState().toString());
					currentState = currentState.getCameFrom();
				} while (currentState != null);

				return solution;
			}
			
			
			neighbors = new ArrayList<State<T>>(Integer.valueOf((pipeGameBoardSearchable.getAllStates(currentState).toString())));

			for(int i=0; i< neighbors.size(); i++)
			{
				if (!openClosedContainsNeighbor(neighbors.get(i), explored)) {
					stack.push((State<T>) neighbors.get(i));
					explored.add((State<T>) neighbors.get(i));
				}
			}
			
		}
		
		return solution;
	}

	
	private boolean openClosedContainsNeighbor(State<T> state, ArrayList<State<T>> explored) 
	{
		boolean flag = false;
		for (State<T> s : explored) {
			if (state.equals(s)) {
				flag = true;
				return flag;
			}
		}

		return flag;
	}



}
