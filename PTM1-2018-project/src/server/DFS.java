package server;

import java.util.ArrayList;
import java.util.Stack;


public class DFS<T> extends CommonSearcherAbstract<T> implements ISearcher<T> {

	private Stack<State<T>> stack = new Stack<State<T>>();
	private ArrayList<State<T>> neighbors = new ArrayList<State<T>>();
	private ArrayList<State<T>> explored = new ArrayList<State<T>>();

	@Override
	public Solution<T> Search(ISearchable<T> pipeGameBoardSearchable) {
		Solution<T> solution = new Solution<T>();
		State<T> currentState = new State<T>();
		
		currentState = pipeGameBoardSearchable.getInitialState();
		stack.push(currentState);
		explored.add(currentState);
		
		int counter = 1;
		
		while(!stack.isEmpty())
		{
			currentState = stack.pop();
			
			counter++;
			
			if (pipeGameBoardSearchable.IsGoalState(currentState)) {
				do {
					solution.add(currentState.getState());
					currentState = currentState.getCameFrom();
				} while (currentState != null);

				return solution;
			}
			
			
			neighbors = new ArrayList<State<T>>(pipeGameBoardSearchable.getAllPossibleStates(currentState));

			for(int i=0; i< neighbors.size(); i++)
			{
				if (!openClosedContainsNeighbor(neighbors.get(i), explored)) {
					stack.push(neighbors.get(i));
					explored.add(neighbors.get(i));
				}
			}
			
		}
		
		return solution;
	}

	
	private boolean openClosedContainsNeighbor(State<T> checkedState, ArrayList<State<T>> explored) 
	{
		boolean flag = false;

//		Iterator<State<T>> openListItartor = queue.iterator();

//		while (openListItartor.hasNext()) {
//			if (checkedState.equals(openListItartor.next())) {
//				flag = true;
//				return flag;
//			}
//
//		}

		for (State<T> s : explored) {
			if (checkedState.equals(s)) {
				flag = true;
				return flag;
			}
		}

		return flag;
	}
	
	@Override
	public int getNumberOfNodesEvaluated() {
		// TODO Auto-generated method stub
		return 0;
	}

}
