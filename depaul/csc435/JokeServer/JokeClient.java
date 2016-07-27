
/*--------------------------------------------------------

1. Name / Date: Gao Shan / Sept. 20, 2014

2. Java version used: jre1.7.0_67

3. Precise command-line compilation examples / instructions:

> javac JokeClient.java

4. Precise examples / instructions to run this program:

In separate shell windows:

> java JokeServer
> java JokeClient
> java JokeClientAdmin

All acceptable commands are displayed on the various consoles.

This runs across machines, in which case you have to pass the IP address of
the server to the clients. For example, if the server is running at
140.192.1.22 then you would type:

> java JokeClient 140.192.1.22
> java JokeClientAdmin 140.192.1.22

5. List of files needed for running the program.

 a. checklist-joke.html
 b. JokeServer.java
 c. JokeClient.java
 d. JokeClientAdmin.java

6. Notes:

----------------------------------------------------------*/
package depaul.csc435.JokeServer;

import java.io.*;
import java.net.*;

public class JokeClient {
	
	static String jokestat = "00000"; // A string to save status of Joke.
	static String proverbstat = "00000"; // A string to save status of Proverb.
	static String clientName = ""; // The name of the JokeClient

	public static void main(String[] args) {
		String serverName;
		if (args.length < 1)	// No argument
			serverName = "localhost";	// Assume server is on localhost
		else
			serverName = args[0];	// Here is the remote server address
		System.out.println("Gao Shan's JokeClient.\n");
		System.out.println("Using server: " + serverName + ", Port: 1565");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			do {
				System.out.print("What's your name? ");
				System.out.flush ();
				clientName = in.readLine ();
			} while (clientName.length()<=0); // An empty name is not allowed!
			
			String cmd;
			do {
				System.out.print("Input anything to get a joke or proverb, (quit) to end: ");
				System.out.flush ();
				cmd = in.readLine ();
				if (cmd.indexOf("quit") < 0)
					getMessage(serverName);
			} while (cmd.indexOf("quit") < 0);
			
		System.out.println ("Cancelled by user request.");
		} catch (IOException x) {x.printStackTrace ();}

	}
	
	static void getMessage (String serverName){
		Socket sock;
		BufferedReader fromServer;
		PrintStream toServer;
		String mode="";
		String message="";
		
		try{
			sock = new Socket(serverName, 1565);	// Get a connection to server port 1565
			fromServer = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			toServer = new PrintStream(sock.getOutputStream());
			
			// Send a request to server which composed by 3 lines: clientName; joke status; proverb status.
			toServer.println(clientName);
			toServer.println(jokestat);
			toServer.println(proverbstat);
			toServer.flush();
			
			// Response from server composed by 4 lines: mode; joke/proverb; joke status; proverb status.
			mode = fromServer.readLine().toLowerCase();
			if (mode != null) {
				message = fromServer.readLine();
				jokestat = fromServer.readLine();
				proverbstat = fromServer.readLine();
			}
			
			//Debug message
			//System.out.println("Mode: " + mode + "\nJoke Status: " + jokestat + "\nProverb Status: " + proverbstat);
			
			System.out.println("Message from server: " + message);
				
			sock.close();
		} catch (IOException x) {
			System.out.println ("Socket error.");
			x.printStackTrace ();
		}
	}

}
