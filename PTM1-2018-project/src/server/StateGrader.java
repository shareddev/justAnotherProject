package server;

interface StateGrader<T>{
	// give a grade to a certain state - how close it is to the solution	
	int grade(State<PipeBoardGame> step); 
		
}