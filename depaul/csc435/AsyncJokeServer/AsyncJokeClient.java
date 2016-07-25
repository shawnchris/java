/*--------------------------------------------------------

1. Name / Date: Gao Shan / Nov. 1, 2014

2. Java version used: jre1.7.0_67

3. Precise command-line compilation examples / instructions:

> javac AsyncJokeClient.java

4. Precise examples / instructions to run this program:

In separate shell windows:

> java AsyncJokeServer
> java AsyncJokeClient
> java AsyncJokeClientAdmin

All acceptable commands are displayed on the various consoles.

This runs across machines, in which case you have to pass the IP address of
the server to the clients. For example, if the server is running at
140.192.1.22 then you would type:

> java AsyncJokeClient 140.192.1.22
> java AsyncJokeClientAdmin 140.192.1.22

5. List of files needed for running the program.

 a. AsyncJokeChecklist.html
 b. AsyncJokeServer.java
 c. AsyncJokeClient.java
 d. AsyncJokeClientAdmin.java

6. Notes:

----------------------------------------------------------*/
package depaul.csc435.AsyncJokeServer;
import java.io.*;
import java.net.*;
import java.util.Random;

class ClientWorker extends Thread {
	int serverPort;
	
	ClientWorker (int port) {
		serverPort = port;
	}
	
	public void run () {
		try {
			DatagramSocket serverSocket = new DatagramSocket(serverPort);
			//System.out.println("Back-end UPD worker started, listening at port: " + serverPort);
			byte[] receiveData = new byte[1024];
			while(true) {
				// Start to listen the message from server
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
				String sentence = new String( receivePacket.getData());
				//System.out.println("RECEIVED: " + sentence);
				String[] response = sentence.split("\n");
				// Response from server composed by 4 lines: mode; joke/proverb; joke status; proverb status.
				AsyncJokeClient.message = response[1];
				AsyncJokeClient.jokestat = response[2];
				AsyncJokeClient.proverbstat = response[3];				
				AsyncJokeClient.got_response = true;
            }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class AsyncJokeClient {
	
	static String jokestat = "00000"; // A string to save status of Joke.
	static String proverbstat = "00000"; // A string to save status of Proverb.
	static String message = ""; // The message returned from server.
	static String clientName = ""; // The name of the JokeClient.
	static boolean got_response = false; // Got message from server?

	public static void main(String[] args) {
		String serverName;
		int UDPport = 6789 + ((new Random()).nextInt(1000)); // Use a random UDP port from 6789 to 7789 to get async message from server.
		if (args.length < 1)	// No argument
			serverName = "localhost";	// Assume server is on localhost
		else
			serverName = args[0];	// Here is the remote server address
		System.out.println("Gao Shan's AsyncJokeClient.");
		System.out.println("Using server: " + serverName + ", Port: 1565 and listening at UDP Port: " + UDPport);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			do {
				System.out.print("What's your name? ");
				System.out.flush ();
				clientName = in.readLine ();
			} while (clientName.length()<=0); // An empty name is not allowed!
			
			// Start the back-end worker to listen at a UPD port for server message.
			ClientWorker cw = new ClientWorker(UDPport);
			cw.start();
			
			String cmd;
			do {
				System.out.print("Input anything to get a joke or proverb, (quit) to end: ");
				System.out.flush ();
				cmd = in.readLine ();
				if (cmd.indexOf("quit") < 0) {
					sendMessage(serverName, UDPport+""); // Send a request to server.
					while (!got_response) {
						// Play sum game till get response from server.
						System.out.print("Enter numbers to sum: ");
						System.out.println("Your sum is: " + get_sum(in.readLine()));
					}
					System.out.println("Message from server: " + message);
				}
				got_response = false;
			} while (cmd.indexOf("quit") < 0);
			
		System.out.println ("Cancelled by user request.");
		} catch (IOException x) {x.printStackTrace ();}

	}
	
	static int get_sum (String input) {
		String [] part = input.split(" ");
		int sum = 0;
		int tmp = 0;
		for (int i=0; i<part.length; i++) {
			try {
				tmp = Integer.valueOf(part[i]);
				sum = sum + tmp;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sum;
	}
	
	static void sendMessage (String serverName, String udp_port) {
		Socket sock;
		PrintStream toServer;
		
		try{
			sock = new Socket(serverName, 1565);	// Get a connection to server port 1565
			toServer = new PrintStream(sock.getOutputStream());
			
			// Send a request to server which composed by 4 lines: clientName; joke status; proverb status; UDP port number
			toServer.println(clientName);
			toServer.println(jokestat);
			toServer.println(proverbstat);
			toServer.println(udp_port);
			toServer.flush();
			sock.close();
		} catch (IOException x) {
			System.out.println ("Socket error.");
			x.printStackTrace ();
		}
	}

}
