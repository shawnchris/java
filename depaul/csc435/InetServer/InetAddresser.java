/*--------------------------------------------------------

1. Name / Date: Gao Shan / Sept13, 2014

2. Java version used: jre1.7.0_67

3. Precise command-line compilation examples / instructions:
> javac InetAddresser.java

4. Precise examples / instructions to run this program:

5. List of files needed for running the program.
 a. checklist.html
 b. InetAddresser.java

6. Notes:

----------------------------------------------------------*/
package depaul.csc435.InetServer;

import java.io.*;
import java.net.*;

public class InetAddresser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		printLocalAddress();
		
		BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
		
		try{
			String name;
			do {
				System.out.flush();
				name = in.readLine();
				if (name.indexOf("quit")<0)
					printRemoteAddress(name);
				} while (name.indexOf("quit")<0);
			System.out.println("exit");
			} catch (IOException x) {x.printStackTrace();}
		}
	
	static void printLocalAddress () {
		try{
			System.out.print("Clark Elliott's INET addresser program, 1.7\n");
			InetAddress me = InetAddress.getLocalHost ();
			System.out.println("My local name is: " + me.getHostName ());
			System.out.println("My local IP address is: " + toText(me.getAddress ()));
			} catch (UnknownHostException x) {
				System.out.println ("I appear to be unknown to myself. Firewall?:");
				x.printStackTrace ();
				}
		}
	
	static void printRemoteAddress (String name) {
		try {
			System.out.println ("Looking up " + name + "...");
			InetAddress machine = InetAddress.getByName (name);
			System.out.println ("Host name : " + machine.getHostName ());
			System.out.println ("Host IP : " + toText (machine.getAddress ()));
		} catch (UnknownHostException ex) {
			System.out.println ("Failed to lookup " + name);
		}
	}
	
	static String toText (byte ip[]) {
		StringBuffer result = new StringBuffer ();
		for (int i = 0; i < ip.length; ++ i) {
			if (i > 0) result.append (".");
			result.append (0xff & ip[i]);
		}
		return result.toString ();
	}
}
