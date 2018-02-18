package server;

import java.io.File;

/*
 * The ICashManager manages our solutions cash.
 * If there are solutions, we save them and load them back up
 * From files.
 */
public interface ICashManager {
	
	//checks in the used data structures if the solution exises
	boolean isExistingSolution(String check);
	String getSolution(String board);
	
	//the saving and loading methods
	void saveASolutionToFile(String board, String solution);
	
	//for saving up our solutions
	void saveAllSolutionsToFile();
	//for loading up our solutions
	void loadAllSolutionsFromFile();
	
	
	//Checking and handling files
	boolean isFileExist(File file);
	void createSolutionsFile();
	

}
