package server;

import java.util.PriorityQueue;

public abstract class CommonSearcherAbstract<T> implements ISearcher<T> {
	
	//variables
	//our list that will hold which node to check
	private PriorityQueue<State<T>> priorityList;
	//an integer to count the amount of checked states
	private int checkedStates;
	
	//C'Tor
	public CommonSearcherAbstract() {
		this.priorityList = new PriorityQueue<State<T>>();
		this.checkedStates = 0;
	}
	
	//Getters
	public PriorityQueue<State<T>> getPriorityList(){
		return this.priorityList;
	}
	
	public int getCheckedStates() {
		return this.checkedStates;
	}
	
	//popping a State from the priorityList, also making sure
	//we count a checked State
	private State<T> popPriorityList(){
		this.checkedStates += 1;
		return this.priorityList.poll();
	}
	
	
	
}
