/*--------------------------------------------------------

1. Name / Date: Gao, Shan / Sept 14, 2014

2. Java version used: jre1.7.0_67

3. Precise command-line compilation examples / instructions:
> javac InetServer.java

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

class Workeri extends Thread {
	Socket sock;
	Workeri (Socket s) {sock = s;}	// Get the socket handler.
	
	public void run(){
		PrintStream out = null;
		BufferedReader in = null;
		
		try {
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintStream(sock.getOutputStream());
			
			if (InetServer.controlSwitch != true){	// Already requested to shutdown
				System.out.println("Listener is now shutting down as per client request.");
				out.println("Server is now shutting down. Goodbye!");
			}
			else try {
				String name;
				name = in.readLine ();
				if (name.indexOf("shutdown") > -1) {	// It is not a very good idea too. Because I can't query sth like www.shutdown.com...
					InetServer.controlSwitch = false;
					System.out.println("Worker has captured a shutdown request.");
					out.println("Shutdown request has been noted by worker.");
					out.println("Please send final shutdown request to listener.");
				}
				else {
					System.out.println("Looking up " + name);
					printRemoteAddress(name, out);
				}
			} catch (IOException x) {
				System.out.println("Server read error");
				x.printStackTrace ();
			}
			
			sock.close();
		} catch (IOException ioe) {System.out.println(ioe);}
	}
	
	static void printRemoteAddress (String name, PrintStream out) {	// Look up DNS to translate between domain name and ip address.
		try {
			out.println("Looking up " + name + "...");
			InetAddress machine = InetAddress.getByName (name);
			out.println("Host name : " + machine.getHostName ());
			out.println("Host IP : " + toText (machine.getAddress ()));
		} catch(UnknownHostException ex) {
			out.println ("Failed in atempt to look up " + name);
		}
	}
	
	//Make portable for 128 bit format
	static String toText (byte ip[]) {
		StringBuffer result = new StringBuffer ();
		for (int i = 0; i < ip.length; ++ i) {
			if (i > 0) result.append (".");
			result.append (0xff & ip[i]);
		}
		return result.toString ();
	}
}

public class InetServer {

	/**
	 * @param args
	 */
	public static boolean controlSwitch = true;
	
	public static void main(String a[]) throws IOException {
		// TODO Auto-generated method stub
		
		int q_len = 6;	//The maximum queue length for incoming connection indications (a request to connect) is set to the backlog parameter. If a connection indication arrives when the queue is full, the connection is refused.
		int port = 1565;
		Socket sock;
		ServerSocket servsock = new ServerSocket(port, q_len);
		System.out.println("Clark Elliott's Inet server starting up, listening at port 1565.\n");
		
		while (controlSwitch) {
			sock = servsock.accept();
			if (controlSwitch) new Workeri(sock).start();
			// Uncomment to see shutdown oddity:
			// try{Thread.sleep(10000);} catch(InterruptedException ex) {}
		}
		
		servsock.close(); //Close the Server Socket gracefully...
	}

}
