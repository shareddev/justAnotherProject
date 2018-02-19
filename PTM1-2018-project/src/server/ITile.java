package server;

public interface ITile {
	public void setTileRowColume(int row, int colume);
	public int getTileRow();
	public int getTileColume();
	public void setTileValue(String value);
	public String getTileValue();
	
}
