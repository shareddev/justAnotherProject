package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class MyCacheManager implements ICacheManager {

	//Variables
	HashMap<String,String> solutions;
	//a string that saves the file path
	String filePath;
	//The file class that will handle our file
	File solutionsFile;

	//C'Tor
	//Because the MyClientHandler sends a file path by default, we don't need to concern with a 
	//default C'Tor for MyCacheManager
	public MyCacheManager(String filePath) {
		//the HashMap that will save the solutions to RAM
		this.solutions = new HashMap<>();
		//The filePath string that will be used to save our solutions
		this.filePath = filePath;
		//The File class that will handle writing and loading solutions
		this.solutionsFile = new File(this.filePath);		

}
	@Override
	public boolean isExistSolution (String key) {
		if (this.solutions.containsKey(key))
			return true;
		else return false;
	}
	
	//
	@SuppressWarnings("resource")
	@Override
	public void loadSolutions() {
		File solutionFilePath = new File(this.filePath);
		File[] filesArray = solutionFilePath.listFiles();
		
		for (int i = 0; i < filesArray.length; i++) {
			try {
				this.solutions.put(filesArray[i].getName().
						substring(0, filesArray[i].getName().length()-4),
						new BufferedReader(new FileReader(filesArray[i])).toString());
				
			} catch (FileNotFoundException e) {
			};
		}
	}
	@Override
	public void saveSolution(Solution solution) {
		
	}
	@Override
	public String getSolution(String key) {
		if (isExistSolution(key))
			FileReader(this.filePath);
					this.solutions.get(key);
		else return null;
	}
	@Override
	public void addSolution(String key, Solution solution) {
		
	}

}
