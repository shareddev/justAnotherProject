package server;

import java.io.File;
import java.util.HashMap;

public class MyCacheManager<GAME> implements ICacheManager {

	//Variables
	HashMap<String,GAME> solutions;
	//a string that saves the file path
	String filePath;
	//The file class that will handle our file
	File solutionsFile;
	//Solution that the MyCacheManager will return to the IClient
	Solution<GAME> solution;
	
	//C'Tor
	//Because the MyClientHandler sends a file path by default, we don't need to concern with a 
	//default C'Tor for MyCacheManager
	public MyCacheManager(String filePath, Solution<GAME> solution) {
		//the HashMap that will save the solutions to RAM
		this.solutions = new HashMap<>();
		//The filePath string that will be used to save our solutions
		this.filePath = filePath;
		//The File class that will handle writing and loading solutions
		this.solutionsFile = new File(this.filePath);
		this.solution = solution;
		

}
	@Override
	public boolean isExistSolution (String board) {
		if (this.solutions.containsKey(board))
			return true;
		else return false;
	}
	
	@Override
	public String loadSolution() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void saveSolution() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getSolution() {
		// TODO Auto-generated method stub
		return null;
	}
}
