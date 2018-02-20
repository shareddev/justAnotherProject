package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MyClientHandler implements IClientHandler {
	
	//variables
	private ISearchable searchable;
	private ISolver  solver;
	private ICacheManager cacheManager;
	private Solution solution;
	//some people created these two variables INSIDE their handle method, why?
	BufferedReader bufferReader;
	PrintWriter bufferWriter;
	//buffer string to handle messages from and to the client
	private String inputBuffer;
	private String outputBuffer;
	
	
	
	//Default C'Tor	- if not provided, we assume that the client wants to solve a PipeBoardGame
	public MyClientHandler() {
		this(new MyCacheManager(
				System.getProperty("user.dir") + "\\pipeSolutions\\", 
					new Solution<String>()));	
		this.searchable = new PipeGameBoard(); 
	}
	//C'Tor
	public MyClientHandler(ICacheManager cacheManager) {
		this.cacheManager = cacheManager;		
		}
	
	//Methods
	@Override
	//gets a client input and output from IServer
	public void handleClient(InputStream input, 
			OutputStream output) {
		//converts the input and output stream into Strings
		bufferReader = new BufferedReader(new InputStreamReader(input));
		//other people from class said it's better to use PrintWriter, need to test
		bufferWriter = new PrintWriter(new OutputStreamWriter(output));
		
		//need to talk with Nathan, should we surround with exception?
		//converting the bufferReader to a string
		this.inputBuffer = bufferedToString(bufferReader);
		
		//we're using the stringToUnique via the ISearchable because the uniqueness of the string
		//is determined by the values the tiles in the board hold, thus the kind of Game will
		//decide how to recognize each tile.
		String uniqueCheck = this.searchable.stringToUnique(inputBuffer);
		
		//we are sending the board the client provided to check if there's
		//a stored solution, if it exists we return the solution, otherwise
		//we will send the board to the ISolver
		if(this.cacheManager.isExistSolution(uniqueCheck)) {
			writeSolutionToClient(this.getRequiredChanges(this.cacheManager.getSolution(uniqueCheck)), bufferWriter);
		}
		else {
			
		}
			
	}
	//converts the changes that we need to make
	private String getRequiredChanges(String solution2) {
		String changes = new String("");
		
	}
	
	//Converting the solution from a String to a PrintWriter
	//making sure that each and every rotation is sent with a tiny delay (not a single message)
	//so the rotations in the client would happen one after the other
	private void writeSolutionToClient(String solution, PrintWriter bufferWriter) {
		String buffer = new String("");
		for (Character character : solution.toCharArray()) {
			if(character != '\n')
				buffer = buffer.concat(Character.toString(character));
			else {
				buffer = buffer.concat("\n");
				bufferWriter.write(buffer);
				bufferWriter.flush();
				buffer = "";
			}
		}
		bufferWriter.println("done\n");
		bufferWriter.flush();
		}
	//this method converts the buffer we got from the client
	//to a string.
	private String bufferedToString(BufferedReader input) throws IOException{
		String buffer = new String();
		while (!buffer.contains("done")) {
			buffer = buffer.concat(input.readLine());
			if(!buffer.contains("done"))
				buffer = buffer.concat("\n");
		}
		return buffer;
	 }
	
	@Override
	public ISolver getSolver() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//converts a boardGame to a unique String identifier to make sure
	//we're talking about the same game when sending to the ICacheManager
	public String stringToUnique(String string) {
		
	}

}
