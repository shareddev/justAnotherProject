package server;

public class MyTile implements ITile {
	
	//variables
	//the Tile's location by a row and colume number
	private int row;
	private int colume;
	//The value that the Tile needs to hold
	private String value;
	
	//C'Tor
	public MyTile(String value) {
		this(0, 0,value);
	}
	
	public MyTile(int row, int colume, String value) {
		this.row = row;
		this.colume = colume;
		this.value = value;
	}
	//Setter for the Row and Colume of the Tile
	@Override
	public void setTileRowColume(int row, int colume) {
		this.row = row;
		this.colume = colume;
	}

	//Getters
	@Override
	public int getTileRow() {
		return this.row;
	}

	@Override
	public int getTileColume() {
		return this.colume;
	}

	@Override
	public void setTileValue(String value) {
		this.value = value;
	}

	@Override
	public String getTileValue() {
		return this.value;
	}
	//according to the protocol provided by the client
	public String toString() {
		return (new String("{" + this.getTileRow() + "," + this.getTileColume() + "}"));
	}
	
	//for comparison between this tile and a provided one
	public boolean equals(ITile tile) {
		if (this.getTileColume() == tile.getTileColume() 
				&& this.getTileRow() == tile.getTileRow())
			return true;
		else return false;
	}

}
