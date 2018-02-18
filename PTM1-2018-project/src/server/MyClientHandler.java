package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MyClientHandler implements IClientHandler {
	
	//variables
	private ISolver solver;
	private ICacheManager cacheManager;
	//some people created these two variables INSIDE their handle method, why?
	BufferedReader bufferReader;
	BufferedWriter bufferWriter;
	//buffer string to handle messages from and to the client
	private String inputBuffer;
	private String outputBuffer;
	
	
	//Default C'Tor	
	public MyClientHandler() {
		this(new MyCacheManager(
				System.getProperty("user.dir") + "\\pipeSolutions\\"));
	}
	//Generic C'Tor
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
		bufferWriter = new BufferedWriter(new OutputStreamWriter(output));
		//converting the bufferReader to a string
		this.inputBuffer = bufferedToString(bufferReader);
		
		//we are sending the board the client provided to check if there's
		//a stored solution, if it exists we return the solution, otherwise
		//we will send the board to the ISolver
		if (this.cacheManager.isExistSolution(this.inputBuffer)) {
			return this.cacheManager.getSolution(this.inputBuffer);
		}
		else {
			
		}
	}

	//this method converts the buffer we got from the client
	//to a string.
	private String bufferedToString(BufferedReader input) throws IOException{
		String buffer = new String();
		while (!buffer.contains("done")) {
			buffer = buffer.concat(input.readLine()).concat("\n");
		}
		return buffer;
	 }

}
