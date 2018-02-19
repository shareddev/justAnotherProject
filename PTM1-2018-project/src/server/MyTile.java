package server;

public abstract class MyTile{
	
	//variables
	//the Tile's location by a row and column number
	private int row;
	private int column;
	//The value that the Tile needs to hold
	private String value;
	
	//C'Tor
	public MyTile(String value) {
		this(0, 0,value);
	}
	
	public MyTile(int row, int column, String value) {
		this.row = row;
		this.column = column;
		this.value = value;
	}
	//Setter for the Row and column of the Tile
	public void setTileRowColumn(int row, int column) {
		this.row = row;
		this.column = column;
	}

	//Getters
	public int getTileRow() {
		return this.row;
	}

	public int getTileColumn() {
		return this.column;
	}

	public void setTileValue(String value) {
		this.value = value;
	}

	public String getTileValue() {
		return this.value;
	}
	//according to the protocol provided by the client
	public String toString() {
		return (new String("{" + this.getTileRow() + "," + this.getTileColumn() + "}"));
	}
	
	//for comparison between this tile and a provided one
	public boolean equals(ITile tile) {
		if (this.getTileColumn() == tile.getTileColumn() 
				&& this.getTileRow() == tile.getTileRow())
			return true;
		else return false;
	}

}
