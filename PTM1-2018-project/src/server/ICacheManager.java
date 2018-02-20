package server;

//the ICacheManager manages the stores solutions
//the solutions are stored in hashmap for quick respons and files
//for backing up the solutions before and after loading the server
public interface ICacheManager {
	// checking if there's a solution for the given uniqueId
	public boolean isExistSolution(String uniqueId);

	// HashMap
	// loading from the HashMap<String,String> the solution
	public String getSolution(String uniqueId);

	// adding the given String as a solution to the HashMap
	public void addSolution(String boardToUniqueId, String solution);

	// files
	// loading from file the solutions
	public void loadSolutions();

}
