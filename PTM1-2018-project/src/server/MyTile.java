package server;

public class MyTile<VALUE> implements ITile<VALUE> {
	
	//variables
	//the Tile's location by a row and colume number
	private int row;
	private int colume;
	//The value that the Tile needs to hold
	private VALUE value;
	
	public MyTile(VALUE value) {
		this(0, 0,value);
	}
	
	public MyTile(int row, int colume, VALUE value) {
		this.row = row;
		this.colume = colume;
		this.value = value;
	}
	
	@Override
	public void setTileRowColume(int row, int colume) {
		this.row = row;
		this.colume = colume;
	}

	@Override
	public int getTileRow() {
		return this.row;
	}

	@Override
	public int getTileColume() {
		return this.colume;
	}

	@Override
	public void setTileValue(VALUE value) {
		this.value = value;
	}

	@Override
	public VALUE getTileValue() {
		return this.value;
	}

}
