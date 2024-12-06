package de.fhac.swt.ELKAAM.UDP_Server;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
	public static void main(String[] args) throws Exception
	{
		int port;
		DatagramSocket dp=new DatagramSocket(Integer.parseInt(args[0]));
		while(true)
		{
				byte[] send=new byte[1024];
				byte[] rcv=new byte[1024];
				DatagramPacket rp=new DatagramPacket(rcv,rcv.length);
				dp.receive(rp);
				String r=new String(rp.getData()).trim();
				System.out.println("Echo client : "+r);
				
				send=r.toUpperCase().getBytes();
				InetAddress ip=rp.getAddress();
				port=rp.getPort();
				DatagramPacket sp=new DatagramPacket(send,send.length,ip,port);
				dp.send(sp);
		}
	}
	}
