
/*--------------------------------------------------------

1. Name / Date: Gao Shan / Oct. 18, 2014

2. Java version used: jre1.7.0_67

3. Precise command-line compilation examples / instructions:
> javac -cp .;xstream-1.2.1.jar;xpp3_min-1.1.3.4.O.jar MyWebServer.java

4. Precise examples / instructions to run this program:
> java -cp .;xstream-1.2.1.jar;xpp3_min-1.1.3.4.O.jar MyWebServer.java

5. List of files needed for running the program.
 a. csc435\myDataArray.java
 b. MyWebServer.java
 c. BCHandler.java
 d. shim.bat
 e. mimer-data.xyz
 f. mimer-call.html
 g. mimer-discussion.html
 h. checklist-mimer.html
 i. xstream-1.2.1.jar
 j. xpp3_min-1.1.3.4.O.jar

6. Notes:
See line 177 for the self-developed MIME type.
And lines from 243 for the new codes to handle back-end channel connection.

----------------------------------------------------------*/
package depaul.csc435.Mimer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import com.thoughtworks.xstream.XStream;

class Worker extends Thread { // The worker thread which serves connection from JokeClient
	Socket sock;
	Worker (Socket s) {sock = s;}	// Get the socket handler.
	
	public void run(){
		PrintStream out = null;
		BufferedReader in = null;
		String request = "";
		String inLine = "";
		
		try {
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintStream(sock.getOutputStream());
			
			// Get the first line, and ignore rest...
			while ((inLine = in.readLine()) != null) {
				//System.out.println(inLine);
				if (inLine==null || inLine.length()==0) break;
				if (request == "") request = inLine;
			}
			
			if (!isValidRequest(request)) {
				error_handler(out, sock, "400", request + " - Bad Request", 
                        "Your browser sent a bad request.");
			}
			else {
				log(sock, request);
				String uri = request.substring(4, request.length()-9).trim(); // Get the request uri
				int type = reqType(uri);
				//System.out.println(type);
				File curPath = new File((new File(".").getCanonicalPath()+uri)); // Current File/Directory requested
				
				if (type == 1) { // Request to a file.
					InputStream file = new FileInputStream(curPath);
                    out.print("HTTP/1.0 200 OK\r\n" +
                               "Content-Type: " + guessContentType(uri) + "\r\n" +
                               "Date: " + new Date() + "\r\n" +
                               "Server: Gao Shan's MyWebServer 1.0\r\n\r\n");
                    sendFile(file, out); // send raw file 
                    log(sock, uri + " - 200 OK");
					
				} else if (type == 2) { // Request to a directory.
					out.print("HTTP/1.0 200 OK\r\n" +
                            "Content-Type: text/html\r\n" +
                            "Date: " + new Date() + "\r\n" +
                            "Server: Gao Shan's MyWebServer 1.0\r\n\r\n");
					
					 // Create link to parent directory if not in wwwroot.
					if (!uri.equals("/"))
				    	out.println("<a href=\"" + parentDir(uri) + "\">Parent Directory</a><br><br>");
					
					// Get all the files and directory under current directory and create hyper links for them.
					String fname = "";
				    File[] strFilesDirs = curPath.listFiles();				    
				    for ( int i = 0; i < strFilesDirs.length; i ++ ) {
				    	fname = subDir(strFilesDirs[i].toString());
				    	if (strFilesDirs[i].isDirectory()) fname = fname + "/";
				    	out.println ( "<a href=\"" + uri + fname +"\">" + fname  + "</a><br>");
				    }
				    log(sock, uri + " - 200 OK");
				    
				} else if (type == 3) { // Request to fake-cgi.
					// Get the three parameters and values from the request uri.
					boolean valid_cgi = false;
					String person = "Noname";
					int num1 = 0;
					int num2 = 0;
					String[] part1 = uri.split("\\?"); // Split the uri by "?", the 2nd part contents the parameters and values
					if (part1.length == 2) {
						if (part1[1].length()>0) {
							valid_cgi = true;
							String[] part2 = part1[1].split("&"); // Split by "&", each is a parameter/value pair.
							for (int i=0; i<part2.length; i++) {
								String[] part3 = part2[i].split("="); // Split by "=", the 1st part is parameter, the 2nd part is value.
								if (part3.length == 2) {
									if (part3[0].equalsIgnoreCase("person")) person=part3[1];
									if (part3[0].equalsIgnoreCase("num1")) num1=Integer.parseInt(part3[1]);
									if (part3[0].equalsIgnoreCase("num2")) num2=Integer.parseInt(part3[1]);
									//System.out.print(part3[0] + "=" + part3[1] + "\n");
								}
							}
							
						}
					}

					if (valid_cgi) {
						out.print("HTTP/1.0 200 OK\r\n" +
	                            "Content-Type: text/html\r\n" +
	                            "Date: " + new Date() + "\r\n" +
	                            "Server: Gao Shan's MyWebServer 1.0\r\n\r\n");
						out.print("<h1>Dear " + person + ", the sum of " + num1 + " and " + num2 + " is " + (num1+num2) +".</h1>");
						log(sock, uri + " - 200 OK - Fake CGI Addnum executed.");
					}
					else
						error_handler(out, sock, "400", uri + " - 400 BAD REQUEST", 
								"The syntax of the request is badly formulated.");
					
				}
				else // File Not Found
					error_handler(out, sock, "404", uri + " - 404 NOT FOUND", 
						"The server does not find the resource you requested");
			}

			sock.close();
		} catch (IOException ioe) {System.out.println(ioe);}
	}
	
	private String parentDir (String Dir) {
		int i = 0;
		if (!Dir.endsWith("/")) Dir += "/";
		for (i = Dir.length()-2; i >=0; i --)
			if (Dir.charAt(i) == '/') break;
		return Dir.substring(0, i+1);
	}
	
	private String subDir (String Dir) {
		int i = 0;
		Dir = Dir.replace("\\", "/");
		if (Dir.endsWith("/")) Dir = Dir.substring(1, Dir.length());
		for (i = Dir.length()-1; i >=0; i --)
			if (Dir.charAt(i) == '/') break;
		return Dir.substring(i+1, Dir.length());
	}
	
	private static String guessContentType(String fn)
    {
        if (fn.endsWith(".html") || fn.endsWith(".htm")) 
            return "text/html";
        else if (fn.endsWith(".gif")) 
            return "image/gif";
        else if (fn.endsWith(".class"))
            return "application/octet-stream";
        else if (fn.endsWith(".jpg") || fn.endsWith(".jpeg"))
            return "image/jpeg";
        else if (fn.endsWith(".ico"))
        	return "image/x-icon";
        else if (fn.endsWith(".xyz"))
        	return "application/xyz";  // This is the self-developed MIME type.
        else
            return "text/plain";
    }

    private static void sendFile(InputStream file, OutputStream out)
    {
        try {
            byte[] buffer = new byte[1000];
            while (file.available()>0) 
                out.write(buffer, 0, file.read(buffer));
        } catch (IOException e) { System.err.println(e); }
    }
    
	private int reqType(String uri) {
		//System.out.println(uri);
		if (uri.indexOf(".fake-cgi")>0) return 3; // Type 3 is for CGI
		
		// Try to analyze it is a file request or a directory request.
		try {
			File file1 = new File(".");
			String curPath = file1.getCanonicalPath();
			
			File file2 = new File(curPath + uri);
			String reqPath = file2.getCanonicalPath();
			
			//System.out.println(curPath + "\n" + reqPath);
			if (reqPath.startsWith(curPath)) // Only directory/file under wwwroot is allowed.
				if (file2.exists()) {  // Requested directory/file exits
					if (file2.isFile()) return 1; // Type 1 is for file
					else return 2; // Type 2 is for directory
				}
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return 0; // 404 Not Found
	}
	
	private boolean isValidRequest (String req) {
		if (!(req.startsWith("GET") || req.startsWith("POST"))
				|| !(req.endsWith("HTTP/1.0") || req.endsWith("HTTP/1.1")))
			return false;
		else
			return true;
	}
	
	private static void log(Socket connection, String msg)
    {
        System.out.println(new Date() + " [" + connection.getInetAddress().getHostAddress() + 
                           ":" + connection.getPort() + "] " + msg);
    }

    private static void error_handler(PrintStream out, Socket sock, String code, String title, String msg)
    {
        out.print( "HTTP/1.0 " + code + " " + title + "\r\n" +
                   "\r\n" +
                   "<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\">\r\n" +
                   "<TITLE>" + code + " " + title + "</TITLE>\r\n" +
                   "</HEAD><BODY>\r\n" +
                   "<H1>" + title + "</H1>\r\n" + msg + "<P>\r\n" +
                   "<HR><ADDRESS>Gao Shan's MyWebServer 1.0 at " + 
                   sock.getLocalAddress().getHostName() + 
                   " Port " + sock.getLocalPort() + "</ADDRESS>\r\n" +
                   "</BODY></HTML>\r\n");
        log(sock, title);
    }
}

class BCWorker extends Thread { // The worker thread which serves connection from BCHandler.
	Socket sock;
	BCWorker (Socket s) {sock = s;}	// Get the socket handler.
	
	public void run(){
		BufferedReader in = null;
		String line = "";
		StringBuilder xml_input = new StringBuilder();
		
		try {
			// Read XML input.
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			while ((line = in.readLine())!=null) {
				xml_input.append(line+"\n");
			}
			System.out.println("The XML input is:\n"+xml_input);
			sock.close();
			
			// Un-marshall the XML back to myDataArray type.
			XStream xstream = new XStream();
			myDataArray mda = (myDataArray) xstream.fromXML(xml_input.toString());
			System.out.println("The convert result is:");
			System.out.println("num_lines: "+mda.num_lines);
			System.out.println("lines:");
			for (int i=0; i<mda.num_lines; i++) {
				System.out.println(mda.lines.get(i));
			}
			
		} catch (IOException ioe) {System.out.println(ioe);}
	}
}

class BCLooper implements Runnable {
	
	public void run(){ // Running the back-end channel listen loop
	
		int q_len = 6; /* Number of requests for OpSys to queue */
		int port = 2570;  // We are listening at a different port for BCHandler to connect.
		Socket sock;
		try{
			ServerSocket servsock = new ServerSocket(port, q_len);
			System.out.println("Backend Channel Looper starting up, listening at port " + port + " for BCHandler.");
			while (true) {
				sock = servsock.accept();
				new BCWorker (sock).start();
			}
		} catch (IOException ioe) {System.out.println(ioe);}
		
	}
}

public class MyWebServer {

	public static void main(String[] args) throws IOException {
		BCLooper BCL = new BCLooper(); // create a thread for back-end channel connection.
	    Thread t = new Thread(BCL);
	    t.start();  // ...and start it, waiting for BCHandler to connect.
		
		int q_len = 6;	//The maximum queue length for incoming connection indications (a request to connect) is set to the backlog parameter. If a connection indication arrives when the queue is full, the connection is refused.
		int port = 2540;
		Socket sock;
		ServerSocket servsock = new ServerSocket(port, q_len);
		System.out.println("\nGao Shan's MyWebServer starting up, listening at port " + port + " for browser.");
		
		while (true) { // Loop forever...
			sock = servsock.accept();
			new Worker(sock).start();
		}
		
	}
}
