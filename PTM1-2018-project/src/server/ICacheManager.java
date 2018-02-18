package server;

public interface ICacheManager {
	public boolean isExistSolution(String board);
	public Solution loadSolution(); 
	public void saveSolution(); 
}
