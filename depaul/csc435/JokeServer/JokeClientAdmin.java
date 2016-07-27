/*--------------------------------------------------------

1. Name / Date: Gao Shan / Sept. 20, 2014

2. Java version used: jre1.7.0_67

3. Precise command-line compilation examples / instructions:

> javac JokeClientAdmin.java

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

public class JokeClientAdmin {

	public static void main(String[] args) {
		String serverName;
		if (args.length < 1)	// No argument
			serverName = "localhost";	// Assume server is on localhost
		else
			serverName = args[0];	// Here is the remote server address
		System.out.println("Gao Shan's JokeClientAdmin.\n");
		System.out.println("Using server: " + serverName + ", Port: 2565");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			String cmd ="";
			do {
				System.out.print("Input a command, (quit) to end: ");
				System.out.flush();
				cmd = in.readLine().toLowerCase();
				if (cmd.equalsIgnoreCase("joke-mode") || cmd.equalsIgnoreCase("proverb-mode") || cmd.equalsIgnoreCase("maintenance-mode") || cmd.equalsIgnoreCase("shutdown") || cmd.equalsIgnoreCase("quit")) {
					if (!cmd.equalsIgnoreCase("quit")) getMessage(serverName, cmd);
				}
				else {
					System.out.println("Your input is invalid. Please input \"joke-mode\", \"proverb-mode\", \"maintenance-mode\" or \"shutdown\".");
				}
				
			} while (!cmd.equalsIgnoreCase("quit"));
			
			System.out.println ("Cancelled by user request.");
		} catch (IOException x) {x.printStackTrace ();}

	}
	
	static void getMessage (String serverName, String cmd){
		Socket sock;
		BufferedReader fromServer;
		PrintStream toServer;
		String message="";
		
		try{
			sock = new Socket(serverName, 2565);	// Get a connection to server port 2565
			fromServer = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			toServer = new PrintStream(sock.getOutputStream());
			
			// The request from AdminClient is composed by only 1 line which has 4 possible values: 
			//"joke-mode", "proverb-mode", "maintenance-mode" or "shutdown"
			toServer.println(cmd);
			
			// Response from server composed by 1 line.
			message = fromServer.readLine().toLowerCase();
			
			System.out.println("Message from server: " + message);
				
			sock.close();
		} catch (IOException x) {
			System.out.println ("Socket error.");
			x.printStackTrace ();
		}
	}

}

