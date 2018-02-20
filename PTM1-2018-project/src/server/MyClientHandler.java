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
				System.getProperty("user.dir") + "\\pipeSolutions\\"));	
		this.searchable = new PipeBoardGame(); 
	}
	//C'Tor
	public MyClientHandler(ICacheManager cacheManager) {
		this.cacheManager = cacheManager;		
		}
	
	//Methods
	@Override
	//gets a client input and output from IServer
	public void handleClient(InputStream input, OutputStream output) {
		//converts the input and output stream into Strings
		bufferReader = new BufferedReader(new InputStreamReader(input));
		//other people from class said it's better to use PrintWriter, need to test
		bufferWriter = new PrintWriter(new OutputStreamWriter(output));
		
		//converting the bufferReader to a string
		this.inputBuffer = bufferedToString(bufferReader);
			
		//we are sending the board the client provided to check if there's
		//a stored solution, if it exists we return the solution, otherwise
		//we will send the board to the ISolver
		if(this.cacheManager.isExistSolution(this.inputBuffer)) {
			writeSolutionToClient(timeToRotate(this.cacheManager.getSolution(boardToUniqueId(this.inputBuffer))), bufferWriter);
		}
		else {
			this.solution = this.getSolver().solve(searchable);
			this.cacheManager.addSolution(boardToUniqueId(this.inputBuffer), solution.toString());
			writeSolutionToClient();
		}
			
	}

	private String timeToRotate(String solution) {
		String[] splitter1 = solution.split("\n");
		String[] splitter2 = this.inputBuffer.split("\n");
		String rotations = new String("");
		for (int i = 0; i < splitter1.length ; i++) {
			for (int j = 0; j < splitter2[0].length(); j++) {
				if (splitter1[j].charAt(i) != splitter2[j].charAt(i)) {
					if(splitter1[j].charAt(i) == '|' || splitter1[j].charAt(i) == '-') {
						rotations = rotations.concat(j + "," + i + "," + "1" + "\n");
					}
					else {
						rotations = rotations.concat(j + "," + i + "," + curvedRotations(splitter1[j]
								.charAt(i), splitter2[j].charAt(i)) + "\n");
					}
				}
			}
		}
		rotations = rotations.concat("done\n");
		return rotations;
	}
	private int curvedRotations(char correct, char wrong) {
		// j = 1, l = 2, f = 3, 7 = d
		int count = 0;
		while (correct != wrong) {
			count++;
			switch (wrong) {
			case 'j':
			case 'J':
				wrong = 'l';
				break;

			case 'l':
			case 'L':
				wrong = 'f';
				break;
				
			case 'f':
			case 'F':
				wrong = '7';
				break;
				
			case '7':
				wrong = 'j';
				break;
				
			default:
				return count;
			}
		}
		return count;
	}
	/*
	* 1 - Curved Pipes: 'L', 'F', '7', 'J'
	* 2 - Straight Pipes: '|', '-'
	* 3 - Source Pipe: 's'
	* 4 - Goal Pipe: 'g'
	* 5 - Empty Tile:' '
	* 6 - End of line: '\n'
	 */
	private String boardToUniqueId(String uniqueId) {
		String buffer = new String("");
		for (Character character : uniqueId.toCharArray()) {
			switch (character) {
			case 'l':
			case 'L':
			case 'F':
			case 'f':
			case '7':
			case 'j':
			case 'J':
			buffer = buffer.concat("1");
				break;
			case '|':
			case '-':
			buffer = buffer.concat("2");
				break;
			case 's':
			case 'S':
			buffer = buffer.concat("3");
				break;
			case 'g':
			case 'G':
			buffer = buffer.concat("4");
				break;
			case ' ':
			buffer = buffer.concat("5");
				break;
			case '\n':
			buffer = buffer.concat("6");
				break;
			default:
			buffer = buffer.concat("done\n");
				break;
			}
		}
		
		return uniqueId;
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
		return this.solver;
	}
	
	

}
