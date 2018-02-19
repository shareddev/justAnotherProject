package server;

public interface ITile {
	public void setTileRowColumn(int row, int Column);
	public int getTileRow();
	public int getTileColumn();
	public void setTileValue(String value);
	public String getTileValue();
	
}
