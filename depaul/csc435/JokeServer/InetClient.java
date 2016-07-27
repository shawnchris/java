/*--------------------------------------------------------

1. Name / Date: Gao, Shan / Sept 14, 2014

2. Java version used: jre1.7.0_67

3. Precise command-line compilation examples / instructions:
> javac InetClient.java

4. Precise examples / instructions to run this program:

In separate shell windows:
> java InetServer
> java InetClient
All acceptable commands are displayed on the various consoles.

5. List of files needed for running the program.
 a. Checklist.html
 b. InetServer.java
 c. InetClient.java

6. Notes:


----------------------------------------------------------*/
package depaul.csc435.JokeServer;

import java.io.*;
import java.net.*;

public class InetClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String serverName;
		if (args.length < 1)	// No argument
			serverName = "localhost";	// Assume server is on localhost
		else
			serverName = args[0];	// Here is the remote server address
		System.out.println("Clark Elliott's Inet Client, 1.7.\n");
		System.out.println("Using server: " + serverName + ", Port: 1565");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			String name;
			do {
				System.out.print("Enter a hostname or an IP address, (quit) to end: ");
				System.out.flush ();
				name = in.readLine ();
				if (name.indexOf("quit") < 0)	// Actually it is not a very good idea. Because I can't query sth like www.quit.com...
					getRemoteAddress(name, serverName);
			} while (name.indexOf("quit") < 0);
		System.out.println ("Cancelled by user request.");
		} catch (IOException x) {x.printStackTrace ();}
	}
	
	static String toText (byte ip[]) { /* Make portable for 128 bit format */
		StringBuffer result = new StringBuffer ();
		for (int i = 0; i < ip.length; ++ i) {
			if (i > 0) result.append (".");
			result.append (0xff & ip[i]);
		}
		return result.toString ();
	}
	
	static void getRemoteAddress (String name, String serverName){
		Socket sock;
		BufferedReader fromServer;
		PrintStream toServer;
		String textFromServer;
		
		try{
			sock = new Socket(serverName, 1565);	// Get a connection to server port 1565
			fromServer = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			toServer = new PrintStream(sock.getOutputStream());
			toServer.println(name);	// Send the request to server
			toServer.flush();
			for (int i = 1; i <=3; i++) {	// Print the results get from server
				textFromServer = fromServer.readLine();
				if (textFromServer != null) System.out.println(textFromServer);
			}
			sock.close();
		} catch (IOException x) {
			System.out.println ("Socket error.");
			x.printStackTrace ();
		}
	}

}
