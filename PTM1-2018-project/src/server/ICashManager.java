package server;

public interface ICashManager {
	public boolean isSolutionExist();
	public Solution loadSolution(); 
	public void saveSolution(); 
}
