package server;

import java.util.ArrayList;
import java.util.Random;

class HillClimbingSearcher implements ISearcher<String> {
    private long timeToRun;
    private StateGrader<String> grader;


    HillClimbingSearcher(StateGrader<String> grader, long timeToRun) {
        this.grader = grader;
        this.timeToRun = timeToRun;
    }


    @Override
    public Solution search(ISearchable<String> searchable) {
        //Define the current state as an initial state
        State<String> next = searchable.getOriginalState();
        Solution result = new Solution();
        
        long time0 = System.currentTimeMillis();


        //Loop until the goal state is achieved or no more operators can be applied on the current state:
        while (System.currentTimeMillis() - time0 < timeToRun) {
            ArrayList<State<String>> neighbors = searchable.getAllStates(next);

            if (Math.random() < 0.7) { // with a high probability
                // find the best one
                int grade = 0;
                for (State<String> step : neighbors) {
                    int g = grader.grade(step);
                    if (g > grade) {
                        grade = g;
                        next = step;
                        //add this step to the solution
                        //result.add
                        //result.add(step[0]);

                    }
                }
            } else {
                next = neighbors.get(new Random().nextInt(neighbors.size()));
            }
        }

        return result;

    }


	@Override
	public int getNumOfEvaluatedNodes() {
		// TODO Auto-generated method stub
		return 0;
	}



}

