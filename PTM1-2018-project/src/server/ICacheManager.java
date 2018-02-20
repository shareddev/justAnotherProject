package server;

//the ICacheManager manages the stores solutions
//the solutions are stored in hashmap for quick respons and files
//for backing up the solutions before and after loading the server
public interface ICacheManager {
	public boolean isExistSolution(String key);
	//get and add solution to the HashMap<String,String>
	public String getSolution(String key);
	public void addSolution(String key, Solution solution);
	//load and save solution from and to file
	public void loadSolutions(); 
	public void saveSolution(Solution solution);
}
