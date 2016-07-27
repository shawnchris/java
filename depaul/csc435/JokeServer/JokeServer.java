/*--------------------------------------------------------

1. Name / Date: Gao Shan / Sept. 20, 2014

2. Java version used: jre1.7.0_67

3. Precise command-line compilation examples / instructions:

> javac JokeServer.java

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
import java.util.*;

class Worker extends Thread { // The worker thread which serves connection from JokeClient
	Socket sock;
	Worker (Socket s) {sock = s;}	// Get the socket handler.
	
	public void run(){
		PrintStream out = null;
		BufferedReader in = null;
		String clientName = "";
		String jokestat = "";
		String proverbstat ="";
		
		try {
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintStream(sock.getOutputStream());
			
			// The request from client is composed by 3 lines: clientName; joke status; proverb status.
			clientName = in.readLine();
			jokestat = in.readLine();
			proverbstat = in.readLine();
			
			if (JokeServer.controlSwitch != true){	// Already requested to shutdown
				System.out.println("Client listener is now shutting down as per AdminClient request.");
				out.println("maintenance-mode");
				out.println("Server is now shutting down. No new connection will be accepted.");
			}
			else {
				if (JokeServer.mode.equalsIgnoreCase("maintenance-mode")) { // If server is in maintenance mode...
					out.println("maintenance-mode");
					out.println("The server is temporarily unavailable -- check-back shortly.");
					System.out.println("Told " + clientName + " that server is in maintenance mode.");
				}
				if (JokeServer.mode.equalsIgnoreCase("joke-mode")) { // If server is in joke mode...
					out.println("joke-mode"); // Respond client the current mode
					
					// If all jokes were sent, start again
					if (jokestat.equals("11111")) jokestat = "00000";
					
					// Trying to get a joke which was not sent
					Random rand = new Random();
					int i;
					do {
						i = rand.nextInt(5);
						//System.out.println("Random number - " + i);
					} while (jokestat.charAt(i)=='1');		
					String message = JokeServer.jokes[i];
					out.println(message.replace("XNAME", clientName)); // Respond client the joke content
					System.out.println("Sent joke " + i + " to " + clientName);
					
					String newstat = "";
					for (int j=0; j<5; j++) {
						if (j != i)
							newstat = newstat + jokestat.charAt(j);
						else
							newstat = newstat + "1";
					}
					jokestat = newstat;
				}
				if (JokeServer.mode.equalsIgnoreCase("proverb-mode")) { // If server is in proverb mode...
					out.println("proverb-mode");
					
					// If all jokes were sent, start again
					if (proverbstat.equals("11111")) proverbstat = "00000";
					
					// Trying to get a proverb which was not sent
					Random rand = new Random();
					int i;
					do {
						i = rand.nextInt(5);
					} while (proverbstat.charAt(i)=='1');		
					String message = JokeServer.proverbs[i];
					out.println(message.replace("XNAME", clientName));
					System.out.println("Sent proverb " + i + " to " + clientName);
					
					String newstat = "";
					for (int j=0; j<5; j++) {
						if (j != i)
							newstat = newstat + proverbstat.charAt(j);
						else
							newstat = newstat + "1";
					}
					proverbstat = newstat;
				}
			}
			
			out.println(jokestat); //Send the client's status back.
			out.println(proverbstat);
			
			sock.close();
		} catch (IOException ioe) {System.out.println(ioe);}
	}
}

class AdminWorker extends Thread { // The worker thread which serves connection from JokeClientAdmin
	Socket sock;
	AdminWorker (Socket s) {sock = s;}	// Get the socket handler.
	
	public void run(){
		PrintStream out = null;
		BufferedReader in = null;
		String cmd = "";
		
		try {
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintStream(sock.getOutputStream());
			
			// The request from AdminClient is composed by only 1 line which has 4 possible values: 
			//"joke-mode", "proverb-mode", "maintenance-mode" or "shutdown"
			cmd = in.readLine();
			
			if (JokeServer.controlSwitch != true){	// Already requested to shutdown
				System.out.println("Admin listener is now shutting down as per AdminClient request.");
				out.println("Server is now shutting down. No new connection will be accepted.");
			}
			else {
				if (cmd.equalsIgnoreCase("joke-mode") || cmd.equalsIgnoreCase("proverb-mode") || cmd.equalsIgnoreCase("maintenance-mode")) {
					JokeServer.mode = cmd;
					System.out.println("Server is switching to " + cmd + ".");
					out.println("Server is switching to " + cmd + ".");
				}
				if (cmd.equalsIgnoreCase("shutdown")) {
					JokeServer.controlSwitch = false;
					System.out.println("Server is shutting down.");
					out.println("Server is shutting down.");
				}
			}
			
			sock.close();
		} catch (IOException ioe) {System.out.println(ioe);}
	}
}

class AdminLooper implements Runnable {
	
	//public static boolean adminControlSwitch = true;
	
	public void run(){ // Running the Admin listen loop
	
		int q_len = 6; /* Number of requests for OpSys to queue */
		int port = 2565;  // We are listening at a different port for Admin clients
		Socket sock;
		try{
			ServerSocket servsock = new ServerSocket(port, q_len);
			System.out.println("AdminLooper starting up, listening at port " + port + " for JokeClientAdmin.");
			
			while (JokeServer.controlSwitch) {
				// wait for the next ADMIN client connection:
				sock = servsock.accept();
				new AdminWorker (sock).start(); 
			}
		}catch (IOException ioe) {System.out.println(ioe);}
	}
}

public class JokeServer {

	public static boolean controlSwitch = true; // This is the flag to control server shutdown. Only message from Admin Client can change this flag.
	public static String mode = "joke-mode"; // This is the flag to control server mode. Only message from Admin Client can change this flag.
	public static String[] jokes = {
			"Joke A - XNAME asked God for a bike, but XNAME know God doesn't work that way. So XNAME stole a bike and asked God for forgiveness.",
			"Joke B - XNAME want to die peacefully in the sleep, like XNAME's grandfather... Not screaming and yelling like the passengers in his car.",
			"Joke C - XNAME, do not argue with an idiot. He will drag you down to his level and beat you with experience.",
			"Joke D - If you think nobody cares you, XNAME, try missing a couple of payments.",
			"Joke E - Never, XNAME, under any cirumstances, take a sleeping pill and a laxative on the same night.",	
	};
	public static String[] proverbs = {
			"Proverb A - By other's faults, wise men correct their own. By XNAME",
			"Proverb B - Conquer of fear of death and you are put into possession of your life. By XNAME",
			"Proverb C - Fame like a river is narrowest at its source and broadest afar off. By XNAME",
			"Proverb D - Govern your thoughts when alone, and your tongue when in company. By XNAME",
			"Proverb E - Idle folks have most labour. By XNAME",
	};
	
	public static void main(String[] args) throws IOException {
		
		AdminLooper AL = new AdminLooper(); // create a thread for AdminClient connection
	    Thread t = new Thread(AL);
	    t.start();  // ...and start it, waiting for administration input
		
		int q_len = 6;	//The maximum queue length for incoming connection indications (a request to connect) is set to the backlog parameter. If a connection indication arrives when the queue is full, the connection is refused.
		int port = 1565;
		Socket sock;
		ServerSocket servsock = new ServerSocket(port, q_len);
		System.out.println("Gao Shan's JokeServer starting up, listening at port " + port +" for JokeClient.");
		
		while (controlSwitch) {
			sock = servsock.accept();
			new Worker(sock).start();
		}

	}

}
