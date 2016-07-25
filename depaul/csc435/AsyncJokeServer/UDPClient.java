package depaul.csc435.AsyncJokeServer;

import java.io.*;
import java.net.*;

class UDPClient
{
	public static void main(String args[]) throws Exception
	{
		DatagramSocket clientSocket = new DatagramSocket();
		clientSocket.setSoTimeout(2000);
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		
		String sentence = "Ping";
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		clientSocket.send(sendPacket);
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		try {
			clientSocket.receive(receivePacket);
			String modifiedSentence = new String(receivePacket.getData());
			System.out.println("FROM SERVER:" + modifiedSentence);
		} catch (SocketTimeoutException e) {
			System.out.println("SocketTimeout.");
		}
		
		sentence = "Register 1234 5678";
		sendData = sentence.getBytes();
		sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		clientSocket.send(sendPacket);
		receivePacket = new DatagramPacket(receiveData, receiveData.length);
		try {
			clientSocket.receive(receivePacket);
			String modifiedSentence = new String(receivePacket.getData());
			System.out.println("FROM SERVER:" + modifiedSentence);
		} catch (SocketTimeoutException e) {
			System.out.println("SocketTimeout.");
		}
		
		clientSocket.close();
	}
}