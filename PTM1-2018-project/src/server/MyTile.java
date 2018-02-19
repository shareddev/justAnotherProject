package server;

public class MyTile{
	
	//variables
	//the Tile's location by a row and column number
	private int row;
	private int column;
	//The value that the Tile needs to hold
	private String value;
	
	//C'Tor
	public MyTile() {
		this((String)"");
	}
	public MyTile(String value) {
		this(0, 0,value);
	}
	
	public MyTile(int column, int row, String value) {
		setTileRowColumn(column, row);
		this.value = value;
	}
	
	//Copy C'Tor
	public MyTile(MyTile tile) {
		this.row = tile.getTileRow();
		this.column = tile.getTileColumn();
		this.value = tile.getTileValue();
	}
	
	//Setter for the Row and column of the Tile
	public void setTileRowColumn(int column, int row) {
		this.column = column;
		this.row = row;
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
		return (new String("{" + this.getTileColumn() + "," + this.getTileRow() + "}"));
	}
	
	//for comparison between this tile and a provided one
	public boolean equals(MyTile tile) {
		if (this.getTileColumn() == tile.getTileColumn() 
				&& this.getTileRow() == tile.getTileRow())
			return true;
		else return false;
	}

}
