package server;

public class State<GAME> implements Comparable<State<GAME>> {
	
	private GAME state; //the current state
	private double cost; //the cost to reach the current state
	private State<GAME> cameFrom; //the previous state to the current one
	

	//C'tor
	public State(GAME state) {
		this(state, 0.0, null);
	}
	public State(GAME state, double cost, State<GAME> cameFrom) {
		this.state = state;
		this.cost = cost;
		this.cameFrom = cameFrom;
	}
	
	@Override
	public int compareTo(State<GAME> compare) {
		if (this.state.equals(compare.getState()) 
				&& this.cameFrom == compare.cameFrom)
			return 1;
		else return 0;
	}
	
	
	public GAME getState() {
		return this.state;
	}
	
	public double getCost() {
		return this.cost;
	}
	public State<GAME> getCameFrom(){
		return this.cameFrom;
	}
	
	@Override
	public String toString() {
		return this.state.toString();
	}
	//isn't this suppose to be an override?
	//isn't equals part of object class?
	//need to ask Nathan if he knows
	//@Override
	public boolean equals(State<GAME> compare) {
		if(this.getState().equals(compare.getState()))
			return true;
		else return false;
	}
	
	
	
	

}
