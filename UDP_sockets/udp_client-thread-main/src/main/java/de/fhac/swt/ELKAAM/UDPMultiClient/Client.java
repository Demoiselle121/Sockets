package de.fhac.swt.ELKAAM.UDPMultiClient;


import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class Client implements Runnable {
public static void main(String args[]) throws Exception {
		
		
		
		DatagramSocket socket = new DatagramSocket();
		MessageReceiver receiver = new MessageReceiver(socket);
		Client sender = new Client(socket, "localhost");
		Thread receiverThread = new Thread(receiver);
		Thread senderThread = new Thread(sender);
		receiverThread.start();
		senderThread.start();
	}

	public final static int PORT = 2020;
	private DatagramSocket socket;
	private String hostName;

	Client(DatagramSocket sock, String host) {
		socket = sock;
		hostName = host;
	}

	private synchronized void sendMessage(String s) throws Exception {
		byte buffer[] = s.getBytes();
		InetAddress address = InetAddress.getByName(hostName);
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PORT);
		socket.send(packet);
	}

	public void run() {
		
		while(true) {
		DataInputStream inp=new DataInputStream(System.in);
		String Message = null;
		try {
			Message = inp.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
			try {
				
					Thread.sleep(100);
					sendMessage(Message);
				
				
				
			} catch (Exception e) {
			}
		
	}
	}
}

class MessageReceiver implements Runnable {
	DatagramSocket socket;
	byte buffer[];

	MessageReceiver(DatagramSocket sock) {
		socket = sock;
		buffer = new byte[1024];
	}

	public void run() {
		while (true) {
			try {
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				socket.receive(packet);
				String received = new String(packet.getData()).trim();
				System.out.println("Echo Server: "+received);
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}
}



	
