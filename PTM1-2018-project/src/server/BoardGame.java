package server;

public abstract class BoardGame<GAME> implements ISearchable<BoardGame<GAME>> {
	private ITile<GAME> tiles[][];
	private ITile<GAME> source;
	private ITile<GAME> goal;
	//an iterator for the future searcher
	private ITile<GAME> current;
	
	private int numberOfRows;
	private int numberOfColumes;

	//C'Tor
	public BoardGame() {
		this(new MyTile<String>(), new MyTile<String>(), new MyTile<String>(), 0, 0);
	}
	public BoardGame(ITile<GAME> tiles[][], ITile<GAME> source, ITile<GAME> goal, 
			ITile<GAME> current, int numberOfRows, int numberOfColumes) {
		
		this.tiles = tiles;
		this.source = source;
		this.goal = goal;
		this.current = current;
		this.numberOfRows = numberOfRows;
		this.numberOfColumes = numberOfColumes;
		
		
	}
	
	@Override
	public State<GAME> getFirstState() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Collection<State<GAME>> getPossibleStates(State<GAME> allStates) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isGoalState(State<GAME> goalState) {
		// TODO Auto-generated method stub
		return false;
	}

}
