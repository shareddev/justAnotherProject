package server;

import java.io.Serializable;
//a State represents an entire board
//the board will be represented by a String
public class State<T> implements Comparable<State<T>>, Serializable {
	
	//setting the Serializable version
	private static final long serialVersionUID = 1L;
	//the current state
	private T state; 
	//the cost to reach the current state
	private double cost; 
	//the previous state to the current one
	private State<T> cameFrom; 
	

	//C'tor
	public State() {
		this(null);
	}
	public State(T state) {
		this(state, 0.0, null);
	}
	public State(T state, double cost, State<T> cameFrom) {
		this.state = state;
		this.cost = cost;
		this.cameFrom = cameFrom;
	}
	
	@Override
	public int compareTo(State<T> compare) {
		if (this.state.equals(compare.getState()) 
				&& this.cameFrom == compare.cameFrom)
			return 1;
		else return 0;
	}
	
	
	public T getState() {
		return this.state;
	}
	
	public double getCost() {
		return this.cost;
	}
	public State<T> getCameFrom(){
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
	public boolean equals(State<T> compare) {
		if(this.getState().equals(compare.getState()))
			return true;
		else return false;
	}
	
	
	
	

}
