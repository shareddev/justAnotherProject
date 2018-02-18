package server;

import java.io.File;
import java.util.HashMap;

public class MyCashManager implements ICashManager {

	//Variables
	HashMap<String,String> solutions;
	//a string that saves the file path
	String filePath;
	//The file class that will handle our file
	File solutionsFile;
	//C'Tor
	//Default C'Tor, just initialize the solutions map
	public MyCashManager(String filePath) {
		//the HashMap that will save the solutions to RAM
		this.solutions = new HashMap<>();
		//The filePath string that will be used to save our solutions
		this.filePath = filePath;
		//The File class that will handle writing and loading solutions
		this.solutionsFile = new File(this.filePath);
		
		//checking if there's a file that contains a list of soltuions
		if (isFileExist(this.solutionsFile)) {
			loadAllSolutionsFromFile();
		}
		else
		{
			createSolutionsFile();
		}
		
	}
	
	@Override
	public boolean isExistingSolution(String check) {
		return this.solutions.containsKey(check);
	}

	//this method assumes that the solution exists
	//need to check before using the method
	@Override
	public String getSolution(String board) {
		return this.solutions.get(board);
	}

	@Override
	public void saveASolutionToFile(String board, String solution) {
		
		
	}

	@Override
	public void saveAllSolutionsToFile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadAllSolutionsFromFile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public 	boolean isFileExist(File file) {
		return file.exists();
	}

	@Override
	public void createSolutionsFile() {
		// TODO Auto-generated method stub
		
	}

}
