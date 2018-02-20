package server;

//Our HillClimbing, need to check more games

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class HillClimbingSearcher implements ISearcher<MyTile[][]> {


    private long timeToRun;
    private StateGrader<MyTile[][]> grader;


    HillClimbingSearcher(StateGrader<MyTile[][]> grader, long timeToRun) {
        this.grader = grader;
        this.timeToRun = timeToRun;
    }


    @Override
    public Solution search(ISearchable<MyTile[][]> searchable) {
        //Define the current state as an initial state
        State<PipeBoardGame> next = searchable.getInitialState();
        Solution result = new Solution();
        
        long time0 = System.currentTimeMillis();


        //Loop until the goal state is achieved or no more operators can be applied on the current state:
        while (System.currentTimeMillis() - time0 < timeToRun) {
            ArrayList<State<PipeBoardGame>> neighbors = searchable.getAllStates(next);

            if (Math.random() < 0.7) { // with a high probability
                // find the best one
                int grade = 0;
                for (State<PipeBoardGame> step : neighbors) {
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
}

