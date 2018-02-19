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
	public void handleClient(InputStream input, OutputStream output) throws IOException {
		//converts the input and output stream into Strings
		bufferReader = new BufferedReader(new InputStreamReader(input));
		//other people from class said it's better to use PrintWriter, need to test
		bufferWriter = new PrintWriter(new OutputStreamWriter(output));
		
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
			writeToClient(this.cacheManager.getSolution(uniqueCheck), bufferWriter);
		}
			
	}

	private void writeToClient(String solution, BufferedWriter bufferWriter) {
		bufferWriter.wr
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

}
