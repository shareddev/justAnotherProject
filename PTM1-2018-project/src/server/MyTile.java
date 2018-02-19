package server;

public class MyTile{
	
	//variables
	//the Tile's location by a row and column number
	private int column;
	private int row;
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
		this(tile.getTileColumn(),tile.getTileRow(), 
				tile.getTileValue());
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
		//return (new String("{" + this.getTileColumn() + "," + this.getTileRow() + "}"));
		return (new String(this.getTileColumn() + "," + this.getTileRow()));
	}
	
	//for comparison between this tile and a provided one
	public boolean equals(MyTile tile) {
		if (this.getTileColumn() == tile.getTileColumn() 
				&& this.getTileRow() == tile.getTileRow())
			return true;
		else return false;
	}
	//after testing on PipeGameBoard, need to make this generic, should be (doAction) or something like that
	//according to the injected or Overrided to a specific game
	public void rotateTile(int rotate) {
		for (int i = 0; i < rotate ; i++) {
			this.rotateThisTile();
		}
	}
	//this is specificly for PipeGameBoard
	//this rotates THIS tile's value
	public void rotateThisTile() {	
		this.setTileValue(rotateByValue(this.getTileValue()));
	}
	//this rotates a tile's value by the PipeGameBoard's demands
	public String rotateByValue(String value) {
		switch (value) {
		//do nothing
		case " ":
			break;
		case "-":
			return "|";
		case "|":
			return "-";
		case "L":
			return "F";
		case "F":
			return "7";
		case "7":
			return "J";
		case "J":
			return "L";
			}
		return null;
		}
	//rotates THIS tile's value by X times
	public void rotateByTimes(int times) {
		for (int i = 0; i < times ; i++) 
			this.rotateThisTile();
		}
	
	
	
	
	
	
	
	
	
	}
