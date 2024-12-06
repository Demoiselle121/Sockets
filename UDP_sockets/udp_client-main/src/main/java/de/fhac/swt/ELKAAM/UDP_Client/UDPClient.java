package de.fhac.swt.ELKAAM.UDP_Client;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
public class UDPClient {

public static void main(String[] args) throws Exception
{
	//Starten eines UDP-Sockets
	DatagramSocket ds=new DatagramSocket();
	while(true){
			int port=Integer.parseInt(args[0]);
			InetAddress ip=InetAddress.getByName(args[1]);
			DataInputStream inp=new DataInputStream(System.in);
			String Message=inp.readLine();
			byte[] send=new byte[1024];
			byte[] rcv=new byte[1024];
			send=Message.getBytes();
			DatagramPacket sp=new DatagramPacket(send,send.length,ip,port);
			System.out.println("die Länge des String: "+send.length);
			ds.send(sp);
			DatagramPacket rp=new DatagramPacket(rcv,rcv.length);
			ds.receive(rp);
			String m=new String(rp.getData()).trim();
			System.out.println("Echo Server: "+m);
		}
}
}