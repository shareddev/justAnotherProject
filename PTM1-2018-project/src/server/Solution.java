package server;

import java.util.ArrayList;

//this class represents the Solution that the ISearchable algorithms will use
public class Solution extends ArrayList<String> {
	static final long serialVersionUID = 1L;
	public void addSolution(State<MyTile[][]> solution) {
		this.add(solution.toString());
	}
}
