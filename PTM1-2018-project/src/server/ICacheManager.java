package server;

//the ICacheManager manages the stores solutions
//the solutions are stored in hashmap for quick respons and files
//for backing up the solutions before and after loading the server
public interface ICacheManager {
	public boolean isExistSolution(String board);
	public String getSolution();
	public String loadSolution(); 
	public void saveSolution(); 
}
