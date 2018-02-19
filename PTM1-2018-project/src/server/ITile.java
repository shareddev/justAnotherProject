package server;

public interface ITile<VALUE> {
	public void setTileRowColume(int row, int colume);
	public int getTileRow();
	public int getTileColume();
	public void setTileValue(VALUE value);
	public VALUE getTileValue();
	
}
