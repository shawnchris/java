/*--------------------------------------------------------

1. Name / Date: Gao Shan / Oct. 20, 2014

2. Java version used: jre1.7.0_67

3. Precise command-line compilation examples / instructions:
> javac -cp .;xstream-1.2.1.jar;xpp3_min-1.1.3.4.O.jar BCHandlerB.java

4. Precise examples / instructions to run this program:
> java -cp .;xstream-1.2.1.jar;xpp3_min-1.1.3.4.O.jar BCHandlerB.java File_with_full_path

5. List of files needed for running the program.
 a. csc435\myDataArray.java
 b. MyWebServerB.java
 c. BCHandlerB.java
 d. shim.bat
 e. mimer-data.xyz
 f. mimer-call.html
 g. mimer-discussion.html
 h. checklist-mimer.html
 i. xstream-1.2.1.jar
 j. xpp3_min-1.1.3.4.O.jar

6. Notes:
This is the bragging right version. Refer to mimer-discussion.html for more detail.

----------------------------------------------------------*/
package depaul.csc435.Mimer;

import java.io.*;
import java.net.Socket;
import com.thoughtworks.xstream.XStream;

public class BCHandlerB {

	public static void main(String[] args) throws IOException {
		
		System.out.println("\nShan's Backend Channel Client starting up.");
		
		if (args.length >= 1) {
			
			String server_ip = "127.0.0.1";
			int server_port = 2570;

			System.out.println("Opening " + args[0]);
			FileReader fr = new FileReader (args[0]); // Open the downloaded file for reading.
			BufferedReader br = new BufferedReader(fr);
			
			myDataArray mda = new myDataArray();
			String line = "";
			int i = 0;

			while ((line=br.readLine())!=null) {
				mda.lines.add(line); // Insert the line to ArrayList.
				if (line.indexOf("server_ip")>=0) { //Find the server_ip keyword
					server_ip = line.substring(10,line.length());
				}
				if (line.indexOf("server_port")>=0) { //Find the server_port keyword
					server_port = Integer.valueOf(line.substring(12,line.length()));
				}
				i++;
			}
			mda.num_lines = i;
			br.close();
			fr.close();
			
			XStream xstream = new XStream();
			String xml_output = xstream.toXML(mda); // Convert myDataArray to XML format.
			System.out.println("\nThe converted output is:\n"+xml_output);
			
			// Write the result to disk. -> TEMP_DIR/output.xml
			String XMLfileName = System.getProperty("java.io.tmpdir") + "output.xml";
			System.out.println("\nWriting the result to " + XMLfileName);
			File xmlFile = new File(XMLfileName);
			if (xmlFile.exists() == true && xmlFile.delete() == false) {
				throw (IOException) new IOException("XML file delete failed.");
		    }
			xmlFile = new File(XMLfileName);
			if (xmlFile.createNewFile() == false){
				throw (IOException) new IOException("XML file creation failed.");
			}
			else {
				PrintWriter toXmlOutputFile = new PrintWriter(new BufferedWriter(new FileWriter(XMLfileName)));
				toXmlOutputFile.print(xml_output);
				toXmlOutputFile.close();
			}
			
			// Establish the back-end channel to server using hard-coded ip and port number.
			
			System.out.println("\nConnecting to server: " + server_ip + " on port: " + server_port);
			Socket sock = new Socket(server_ip, server_port);	// Get a connection to server
			PrintStream toServer = new PrintStream(sock.getOutputStream());
			// Send the XML result to server.
			toServer.print(xml_output);
			toServer.flush();
			sock.close();
			
			System.out.println("\nEverything is done! Have a nice day!");
			
		}
		else {
			System.out.println("No argument! The program will exit.");
		}

	}

}
