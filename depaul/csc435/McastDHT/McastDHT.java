package depaul.csc435.McastDHT;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Random;

public class McastDHT {

	public static int Predecessor = 40001; // Pointer to Predecessor
	public static int Successor = 40001; // Pointer to Successor
	public static int ComPort = 40001; // My own ID
	public static final int Timeout = 3000; // Default time out period.
	public static final String[] Command = {
		"exit", "status", "ping", "loopping", "survey", "file",
		"mcast create", "mcast Add", "mcast send", "mcast remove", "mcast destroy"
	};
	
	
	public static void main(String[] args) throws Exception{
		
		System.out.println("Shan's Multi-case DHT node starting.");
		
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		DatagramSocket clientSocket = new DatagramSocket();
		clientSocket.setSoTimeout(Timeout); // Set default time out period.
		InetAddress IPAddress = InetAddress.getByName("localhost");
		boolean root_node = false;
		boolean registered = false;
		
		// Ping node 40001 to decide if I am the root node?
		System.out.println("Pinging 40001 to decide if I am the root node?");
		sendData = "ping".getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 40001);
		clientSocket.send(sendPacket);
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		try {
			clientSocket.receive(receivePacket);
			System.out.println("Got response from 40001, so I am not the root node.");
			root_node = false;
			
		} catch (SocketTimeoutException e) {
			System.out.println("No response from 40001, I am the root node!");
			root_node = true;
		}
		
		// If I am not the root node, I will need to generate a random ComPort. Then check if it is used.
		if (!root_node) {
			Random rnd = new Random();
			boolean conflict = true;
			while (conflict) {
				ComPort = 40001 + rnd.nextInt(999) +1; // Generate a random port from 40002~41000
				sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, ComPort);
				clientSocket.send(sendPacket);
				receivePacket = new DatagramPacket(receiveData, receiveData.length);
				try {
					clientSocket.receive(receivePacket);
					System.out.println("Got response from "+ComPort+", so I cannot use this port.");
				} catch (SocketTimeoutException e) {
					System.out.println("No response from "+ComPort+", I will use this port!");
					conflict=false;
				}
			}
		}
		
		// If I am not the root node, I will need to register myself in the DHT.
		if (!root_node) {
			clean_data(sendData);
			sendData = ("register "+ComPort+" "+clientSocket.getLocalPort()).getBytes();
			sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 40001);
			clientSocket.send(sendPacket);
			receivePacket = new DatagramPacket(receiveData, receiveData.length);
			try {
				clientSocket.receive(receivePacket);
				// If successfully registered, Predecessor node will return a message containing Predecessor and Successor ComPort.
				String[] parts = (new String (receivePacket.getData())).toString().trim().split(" ");
				Predecessor = Integer.valueOf(parts[0]);
				Successor = Integer.valueOf(parts[1]);
				System.out.println("Successfully registered! My Predecessor is " + Predecessor + " and my Successor is " + Successor);
				registered = true;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Failed to register. I will quit...");
			}
		}
		
		if (root_node || registered) {
			//Start a ServerLoop to process all messages from the DHT
			ServerLoop sl = new ServerLoop();
			sl.start();
			
			//Start to accept user input
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String cmd ="";
			do {
				System.out.print("Input a command, (exit) to end: ");
				System.out.flush();
				cmd = in.readLine().toLowerCase();
				if (isValidCmd(cmd)) {
					if (cmd.startsWith("exit")) { // Ask to exit the DHT
						if (root_node) {
							System.out.println("You are root node. Not allowed to exit at this moment...");
							cmd = "noexit";
						}
						else {
							// Ask my Predecessor and Successor to update their info
							clean_data(sendData);
							sendData = ("updatesuccessor "+McastDHT.Successor).getBytes();
							sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, McastDHT.Predecessor);
							clientSocket.send(sendPacket);
							clean_data(sendData);
							sendData = ("updatepredecessor "+McastDHT.Predecessor).getBytes();
							sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, McastDHT.Successor);
							clientSocket.send(sendPacket);
							
							// Ask my ServerLoop thread to exit
							clean_data(sendData);
							sendData = ("exit").getBytes();
							sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, McastDHT.ComPort);
							clientSocket.send(sendPacket);
						}
					}
					else {
						System.out.println("Not implemented...");
					}
				}
				else {
					//System.out.println("Your input is invalid.");
					printUsage();
				}
				
			} while (!cmd.startsWith("exit"));
			
			clientSocket.close();
			System.out.print("Existing DHT...");
		}

	}
	
	static void printUsage() {
		System.out.println("Valid Commands:");
		System.out.println("Status % Display NodeID, predecessor NodeID, successor NodeID, Forwarder/Member Status for any Mcast group, and [McastID for any Mcast Root nodes managed by this node]");
		System.out.println("Ping [ComPort] % Send a ping to this ComPort, get a response, both nodes display ping information of sender on their console.");
		System.out.println("LoopPing [Msg] % Forward ping around DHT, all consoles display LoopPing Msg");
		System.out.println("Survey % Display a list of all DHT nodes, in order, starting and ending with the current node.");
		System.out.println("File [FileName] % Read this ascii file full of commands.");
		System.out.println("Mcast Create [McastID] % Create Mcast group with this ID. In the basic assignment, this will be issued only once.");
		System.out.println("Mcast Add [McastID] [NodeID] % Add this DHT node to Mcast group, possibly by changing from forwarder status");
		System.out.println("Mcast Send [McastID] [Msg] % Send Message to Mcast group");
		System.out.println("Mcast Remove [McastID] [NodeID] % Remove DHT node frm Mcast group by changing to forwarder status");
		System.out.println("Mcast Destroy [McastID] % HARD! Bragging rights. Remove all traces of the Mcast group. Non-trivial.");
	}
	
	static boolean isValidCmd(String cmd) {
		
		for (String s : Command) {
			if (cmd.startsWith(s)) return true;
		}
		return false;
	}
	
	static void clean_data (byte[] array) {
		for (int i=0; i<array.length; i++)
			array[i]=0;
	}
}

class ServerLoop extends Thread {

	public void run() {
		try {
			DatagramSocket serverSocket = new DatagramSocket(McastDHT.ComPort);
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			
			String message = ""; 
			do
			{
				clean_data(receiveData);
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
				message = (new String(receivePacket.getData())).trim();
				System.out.println("RECEIVED: " + message);
				String[] parts = message.split(" ");
				InetAddress IPAddress = receivePacket.getAddress();
				DatagramPacket sendPacket = null;
				
				// Received Ping message. Reply with Pong.
				if (message.startsWith("ping")) { 
					int port = receivePacket.getPort();
					clean_data(sendData);
					sendData = "Pong".getBytes();
					sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
					serverSocket.send(sendPacket);
				}
				// Received Register request.
				else if (message.startsWith("register")){
					int com_port = Integer.valueOf(parts[1]);
					int src_port = Integer.valueOf(parts[2]);
					// Shall I take care of this register request? Or I just pass it to my successor?
					if (McastDHT.ComPort == 40001) { // I am the root.
						if (McastDHT.Successor == 40001) { // So this is the first register request.
							clean_data(sendData);
							sendData = "40001 40001".getBytes();
							sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, src_port);
							serverSocket.send(sendPacket);
							McastDHT.Predecessor = com_port;
							McastDHT.Successor = com_port;
						}
						else { // Pass the register request to my Successor
							clean_data(sendData);
							sendData = message.getBytes();
							sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, McastDHT.Successor);
							serverSocket.send(sendPacket);
						}
					}
					else { // I am not root
						if (McastDHT.ComPort > com_port) { // Insert the node in front of me
							// Tell my Predecessor to update its Successor to com_port
							clean_data(sendData);
							sendData = ("updatesuccessor "+com_port).getBytes();
							sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, McastDHT.Predecessor);
							serverSocket.send(sendPacket);
							// Reply register message to the new node
							clean_data(sendData);
							sendData = (McastDHT.Predecessor + " " + McastDHT.ComPort).getBytes();
							sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, src_port);
							serverSocket.send(sendPacket);
							// Update my Predecessor as the newly registered node.
							McastDHT.Predecessor=com_port;
						}
						else {
							if (McastDHT.Successor == 40001) { // I am the last node.
								// Tell root to update its Predecessor to com_port
								clean_data(sendData);
								sendData = ("updatepredecessor "+com_port).getBytes();
								sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 40001);
								serverSocket.send(sendPacket);
								// Reply register message to the new node
								clean_data(sendData);
								sendData = (McastDHT.ComPort + " 40001").getBytes();
								sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, src_port);
								serverSocket.send(sendPacket);
								// Update my Successor as the newly registered node.
								McastDHT.Successor=com_port;
							}
							else {
								// Pass the register request to my Successor
								clean_data(sendData);
								sendData = message.getBytes();
								sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, McastDHT.Successor);
								serverSocket.send(sendPacket);
							}
						}
					}
				}
				// Received Update Successor message.
				else if (message.startsWith("updatesuccessor")){
					McastDHT.Successor = Integer.valueOf(parts[1]);
				}
				// Received Update Predecessor message.
				else if (message.startsWith("updatepredecessor")){
					McastDHT.Predecessor = Integer.valueOf(parts[1]);
				}
				
			} while(!message.startsWith("exit"));
			
			serverSocket.close();
			System.out.println("ServerLoop thread existing...");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void clean_data (byte[] array) {
		for (int i=0; i<array.length; i++) array[i]=0;
	}
}
