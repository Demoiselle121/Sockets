package de.fhac.swt.ELKAAM.UDPMultiClient;


import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer implements  Runnable {
	public final static int PORT = 2020;
	private final static int BUFFER = 1024;

	private DatagramSocket socket;
	private ArrayList<InetAddress> client_addresses;
	private ArrayList<Integer> client_ports;
	private HashSet<String> existing_clients;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ChatServer() throws IOException {
		socket = new DatagramSocket(PORT);
		System.out.println("Server is running and is listening on port " + PORT);
		client_addresses = new ArrayList();
		client_ports = new ArrayList();
		existing_clients = new HashSet();
	}

	public void run() {
		byte[] buffer = new byte[BUFFER];
		while (true) {
			try {
				Arrays.fill(buffer, (byte) 0);
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				socket.receive(packet);

				String message = new String(buffer, 0, buffer.length);

				InetAddress clientAddress = packet.getAddress();
				int client_port = packet.getPort();
				byte[] send=new byte[1024];
				String id = clientAddress.toString() + "|" + client_port;
				
				if (!existing_clients.contains(id)) {
					existing_clients.add(id);
					client_ports.add(client_port);
					client_addresses.add(clientAddress);
				}

				
				String r=new String(packet.getData()).trim();
				System.out.println("Echo client : "+r);
				if( r.equals("broad")) {
					byte[] data = (id + " : " + "Es ist ein Broadcast").getBytes();
					for (int i = 0; i < client_addresses.size(); i++) {
						InetAddress cl_address = client_addresses.get(i);
						int cl_port = client_ports.get(i);
						packet = new DatagramPacket(data, data.length, cl_address, cl_port);
						socket.send(packet);
						return;
				}
				}
				
				else {
				send=r.toUpperCase().getBytes();
				InetAddress ip=packet.getAddress();
				int port=packet.getPort();
				DatagramPacket sp=new DatagramPacket(send,send.length,ip,port);
				socket.send(sp);
				}
			} catch (Exception e) {
				System.err.println(e);
			}
		}
		}

	public static void main(String args[]) throws Exception {
		ChatServer server_thread = new ChatServer();
		server_thread.run();
	}
}